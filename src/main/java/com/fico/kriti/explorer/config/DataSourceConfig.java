////
//// $Id: ApplicationContextSetter.java 12618 2016-01-13 09:15:44Z TruptiVaishnav $
////
//// Copyright (2014-2024), Fair Isaac Corporation. All Rights Reserved.
////
//package com.fico.kriti.explorer.config;
//
//import com.fico.dmp.context.DMPContext;
//import com.fico.dmp.core.DMPException;
//import com.fico.dmp.manager.model.ServiceInstance;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Configures the default JPA data source.
// */
//@Configuration
//public class DataSourceConfig {
//
//    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);
//    private static final String DMP_MYSQL_SERVICE_ID = "explorer-mysql";
//    private static final String USER_KEY = "dbUserName";
//    private static final String PASS_KEY = "dbPassword";
//    private static final String URL_KEY = "jdbcUrl";
//
//
//    /**
//     * Create a bone-cp data source
//     * <p>
//     * Default settings use very few connections, and release connections aggressively,
//     * so that resource usage can be kept low.
//     *
//     * @return
//     */
//    @Bean
//    @Primary
//    public DataSource getDataSource() {
//        _JdbcConfig conf = getConfig();
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setUsername(conf.username);
//        hikariConfig.setPassword(conf.password);
//        hikariConfig.setJdbcUrl(conf.url);
//        hikariConfig.setMaximumPoolSize(5);
//        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        return dataSource;
//    }
//
//    private _JdbcConfig getConfig() {
//        _JdbcConfig conf = new _JdbcConfig();
//        LOG.info("Loading JDBC config from DMP context");
//        Map<?, ?> dmpConf = _fetchDBServiceConfig();
//        conf.username = getStringEntry(dmpConf, USER_KEY);
//        conf.password = getStringEntry(dmpConf, PASS_KEY);
//        conf.url = getStringEntry(dmpConf, URL_KEY);
//        return conf;
//    }
//
//    private String getStringEntry(Map<?, ?> m, String key) {
//        Object value = m.get(key);
//        return String.valueOf(value);
//    }
//
//    private Map<?, ?> _fetchDBServiceConfig() {
//        Collection<ServiceInstance> serviceInstances = null;
//        ServiceInstance dmnMySqlService = null;
//
//        try {
//            serviceInstances = DMPContext.getContext().getComponentContext().listServices();
//        } catch (DMPException e) {
//            LOG.error(e.getMessage());
//        }
//        Iterator<ServiceInstance> iteratorObj = serviceInstances.iterator();
//        while (iteratorObj.hasNext()) {
//            ServiceInstance serviceInstance = iteratorObj.next();
//            //
//            LOG.info("Service Id::: " + serviceInstance.getServiceId());
//
//            if (serviceInstance.getServiceId().equals(DMP_MYSQL_SERVICE_ID)) {
//                dmnMySqlService = serviceInstance;
//                break;
//            }
//        }
//
//        if (dmnMySqlService == null) {
//            LOG.error("Explorer: Database Service could not be found.");
//        } else {
//            Map<?, ?> config = dmnMySqlService.getOptions();
//            //
//            LOG.info("MySQL Service configuration::: " + config.toString());
//            if (config == null) {
//                LOG.error("Explorer: Database Service could not be be initialized.");
//            }
//            return config;
//        }
//        return null;
//    }
//
//    /**
//     * Holder/wrapper for JDBC info
//     */
//
//    private static class _JdbcConfig {
//        private String url, username, password;
//    }
//}
