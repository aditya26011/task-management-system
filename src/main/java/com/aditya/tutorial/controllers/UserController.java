package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.AddTeamDto;
import com.aditya.tutorial.dto.UserDto;
import com.aditya.tutorial.dto.UserResponseDto;
import com.aditya.tutorial.dto.UserRoleRequestDto;
import com.aditya.tutorial.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/create")
//    public ResponseEntity<UserDto> createEmployee(@Valid  @RequestBody UserDto userDto){
//         UserDto user=userService.createEmployee(userDto);
//
//         return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<UserResponseDto> userResponseDto=userService.getAll();
        return new ResponseEntity<>(userResponseDto,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getEmpById(@PathVariable(value = "id") Long id){
        UserResponseDto user=userService.getEmpById(id);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
      int deleted= userService.deleteById(id);
      if(deleted==1)
          return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
      else
          return new ResponseEntity<>("failed to delete",HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> UpdateEmployee( @RequestBody UserDto userDto, @PathVariable(value = "id") Long id){
        UserResponseDto updatedEmployee =  userService.UpdateEmployee(userDto,id);
     return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }

    @PatchMapping("{id}/role")
    public ResponseEntity<UserResponseDto> updateEmployeeRole(@RequestBody UserRoleRequestDto userRoleRequestDto,
                                                              @PathVariable(value = "id") Long id){

        UserResponseDto updatedEmployeeData=userService.updateEmployeeRole(userRoleRequestDto,id);
        return new ResponseEntity<>(updatedEmployeeData,HttpStatus.OK);

    }

    @PatchMapping("/{id}/assign-team")
    public ResponseEntity<UserResponseDto> addEmpToTeam(@RequestBody AddTeamDto addTeamDto,
                                                        @PathVariable("id")Long id){

        UserResponseDto updatedUser=userService.addEmpToTeam(addTeamDto,id);
        return ResponseEntity.ok(updatedUser);

    }
}
