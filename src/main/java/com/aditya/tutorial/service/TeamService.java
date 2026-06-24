package com.aditya.tutorial.service;

import com.aditya.tutorial.dto.TeamResponseDto;
import com.aditya.tutorial.entity.Team;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final ModelMapper modelMapper;
    private final TeamRepo teamRepo;

    public TeamResponseDto createTeam(TeamResponseDto teamResponseDto) {

        Team team=modelMapper.map(teamResponseDto,Team.class);
        Team savedTeam = teamRepo.save(team);
        return modelMapper.map(savedTeam, TeamResponseDto.class);

    }

    public List<TeamResponseDto> getAllTeams() {
        List<Team> listOfTeams = teamRepo.findAll();
        return listOfTeams.stream().map(team -> modelMapper.map(team, TeamResponseDto.class)).collect(Collectors.toList());
    }

    public boolean deleteTeam(Long id) {
        boolean isPresent = teamRepo.existsById(id);
        if(isPresent){
            teamRepo.deleteById(id);
            return true;
        }
        else {
            throw new ResourceNotFoundException("Team with Id not found:"+id);
        }
    }

    public TeamResponseDto getTeamById(Long id) {
        Team team = teamRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team with Id not found"));
        return modelMapper.map(team, TeamResponseDto.class);
    }

    public TeamResponseDto patchTeam(Long id, TeamResponseDto teamResponseDto) {
        Team team = teamRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team with Id not found"));

        if(teamResponseDto.getName()!=null){
            team.setName(teamResponseDto.getName());
        }

        if(teamResponseDto.getDescription()!=null){
            team.setDescription(teamResponseDto.getDescription());
        }
        team.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        Team saved = teamRepo.save(team);
        return modelMapper.map(saved, TeamResponseDto.class);
    }
}
