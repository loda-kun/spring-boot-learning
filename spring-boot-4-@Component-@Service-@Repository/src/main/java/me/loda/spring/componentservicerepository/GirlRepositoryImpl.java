package me.loda.spring.componentservicerepository;

import org.springframework.stereotype.Repository;

@Repository
public class GirlRepositoryImpl implements GirlRepository {

    @Override
    public Girl getGirlByName(String name) {
        // Ở đây tôi ví dụ là database đã trả về
        // một cô gái với tên đúng như tham số
        // Để làm ví dụ.
        // Còn thực tế phải query trong csđl nhé.
        return new Girl(name);
    }
}
