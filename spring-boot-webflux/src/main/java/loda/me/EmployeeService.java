package loda.me;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<Employee> findAll();
}
