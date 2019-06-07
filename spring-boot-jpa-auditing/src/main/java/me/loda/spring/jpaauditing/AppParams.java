package me.loda.spring.jpaauditing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-07
 * Github: https://github.com/loda-kun
 */
@Builder
@Data
@Entity
@Table(name = "app_params")
// Bạn phải đánh dấu class bởi @EntityListeners(AuditingEntityListener.class)
// Đây là Một đối tượng Listener, lắng nghe sự kiện insert hoặc update của đối tượng
// Để từ đó tự động cập nhật các trường @CreatedDate và @LastModifiedDate
@EntityListeners(AuditingEntityListener.class)
public class AppParams {
    private static final long serialVersionUID = 2783421558989997612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String paramName;
    private String paramValue;

    /*
    đánh dấu trường Date với @CreatedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào createdAt
     */
    @CreatedDate
    private Date createdAt;

    /*
    đánh dấu trường Date với @LastModifiedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào updatedAt
    Nếu đối tượng được update vào database lần tiếp theo -> nó sẽ tự động lấy thời điểm đó cập nhật vào updatedAt
     */
    @LastModifiedDate
    private Date updatedAt;
}
