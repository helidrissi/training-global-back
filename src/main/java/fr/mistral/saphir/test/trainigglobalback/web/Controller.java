package fr.mistral.saphir.test.trainigglobalback.web;



import fr.mistral.saphir.test.trainigglobalback.dao.UsersRepository;
import fr.mistral.saphir.test.trainigglobalback.dto.UserDto;
import fr.mistral.saphir.test.trainigglobalback.model.UsersEntity;
import fr.mistral.saphir.test.trainigglobalback.requests.UsersRequest;
import fr.mistral.saphir.test.trainigglobalback.responses.UsersResponse;
import fr.mistral.saphir.test.trainigglobalback.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {


@Autowired
UsersRepository usersRepository;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UsersResponse> CreateUser(@RequestBody UsersRequest users) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(users, UserDto.class);


        UserDto createUser = userService.createUser(userDto);

        UsersResponse userResponse = modelMapper.map(createUser, UsersResponse.class);


        return new ResponseEntity<UsersResponse>(userResponse, HttpStatus.CREATED);


    }

    @GetMapping
    public HttpEntity<List<UsersEntity>> getAllEmployees() {

         
        List<UsersEntity> list = usersRepository.findAll();

        return new ResponseEntity<List<UsersEntity>>(list, new HttpHeaders(), HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam Long id){

        usersRepository.deleteById(id);

        return "Ok";

    }
}
