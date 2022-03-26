package uz.pdp.appcompanylesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompanylesson1.entity.Company;
import uz.pdp.appcompanylesson1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    boolean existsByName(String name);

}
