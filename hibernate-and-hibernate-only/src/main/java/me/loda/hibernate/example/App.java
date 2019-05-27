package me.loda.hibernate.example;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/26/2019
 * Github: https://github.com/loda-kun
 */
public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        /*
        Mọi thao tác với DB bắt từ từ một session
         */
        Session session = factory.getCurrentSession();

        try {
            /*
                session phải mở transaction trước khi thực hiện
             */
            session.getTransaction().begin();

            String jpql = "Select e from " + Todo.class.getName() + " e ";

            System.out.println(jpql);

            // Tạo đối tượng Query.
            Query<Todo> query = session.createQuery(jpql, Todo.class);

            // Thực hiện truy vấn.
            List<Todo> todos = query.getResultList();

            for (Todo todo : todos) {
                System.out.println(todo);
            }

            // Commit là thực hiện mọi thay đổi xuống DB nếu có
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback trong trường hợp có lỗi xẩy ra.
            session.getTransaction().rollback();
        }
    }

}
