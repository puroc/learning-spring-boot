package com.example.springboot;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by puroc on 2017/11/17.
 */
@Component
public class Test implements CommandLineRunner {

    public static final String SQL = "INSERT INTO card_e_t VALUES (?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, " +
            "NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, " +
            "NULL, NULL, NULL, '0', NULL)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final int INSERT_NUM_PER_THREAD = 100;

    private static final int BATCH_INSERT_NUM = 10;

    private static final int THREAD_NUM = 20;

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        JdbcTemplate oracle = new JdbcTemplate();
        oracle.setDataSource(dataSource);
        return oracle;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://10.10.30.191:5432/postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxWait(60000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    class MyBatchSetter implements BatchPreparedStatementSetter {

        private List<Card> list;

        public MyBatchSetter(List<Card> list) {
            this.list = list;
        }

        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            final Card card = list.get(i);
            ps.setString(1, card.getId());
            ps.setString(2, card.getIccid());
        }

        @Override
        public int getBatchSize() {
            return list.size();
        }
    }

    class Task implements Runnable {

        private String threadName;
        private long position;

        public Task(String name, long position) {
            this.threadName = name;
            this.position = position;
        }

        public void run() {
            try {
                System.out.println("Thread " + threadName + " start insert.");
                if (INSERT_NUM_PER_THREAD % BATCH_INSERT_NUM != 0) {
                    throw new RuntimeException("INSERT_NUM_PER_THREAD % BATCH_INSERT_NUM != 0");
                }
                int num = INSERT_NUM_PER_THREAD / BATCH_INSERT_NUM;
                for (int i = 0; i < num; i++) {
                    List<Card> list = createData(position + i * BATCH_INSERT_NUM);
                    jdbcTemplate.batchUpdate(SQL, new MyBatchSetter(list));
                }
                System.out.println("Thread " + threadName + " finish.");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        Counter.getInstance().start();
        long start = System.currentTimeMillis();

        //创建线程
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(new Task(i + "", i * INSERT_NUM_PER_THREAD));
            threadList.add(thread);
        }

        //启动线程
        for (Thread thread : threadList) {
            thread.start();
        }

        //等待线程结束
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        long stop = System.currentTimeMillis();

        System.out.println("num:" + INSERT_NUM_PER_THREAD + ",time:" + (stop - start));
        Counter.getInstance().stop();
    }

    private List<Card> createData(long position) {
        List<Card> list = new ArrayList<Card>();
        long target = position + BATCH_INSERT_NUM;
        for (long i = position; i < target; i++) {
            Card card = new Card();
            card.setId(i + "");
            card.setIccid(i + "");
            list.add(card);
        }
        return list;
    }
}
