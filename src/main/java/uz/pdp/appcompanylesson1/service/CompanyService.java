package uz.pdp.appcompanylesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.CompanyDto;
import uz.pdp.appcompanylesson1.entity.Address;
import uz.pdp.appcompanylesson1.entity.Company;
import uz.pdp.appcompanylesson1.repository.AddressRepository;
import uz.pdp.appcompanylesson1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public record CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {

    public ApiResponse add(CompanyDto companyDto) {
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName) {
            return new ApiResponse("There is such corp name",false);
        }
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address save = addressRepository.save(address);
        company.setAddress(save);
        companyRepository.save(company);
        return new ApiResponse("Added",true);
    }

    public List<Company> getAll() {
       return companyRepository.findAll();
    }

    public Company getOne(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return null;
        }
        return optionalCompany.get();
    }

    public ApiResponse edit(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Not found company",false);
        }
        Company editingCompany = optionalCompany.get();
        editingCompany.setCorpName(companyDto.getCorpName());
        editingCompany.setDirectorName(companyDto.getDirectorName());
        Address editingCompanyAddress = editingCompany.getAddress();
        editingCompanyAddress.setStreet(companyDto.getStreet());
        editingCompanyAddress.setHomeNumber(companyDto.getHomeNumber());
        companyRepository.save(editingCompany);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Not found company",false);
        }
        Company company = optionalCompany.get();
        companyRepository.delete(company);
        return new ApiResponse("Deleted",true);
    }
}
