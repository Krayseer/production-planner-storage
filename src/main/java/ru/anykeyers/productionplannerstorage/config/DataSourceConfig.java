package ru.anykeyers.productionplannerstorage.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конфигурация динамической настройки базы данных<p/>
 * Если PostgreSQL недоступен - используется база данных H2
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String H2_URL = "jdbc:h2:mem:production_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String H2_USERNAME = "sa";
    private static final String H2_PASSWORD = "";

    @Value("${spring.datasource.url}")
    private String postgresUrl;
    @Value("${spring.datasource.username}")
    private String postgresUser;
    @Value("${spring.datasource.password}")
    private String postgresPassword;

    @Bean
    public DataSource dataSource() {
        if (isPostgresAvailable()) {
            log.info("✅ Подключение к PostgreSQL успешно: {}", postgresUrl);
            return createDataSourcePostgreSQL();
        } else {
            log.warn("⚠️ PostgreSQL недоступен, переключаемся на H2 (in-memory)");
            return createDataSourceH2();
        }
    }

    private boolean isPostgresAvailable() {
        try (Connection ignored = createDataSourcePostgreSQL().getConnection()) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private DataSource createDataSourcePostgreSQL() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(POSTGRES_DRIVER);
        ds.setUrl(postgresUrl);
        ds.setUsername(postgresUser);
        ds.setPassword(postgresPassword);
        return ds;
    }

    private DataSource createDataSourceH2() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(H2_DRIVER);
        ds.setUrl(H2_URL);
        ds.setUsername(H2_USERNAME);
        ds.setPassword(H2_PASSWORD);
        return ds;
    }

}
