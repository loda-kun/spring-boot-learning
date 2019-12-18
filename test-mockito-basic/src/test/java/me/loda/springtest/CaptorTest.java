package me.loda.springtest;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CaptorTest {
    @Mock
    List<Object> list;

    @Captor
    ArgumentCaptor<Object> captor;

    @Test
    public void testCaptor1() {
        list.add(1);
        // Capture 2 lần gọi add có giá trị như thế nào
        Mockito.verify(list).add(captor.capture());

        System.out.println(captor.getAllValues());

        Assert.assertEquals(1, captor.getValue());
    }

    @Test
    public void testCaptor2() {
        list.add(1);
        list.add("String");
        // Capture 2 lần gọi add có giá trị như thế nào
        Mockito.verify(list, Mockito.times(2)).add(captor.capture());

        System.out.println(captor.getAllValues());

        Assert.assertEquals(Arrays.asList(1, "String"), captor.getAllValues());
        Assert.assertEquals("String", captor.getValue());
    }

}
