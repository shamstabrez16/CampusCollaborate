package com.campuscollaborate.service;

import com.campuscollaborate.entity.ProjectEntity;
import com.campuscollaborate.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

     @Autowired
     private ProjectRepository projectRepository;
    public List<ProjectEntity> getAllProjects() {
        return  projectRepository.findAll();
    }

    public Optional<ProjectEntity> getProjectById(int projectId) {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);
        if (((Optional<?>) project).isPresent()) {
            return project;
        } else {
           return Optional.empty();
        }
    }

    public ProjectEntity createProject(ProjectEntity project) {
        project=   projectRepository.save(project);
        return project;
    }

    public ResponseEntity<ProjectEntity> updateProject(int projectId, ProjectEntity projectDetails) {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            ProjectEntity updatedProject = project.get();
            updatedProject.setProjectName(projectDetails.getProjectName());
            updatedProject.setProjectDescription(projectDetails.getProjectDescription());
            // updatedProject.setUser(projectDetails.getUserId());
            projectRepository.save(updatedProject);
            return ResponseEntity.ok().body(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteProject(int projectId) {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
