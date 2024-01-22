package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity


public class RentEntity {

    @Id
    private String rentId;
    private Date startDate;
    private Date endDate;
    private Double payment;
    private Double total;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarEntity carEntity;
}
