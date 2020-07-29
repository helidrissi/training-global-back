package fr.mistral.saphir.test.trainigglobalback.services;


import fr.mistral.saphir.test.trainigglobalback.dao.UsersRepository;
import fr.mistral.saphir.test.trainigglobalback.dto.UserDto;
import fr.mistral.saphir.test.trainigglobalback.model.UsersEntity;
import fr.mistral.saphir.test.trainigglobalback.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    Utils util;

    public UserDto createUser(UserDto userDto) {
          UsersEntity checkUser=usersRepository.findByEmail(userDto.getEmail());
        if (checkUser != null) throw new RuntimeException("Cet Utilisateur existe dèja !");
        ModelMapper modelMapper = new ModelMapper();

        UsersEntity userEntity = modelMapper.map(userDto, UsersEntity.class);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(util.generateStringId(32));

        UsersEntity newUser = usersRepository.save(userEntity);

        UserDto usersDto = modelMapper.map(newUser, UserDto.class);

        return usersDto;
            }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersEntity usersEntity = usersRepository.findByEmail(email);

        if (usersEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(usersEntity.getEmail(), usersEntity.getEncryptedPassword(), new ArrayList<>());
    }

    public UserDto getUser(String email) {

        UsersEntity userEntity = usersRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }
}
