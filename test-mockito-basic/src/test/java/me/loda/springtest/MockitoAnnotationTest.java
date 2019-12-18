package me.loda.springtest;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import lombok.Data;

/**
 * Để sử dụng Mockito Annotation, bạn cần kích hoạt nó theo 1 trong 3 cách sau
 */
// Cách 1
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {

    // Cách 2
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        // Cách 3
        // Nếu bạn không dùng cách 1 hoặc 2 thì phải sử dụng
        // dòng code dưới đây:
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Mock
    Calculator calculator;

    @Mock
    List<String> mockedList;

    @Test
    public void testUserMockFunction() {
        List mockList = Mockito.mock(List.class);
        when(mockList.size()).thenReturn(2);
        Assert.assertEquals(2, mockList.size());
    }

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");

        Mockito.verify(mockedList).add("one");
        Assert.assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        Assert.assertEquals(100, mockedList.size());
    }

    @Test
    public void testCalculator() {
        calculator.multiply(1,2);
        // Kiểm tra multiply(1,2) đã được gọi tới
        Mockito.verify(calculator).multiply(1,2);

        // Giả lập multiply(1,2) trả về 3
        Mockito.when(calculator.multiply(1,2)).thenReturn(3);

        Assert.assertEquals(3, calculator.multiply(1,2));
    }

    @Data
    public static class Calculator {
        public int multiply(int a, int b) {
            // giả sử có 1 đoạn code rất phức tạp chứa nhiều lời gọi dêpendencies ở đây
            return 0;
        }
    }
}