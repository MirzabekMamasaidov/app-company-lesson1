package uz.pdp.appcompanylesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.DepartmentDto;
import uz.pdp.appcompanylesson1.entity.Company;
import uz.pdp.appcompanylesson1.entity.Department;
import uz.pdp.appcompanylesson1.repository.CompanyRepository;
import uz.pdp.appcompanylesson1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public record DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {

    public ApiResponse add(DepartmentDto departmentDto) {
        boolean byName = departmentRepository.existsByName(departmentDto.getName());
        if (byName) {
            return new ApiResponse("There is such department", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Not found company", false);
        }
        Company company = optionalCompany.get();
        department.setCompany(company);
        departmentRepository.save(department);
        return new ApiResponse("Added", true);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getOne(Integer id) {
       return departmentRepository.findById(id);
    }

    public ApiResponse edit(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Not found department",false);
        }
        Department editingDepartment = optionalDepartment.get();
        editingDepartment.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Not found company",false);
        }
        editingDepartment.setCompany(optionalCompany.get());
        departmentRepository.save(editingDepartment);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Not found department",false);
        }
        departmentRepository.delete(optionalDepartment.get());
        return  new ApiResponse("Deleted",true);
    }
}
