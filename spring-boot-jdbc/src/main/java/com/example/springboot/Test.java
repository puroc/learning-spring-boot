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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by puroc on 2017/11/17.
 */
@Component
public class Test implements CommandLineRunner {

    public static final String SQL = "INSERT INTO card_e_t VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?)";
    public static final int RANDOM_BOUND = 100;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final int INSERT_NUM_PER_THREAD = 1000000;

    private static final int BATCH_INSERT_NUM = 10;

    private static final int THREAD_NUM = 50;

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
            ps.setString(3, card.getImsi());
            ps.setString(4, card.getMsisdn());
            ps.setString(5, card.getPin1());
            ps.setString(6, card.getPin2());
            ps.setString(7, card.getPuk1());
            ps.setString(8, card.getPuk2());
            ps.setString(9, card.getCustomerId());
            ps.setString(10, card.getBindImei());
            ps.setString(11, card.getCurrentImei());
            ps.setString(12, card.getDeviceStateId());
            ps.setString(13, card.getSailStateDic());
            ps.setString(14, card.getOperatorKeyDic());
            ps.setString(15, card.getChannelId());
            ps.setString(16, card.getMaterialDic());
            ps.setString(17, card.getTypeDic());
            ps.setTimestamp(18, Timestamp.valueOf(card.getInNetDate()));
            ps.setTimestamp(19, Timestamp.valueOf(card.getActiveDate()));
            ps.setTimestamp(20, Timestamp.valueOf(card.getRefreshDate()));
            ps.setString(21, card.getCountryId());
            ps.setString(22, card.getProvinceId());
            ps.setString(23, card.getCityId());
            ps.setString(24, card.getCreateBy());
            ps.setTimestamp(25, Timestamp.valueOf(card.getUpdateDate()));
            ps.setTimestamp(26, Timestamp.valueOf(card.getCreateDate()));
            ps.setString(27, card.getUpdateBy());
            ps.setString(28, card.getDelBy());
            ps.setTimestamp(29, Timestamp.valueOf(card.getDelDate()));
            ps.setInt(30, 1);
            ps.setString(31, card.getPermExt());
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
                    Counter.getInstance().add();
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
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long target = position + BATCH_INSERT_NUM;
        for (long i = position; i < target; i++) {
            Card card = new Card();
            card.setId(i + "");
            card.setIccid(i + "");
            card.setImsi(random.nextInt(RANDOM_BOUND) + "");
            card.setMsisdn(random.nextInt(RANDOM_BOUND) + "");
            card.setPin1(random.nextInt(RANDOM_BOUND) + "");
            card.setPin2(random.nextInt(RANDOM_BOUND) + "");
            card.setPuk1(random.nextInt(RANDOM_BOUND) + "");
            card.setPuk2(random.nextInt(RANDOM_BOUND) + "");
            card.setCustomerId(random.nextInt(RANDOM_BOUND) + "");
            card.setBindImei(random.nextInt(RANDOM_BOUND) + "");
            card.setCurrentImei(random.nextInt(RANDOM_BOUND) + "");
            card.setDeviceStateId(random.nextInt(RANDOM_BOUND) + "");
            card.setSailStateDic(random.nextInt(RANDOM_BOUND) + "");
            card.setOperatorKeyDic(random.nextInt(RANDOM_BOUND) + "");
            card.setChannelId(random.nextInt(RANDOM_BOUND) + "");
            card.setMaterialDic(random.nextInt(RANDOM_BOUND) + "");
            card.setTypeDic(random.nextInt(RANDOM_BOUND) + "");
            card.setInNetDate(sdf.format(new Date()) + "");
            card.setActiveDate(sdf.format(new Date()) + "");
            card.setRefreshDate(sdf.format(new Date()) + "");
            card.setCountryId(random.nextInt(RANDOM_BOUND) + "");
            card.setProvinceId(random.nextInt(RANDOM_BOUND) + "");
            card.setCityId(random.nextInt(RANDOM_BOUND) + "");
            card.setCreateBy(random.nextInt(RANDOM_BOUND) + "");
            card.setUpdateDate(sdf.format(new Date()) + "");
            card.setCreateDate(sdf.format(new Date()) + "");
            card.setUpdateBy(random.nextInt(RANDOM_BOUND) + "");
            card.setDelBy(random.nextInt(RANDOM_BOUND) + "");
            card.setDelDate(sdf.format(new Date()) + "");
            card.setDelFlag(random.nextInt(RANDOM_BOUND) + "");
            card.setPermExt(random.nextInt(RANDOM_BOUND) + "");
            list.add(card);
        }
        return list;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);

    }

}
