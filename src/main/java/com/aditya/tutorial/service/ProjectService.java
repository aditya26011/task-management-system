package com.aditya.tutorial.service;


import com.aditya.tutorial.dto.projectDtos.ProjectRequestDto;
import com.aditya.tutorial.dto.projectDtos.ProjectResponseDto;
import com.aditya.tutorial.dto.teamDtos.TeamSummaryDto;
import com.aditya.tutorial.entity.Enums.Status;
import com.aditya.tutorial.entity.Project;
import com.aditya.tutorial.entity.Team;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.repo.ProjectRepo;
import com.aditya.tutorial.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final TeamRepo teamRepo;
    private final ModelMapper modelMapper;

    private TeamSummaryDto mapToTeamSummaryResponseDto(Team team){
        TeamSummaryDto teamSummaryDto=new TeamSummaryDto();
        teamSummaryDto.setName(team.getName());
        teamSummaryDto.setTeamId(team.getId());
        return teamSummaryDto;
    }

    private ProjectResponseDto mapToProjectResponseDto(Project project){
        ProjectResponseDto projectResponseDto=new ProjectResponseDto();
        projectResponseDto.setStatus(project.getStatus());
        projectResponseDto.setName(project.getName());
        projectResponseDto.setId(project.getId());
        projectResponseDto.setEndDate(project.getEndDate());
        projectResponseDto.setStartDate(project.getStartDate());
        projectResponseDto.setCreated_at(project.getCreated_at());
        projectResponseDto.setDescription(project.getDescription());
        projectResponseDto.setTeam(mapToTeamSummaryResponseDto(project.getTeam()));

        return projectResponseDto;
    }

    public ProjectResponseDto createProject(ProjectRequestDto projectDto) {
        Long teamId = projectDto.getTeamId();
        if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Team with Id not found"));

        Project project = new Project();

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setStatus(Status.PLANNING);
        project.setTeam(team);

        Project savedProject = projectRepo.save(project);

        ProjectResponseDto projectResponseDto = modelMapper.map(savedProject, ProjectResponseDto.class);

        TeamSummaryDto teamSummaryDto = new TeamSummaryDto();
        teamSummaryDto.setTeamId(team.getId());
        teamSummaryDto.setName(team.getName());

        projectResponseDto.setTeam(teamSummaryDto);
        return projectResponseDto;
    }

    public List<ProjectResponseDto> getAllProjects() {
     List<Project> projectList = projectRepo.findAll();
    return projectList.stream().map(this::mapToProjectResponseDto).toList();

    }
}