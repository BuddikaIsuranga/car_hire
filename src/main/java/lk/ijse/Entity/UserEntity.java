package lk.ijse.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "user")


public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "first_name", nullable = false)
    private String fName;
    @Column(name = "last_name", nullable = false)
    private String lName;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "password", nullable = false)
    private String password;

}
