package me.loda.spring.applicationproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Lấy giá trị config từ file application.properties
    @Value("${loda.mysql.url}")
    String mysqlUrl;

    @Bean
    DatabaseConnector mysqlConfigure() {
        DatabaseConnector mySqlConnector = new MySqlConnector();
        // Set Url
        System.out.println("Config Mysql Url: " + mysqlUrl);
        mySqlConnector.setUrl(mysqlUrl);
        // Set username, password, format, v.v...
        return mySqlConnector;
    }
}
