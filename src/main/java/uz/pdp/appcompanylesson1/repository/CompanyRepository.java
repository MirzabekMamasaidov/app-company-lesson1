package uz.pdp.appcompanylesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompanylesson1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String corpName);

}
