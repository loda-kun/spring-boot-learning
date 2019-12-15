package me.loda.hibernate.customvalidation;

import lombok.Data;

@Data
public class User {
    // Đánh dấu field lodaId sẽ cần validate bởi @LodaId
    @LodaId
    private String lodaId;
}
