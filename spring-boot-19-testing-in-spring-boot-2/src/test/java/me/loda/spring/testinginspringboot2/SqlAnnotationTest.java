package me.loda.spring.testinginspringboot2;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SqlAnnotationTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    @Sql("/createTodo.sql")
    public void testTodoRepositoryBySqlSchema() {
        Assertions.assertThat(todoRepository.findAll()).hasSize(2);
        Assertions.assertThat(todoRepository.findById(1).getTitle()).isEqualTo("Todo-1");
    }

}
