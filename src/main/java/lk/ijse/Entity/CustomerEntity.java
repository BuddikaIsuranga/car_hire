package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
//@Table(name = "customer")

public class CustomerEntity {

    @Id
    private String id;
    @Column(name = "cust_id", nullable = false)
    private String custId;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "first_name", nullable = false)
    private String fName;
    @Column(name = "last_name", nullable = false)
    private String lName;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "district", nullable = false)
    private String distriict;
    @Column(name = "post_code", nullable = false)
    private String postCode;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "contact", nullable = false)
    private String contact;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customerEntity", fetch = FetchType.LAZY)
    @ToString.Exclude
    List<RentEntity> rentEntityList = new ArrayList<>();



}

