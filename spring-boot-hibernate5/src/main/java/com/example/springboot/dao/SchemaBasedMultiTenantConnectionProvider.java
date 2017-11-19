package com.example.springboot.dao;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Stoppable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * 以租户特有的方式获取数据库连接
 * <p>
 * 说明：实现了MultiTenantConnectionProvider 接口，
 * 根据 tenantIdentifier 获得相应的连接。
 * 在实际应用中，可结合使用 JNDI DataSource 技术获取连接以提高性能。
 */
public class SchemaBasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider, Stoppable,
        Configurable, ServiceRegistryAwareService {

    private final DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();

    //得到数据库连接
    @Override
    public Connection getAnyConnection() throws SQLException {
        return connectionProvider.getConnection();
    }

    //关闭数据库连接
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connectionProvider.closeConnection(connection);
    }

    //根据不同用户，Use对应用户的库的链接
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
//            connection.createStatement().execute("USE " + tenantIdentifier);
            connection.createStatement().execute("set schema '" + tenantIdentifier + "'");
        } catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier
                    + "]", e);
        }
        return connection;
    }


    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
//            connection.createStatement().execute("USE test");
            connection.createStatement().execute("set schema 'test'");
        } catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier
                    + "]", e);
        }
        connectionProvider.closeConnection(connection);
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return this.connectionProvider.isUnwrappableAs(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return this.connectionProvider.unwrap(unwrapType);
    }

    @Override
    public void stop() {
        this.connectionProvider.stop();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return this.connectionProvider.supportsAggressiveRelease();
    }

    @Override
    public void configure(Map configurationValues) {
        this.connectionProvider.configure(configurationValues);

    }

    //注入服务
    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.connectionProvider.injectServices(serviceRegistry);

    }

}