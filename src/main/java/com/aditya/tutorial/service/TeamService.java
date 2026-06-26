package com.aditya.tutorial.service;

import com.aditya.tutorial.dto.teamDtos.TeamMemberDto;
import com.aditya.tutorial.dto.teamDtos.TeamResponseDto;
import com.aditya.tutorial.entity.Team;
import com.aditya.tutorial.exceptions.InvalidRequestException;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
        List<Team> teams = teamRepo.findAll();

        return teams.stream()
                .map(team -> {

                    TeamResponseDto teamResponseDto = modelMapper.map(team, TeamResponseDto.class);

                    List<TeamMemberDto> members = team.getUserList()
                            .stream()
                            .map(user -> modelMapper.map(user, TeamMemberDto.class))
                            .collect(Collectors.toList());

                    teamResponseDto.setUsers(members);

                    return teamResponseDto;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteTeam(Long id) {
    Team team = teamRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team with Id not found"));

        if(!team.getUserList().isEmpty()){
            throw new InvalidRequestException("Team has members can't be deleted");
        }
        else {
            teamRepo.deleteById(id);
            return true;
        }
    }

    public TeamResponseDto getTeamById(Long id) {
        Team team = teamRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team with Id not found"));

        List<TeamMemberDto> userList=team.getUserList()
                .stream()
                .map(user -> modelMapper.map(user, TeamMemberDto.class))
                .toList();

        TeamResponseDto response = modelMapper.map(team, TeamResponseDto.class);
        response.setUsers(userList);
        return  response;
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
