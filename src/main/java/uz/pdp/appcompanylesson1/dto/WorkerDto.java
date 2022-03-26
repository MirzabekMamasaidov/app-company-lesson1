package uz.pdp.appcompanylesson1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    private String name;

    private String phoneNumber;

    private String street;

    private String homeNumber;

    private Integer departmentId;
}
