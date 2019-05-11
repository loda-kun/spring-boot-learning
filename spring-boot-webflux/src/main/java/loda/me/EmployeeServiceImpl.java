package loda.me;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Flux<Employee> findAll() {
        return Flux.just(new Employee("29750","AtomPtit") , new Employee("18273", "HungCD"));
    }
}
