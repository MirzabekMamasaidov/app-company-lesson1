package uz.pdp.appcompanylesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.CompanyDto;
import uz.pdp.appcompanylesson1.entity.Company;
import uz.pdp.appcompanylesson1.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    final
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CompanyDto companyDto){
        ApiResponse response = companyService.add(companyDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Company> all = companyService.getAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Company company = companyService.getOne(id);
        return ResponseEntity.status(company==null?
                HttpStatus.NOT_FOUND:HttpStatus.OK).body(company);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        ApiResponse response = companyService.edit(id, companyDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = companyService.delete(id);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

}
