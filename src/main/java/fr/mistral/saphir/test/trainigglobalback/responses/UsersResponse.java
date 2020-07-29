package fr.mistral.saphir.test.trainigglobalback.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UsersResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
