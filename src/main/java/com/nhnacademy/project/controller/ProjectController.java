package com.nhnacademy.project.controller;


import com.nhnacademy.project.service.ProjectService;
import com.nhnacademy.project.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;
    @GetMapping
    public List<Project> getAllProjects()
    {
        return service.getAllProjects();
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Integer projectId) {
        return service.getProject(projectId);
    }

    @PostMapping
    public void createProject(@RequestBody Project project) {
        service.createProject(project);
    }

    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Integer projectId, @RequestBody Project project) {
        service.updateProject(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Integer projectId) {
        service.deleteProject(projectId);
    }
}
