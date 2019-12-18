package me.loda.springtest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import lombok.AllArgsConstructor;
import lombok.Data;

@RunWith(MockitoJUnitRunner.class)
public class InjectMockTest {
    @Mock
    DatabaseDriver driver;

    /**
     * Inject driver vào superService.
     * Mọi người còn nhớ Spring Inject thế nào chứ?
     */
    @InjectMocks
    SuperService superService;

    @Test(expected = SQLException.class)
    public void testInjectMock() throws SQLException {
        // Giả lập cho driver luôn trả về list (3,2,1) khi được gọi tới
        Mockito.doReturn(Arrays.asList(3, 2, 1)).when(driver).get();

        Assert.assertEquals(driver, superService.getDriver());

        // Test xem superService trả ra ngoài giá trị đúng không
        Assert.assertEquals(Arrays.asList(1, 2, 3), superService.getObjects());

        // Giả lập cho driver bắn exception
        Mockito.when(driver.get()).thenThrow(SQLException.class);
        superService.getObjects();
    }

    public interface DatabaseDriver {
        List<Object> get() throws SQLException;
    }

    @Data
    @AllArgsConstructor
    public static class SuperService {
        DatabaseDriver driver;

        public List<Object> getObjects() throws SQLException {
            System.out.println("LOG: Getting objects");
            List<Object> list = driver.get();

            System.out.println("LOG: Sorting");
            Collections.sort(list, Comparator.comparingInt(value -> Integer.valueOf(value.toString())));

            System.out.println("LOG: Done");
            return list;
        }
    }
}
