package me.loda.springtest;

import java.sql.SQLException;
import java.util.Arrays;
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
     * Inject driver vào userService.
     * Mọi người còn nhớ Spring Inject thế nào chứ?
     */
    @InjectMocks
    IntegerService userService;

    @Test(expected = SQLException.class)
    public void testInjectMock() throws SQLException {
        Mockito.doReturn(Arrays.asList(1, 2, 3)).when(driver).get();

        Assert.assertEquals(driver, userService.getDriver());
        Assert.assertEquals(Arrays.asList(1, 2, 3), userService.getIntegers());

        Mockito.when(driver.get()).thenThrow(SQLException.class);
        userService.getIntegers();
    }

    public interface DatabaseDriver {
        List<Object> get() throws SQLException;
    }

    @Data
    @AllArgsConstructor
    public static class IntegerService {
        DatabaseDriver driver;

        public List<Object> getIntegers() throws SQLException{
            System.out.println("LOG: Getting users");
            List<Object> list = driver.get();
            System.out.println("LOG: Done");
            return list;
        }
    }
}
