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


public class CatergoryEntity {

    @OneToMany(mappedBy = "category",targetEntity = CarEntity.class,cascade = CascadeType.REMOVE)
    List<CarEntity> carList;
    @Id
    private String Categoryid;
    @Column(nullable = false)
    private String Categoryname;

    public CatergoryEntity( String  Categoryid,String Categoryname) {
        this.Categoryid = Categoryid;
        this.Categoryname = Categoryname;
    }
}



