package uz.pdp.appcompanylesson1.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.WorkerDto;
import uz.pdp.appcompanylesson1.entity.Worker;
import uz.pdp.appcompanylesson1.service.WorkerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody WorkerDto workerDto){
        ApiResponse response = workerService.add(workerDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Worker> workerList = workerService.getAll();
        return ResponseEntity.ok().body(workerList);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Worker> optionalWorker = workerService.getOne(id);
        return ResponseEntity.status(optionalWorker.isPresent()?
                HttpStatus.OK:HttpStatus.NOT_FOUND).body(optionalWorker.get());
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody WorkerDto workerDto){
        ApiResponse response = workerService.edit(id, workerDto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = workerService.delete(id);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}
