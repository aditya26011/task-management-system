package com.aditya.tutorial.service;

import com.aditya.tutorial.dto.UserDto;
import com.aditya.tutorial.entity.User;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.exceptions.UserAlreadyExistsException;
import com.aditya.tutorial.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    public UserDto createEmployee(UserDto userDto) {
       String email= userDto.getEmail();
    User userWithEmail= userRepo.findByEmail(email);
      if(userWithEmail!=null){
          throw new UserAlreadyExistsException("User with same email exists");
      }
      else {
          User user = modelMapper.map(userDto, User.class);
          User savedUser = userRepo.save(user);

          return modelMapper.map(savedUser, UserDto.class);
      }

    }

    public List<UserDto> getAll() {

     List<User> users=userRepo.findAll();

     return users.stream().map((element) -> modelMapper.map(element, UserDto.class)).collect(Collectors.toList());

    }

    public UserDto getEmpById(Long id) {
        User employee = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        return modelMapper.map(employee, UserDto.class);

    }

    public int deleteById(Long id) {
        boolean val = userRepo.existsById(id);
        if(val){
            userRepo.deleteById(id);
            return 1;
        }else{
            return -1;
        }

    }
}
