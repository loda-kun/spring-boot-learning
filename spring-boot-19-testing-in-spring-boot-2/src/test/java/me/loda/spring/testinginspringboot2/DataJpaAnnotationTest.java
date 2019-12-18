package me.loda.spring.testinginspringboot2;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
/**
 * Khi đánh dấu một class là @DataJpaTest.
 * Spring Boot sẽ load lên tất cả các Bean có liên quan tới tầng Repository
 * Thay vì load hết tất cả Bean như là @SpringBootTest
 */
@DataJpaTest
public class DataJpaAnnotationTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void allComponentAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(jdbcTemplate).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
        Assertions.assertThat(todoRepository).isNotNull();
    }

    @Test
    public void testTodoRepositoryByCode() {
        todoRepository.save(new Todo(0, "Todo-1", "Detail-1"));
        todoRepository.save(new Todo(0, "Todo-2", "Detail-2"));

        Assertions.assertThat(todoRepository.findAll()).hasSize(2);
        Assertions.assertThat(todoRepository.findById(1).getTitle()).isEqualTo("Todo-1");
    }

    @After
    public void clean() {
        todoRepository.deleteAll();
    }
}