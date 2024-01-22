package lk.ijse.dto;

import lk.ijse.Entity.CustomerEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDto {
    private String customerId;
   private String userName;
   private String fName;
   private String lName;
   private String street;
   private String city;
   private String district;
   private String postCode;
   private String email;
   private String contact;

   @ToString.Exclude
   List<RentDto> rentDtosList;



}
