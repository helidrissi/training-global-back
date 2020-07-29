package fr.mistral.saphir.test.trainigglobalback.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
@Data@AllArgsConstructor@NoArgsConstructor@ToString
public class UsersEntity implements Serializable {


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String encryptedPassword;


}
