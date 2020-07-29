package fr.mistral.saphir.test.trainigglobalback.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UsersRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
