package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
//@Table(name = "cars")

public class CarEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private CatergoryEntity category;

    @Id
    @Column(name = "id",nullable = false)
    private String carId;
    @Column(name = "car_number",nullable = false)
    private String carNumber;
    @Column(name = "car_brand",nullable = false)
    private String carBrand;
    @Column(name = "car_model",nullable = false)
    private String carModel;
    @Column(name = "car_year",nullable = false)
    private Long carYear;
    @Column(name = "car_rate",nullable = false)
    private Long carRate;

    private Boolean isCarAvailable = true;

    @OneToMany(mappedBy = "carEntity")
    @ToString.Exclude
    private List<RentEntity> rentList;

}
