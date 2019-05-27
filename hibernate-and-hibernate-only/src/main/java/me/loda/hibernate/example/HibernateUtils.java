package me.loda.hibernate.example;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/26/2019
 * Github: https://github.com/loda-kun
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Giải phóng cache và Connection Pools.
        getSessionFactory().close();
    }

    // Hibernate 5:
    private static SessionFactory buildSessionFactory() {
        // Tạo danh sách dịch vụ từ file config
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        // Tạo MetaData (siêu dữ liệu) cung cấp các thông tin về DB, charset, vv...
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        // Từ Metadata chúng ta có thể lấy ra SessionFactory, class đảm nhiệm tạo ra Session
        return metadata.getSessionFactoryBuilder().build();
    }

}
