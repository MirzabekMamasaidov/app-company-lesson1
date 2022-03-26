package uz.pdp.appcompanylesson1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.DepartmentDto;
import uz.pdp.appcompanylesson1.entity.Department;
import uz.pdp.appcompanylesson1.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    final DepartmentService departmentService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody DepartmentDto departmentDto){

        ApiResponse response = departmentService.add(departmentDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Department> departmentList = departmentService.getAll();
        return ResponseEntity.ok().body(departmentList);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Department> optionalDepartment = departmentService.getOne(id);
        return ResponseEntity.status(optionalDepartment.isPresent()?
                HttpStatus.OK:HttpStatus.NOT_FOUND).body(optionalDepartment.get());
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        ApiResponse response = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = departmentService.delete(id);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


}
