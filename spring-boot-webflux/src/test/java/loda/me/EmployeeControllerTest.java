package loda.me;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
public class EmployeeControllerTest {

    @Test
    public void TestGetAllEmpployees() {
        WebClient client = WebClient.builder().baseUrl("localhost:8080").build();
//        Flux<Employee> employees =
                client.get().uri("/employees")
                .exchange()
                .doOnSuccess(clientResponse -> {
                    clientResponse.bodyToFlux(Employee.class).subscribe(employee -> System.out.println("$$$$$$$$$$$$$"));
                })
                .doOnError(throwable -> throwable.printStackTrace());
//        employees.subscribe(employee -> System.out.println("ok"));
    }
}