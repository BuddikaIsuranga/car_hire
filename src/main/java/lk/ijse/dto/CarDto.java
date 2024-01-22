package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CarDto {
    private CategoryDto categoryDto;
    private String carId;
    private String carNumber;
    private String carBrand;
    private String carModel;
    private Long carYear;
    private Long carRate;
    private Boolean isCarAvailable;


}
