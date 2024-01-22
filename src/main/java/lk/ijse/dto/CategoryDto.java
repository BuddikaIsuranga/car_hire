package lk.ijse.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CategoryDto {

    @ToString.Exclude
    List<CarDto> carList;
    private String Categoryid;
    private String Categoryname;
    public CategoryDto(String Categoryid, String Categoryname) {
        this.Categoryid = Categoryid;
        this.Categoryname = Categoryname;
    }
}
