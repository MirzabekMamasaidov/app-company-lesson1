package uz.pdp.appcompanylesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompanylesson1.entity.Address;
import uz.pdp.appcompanylesson1.entity.Company;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
