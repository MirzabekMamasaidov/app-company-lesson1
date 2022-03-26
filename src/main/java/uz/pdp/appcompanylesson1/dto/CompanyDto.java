package uz.pdp.appcompanylesson1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotEmpty
    private String street;

    @NotBlank
    private String homeNumber;
}
