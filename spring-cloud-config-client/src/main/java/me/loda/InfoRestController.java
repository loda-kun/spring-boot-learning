package me.loda;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoRestController {
    @Value("${db.name:Data Null}")
    private String dbName;

    @Value("${db.username:Data Null}")
    private String dbUsername;

    @Value("${db.password:Data Null}")
    private String dbPassword;

    @Value("${message1:Data Null}")
    private String message1;

    @Value("${message2:Data Null}")
    private String message2;

    @RequestMapping("/info")
    Object getMessage() {
        Map<String, String>  data = new HashMap<String, String>();
        data.put("db.name", dbName);
        data.put("db.username", dbUsername);
        data.put("db.password", dbPassword);
        data.put("message1", message1);
        data.put("message2", message2);

        return data;
    }
}
