package uz.pdp.appcompanylesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompanylesson1.entity.Company;
import uz.pdp.appcompanylesson1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

}
