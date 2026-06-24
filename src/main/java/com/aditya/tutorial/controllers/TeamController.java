package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.TeamResponseDto;
import com.aditya.tutorial.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;


    @PostMapping()
    public ResponseEntity<TeamResponseDto> createTeam(@RequestBody TeamResponseDto teamResponseDto){
        TeamResponseDto teamResponseDto1=teamService.createTeam(teamResponseDto);
        return ResponseEntity.ok(teamResponseDto1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeamResponseDto>> getAllTeams(){
        List<TeamResponseDto> teamResponseDtoList=teamService.getAllTeams();
        return ResponseEntity.ok(teamResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable(value = "id")Long id){
        TeamResponseDto teamResponseDto=teamService.getTeamById(id);
        return ResponseEntity.ok(teamResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable(value = "id") Long id){
        boolean isDeleted =teamService.deleteTeam(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamResponseDto> patchTeam(@PathVariable(value = "id") Long id,
                                                     @RequestBody TeamResponseDto teamResponseDto){

            TeamResponseDto updatedTeam=teamService.patchTeam(id,teamResponseDto);
            return ResponseEntity.ok(updatedTeam);
    }
}
