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

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final TeamRepo teamRepo;
    private final ModelMapper modelMapper;

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
}