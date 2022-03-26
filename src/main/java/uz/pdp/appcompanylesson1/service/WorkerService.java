package uz.pdp.appcompanylesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompanylesson1.dto.ApiResponse;
import uz.pdp.appcompanylesson1.dto.WorkerDto;
import uz.pdp.appcompanylesson1.entity.Address;
import uz.pdp.appcompanylesson1.entity.Department;
import uz.pdp.appcompanylesson1.entity.Worker;
import uz.pdp.appcompanylesson1.repository.AddressRepository;
import uz.pdp.appcompanylesson1.repository.DepartmentRepository;
import uz.pdp.appcompanylesson1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public record WorkerService(WorkerRepository workerRepository, AddressRepository addressRepository,
                            DepartmentRepository departmentRepository) {

    public ApiResponse add(WorkerDto workerDto) {
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("There is such phone number", false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        addressRepository.save(address);
        worker.setAddress(address);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Not found department", false);
        }
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Added", true);
    }

    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    public Optional<Worker> getOne(Integer id) {
        return workerRepository.findById(id);
    }

    public ApiResponse edit(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new ApiResponse("Not found worker",false);
        }
        Worker editingWorker = optionalWorker.get();
        editingWorker.setName(workerDto.getName());
        editingWorker.setPhoneNumber(workerDto.getPhoneNumber());
        Address editingWorkerAddress = editingWorker.getAddress();
        editingWorkerAddress.setStreet(workerDto.getStreet());
        editingWorkerAddress.setHomeNumber(workerDto.getHomeNumber());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Not found department",false);
        }
        editingWorker.setDepartment(optionalDepartment.get());
        workerRepository.save(editingWorker);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new ApiResponse("Not found worker",false);
        }
        workerRepository.delete(optionalWorker.get());
        return new ApiResponse("Deleted",true);
    }
}
