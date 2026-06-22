package com.aditya.tutorial.service;

import com.aditya.tutorial.dto.UserDto;
import com.aditya.tutorial.dto.UserResponseDto;
import com.aditya.tutorial.dto.UserRoleRequestDto;
import com.aditya.tutorial.entity.Enums.Roles;
import com.aditya.tutorial.entity.User;
import com.aditya.tutorial.exceptions.AdminRoleException;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.exceptions.UserAlreadyExistsException;
import com.aditya.tutorial.repo.UserRepo;
import jakarta.validation.Valid;
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


//    public UserDto createEmployee(UserDto userDto) {
//       String email= userDto.getEmail();
//   Optional<User> sameEmail= userRepo.findByEmail(email);
//            if(sameEmail.isPresent()){
//                throw new UserAlreadyExistsException("user with same email exists");
//            }
//            else{
//                User user = modelMapper.map(userDto, User.class);
//                User savedUser = userRepo.save(user);
//
//                return modelMapper.map(savedUser, UserDto.class);
//            }
//
//
//
//
//    }

    public List<UserResponseDto> getAll() {

     List<User> users=userRepo.findAll();

     return users.stream().map((element) -> modelMapper.map(element, UserResponseDto.class)).collect(Collectors.toList());

    }

    public UserResponseDto getEmpById(Long id) {
        User employee = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        return modelMapper.map(employee, UserResponseDto.class);

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

    public UserResponseDto UpdateEmployee( UserDto userDto,Long id) {
     User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with Id not found"));

       user.setName(userDto.getName());

     User updatedUser = userRepo.save(user);
     return modelMapper.map(updatedUser,UserResponseDto.class);


    }

    public UserResponseDto updateEmployeeRole(UserRoleRequestDto userRoleRequestDto, Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with Id now found"));

        if(user.getRole()!= Roles.ADMIN){
            user.setRole(userRoleRequestDto.getRole());
        }else{
            throw new AdminRoleException("Admin role cannot be changed");
        }
        User save = userRepo.save(user);
        return  modelMapper.map(save,UserResponseDto.class);
    }
}
