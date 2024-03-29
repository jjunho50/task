package com.nhnacademy.project.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.project.task.TaskApplication;
import com.nhnacademy.project.task.entity.Project;
import com.nhnacademy.project.task.repository.ProjectRepository;
import com.nhnacademy.project.task.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = TaskApplication.class)
@AutoConfigureMockMvc
class ProjectControllerTest {

    @MockBean
    ProjectService projectService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void projectRepository() throws Exception {

        given(projectService.getAllProjects())
                .willReturn(List.of(new Project(1, "testProject", "test상태")));

        mockMvc.perform(get("/project"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].projectName", equalTo("testProject"))
                );
    }

    @Test
    void getProject() throws Exception{
        given(projectService.getProject(2))
                .willReturn(new Project(2, "testProject", "test상태"));

        mockMvc.perform(get("/project/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectState", equalTo("test상태"))
                );
    }

    @Test
    void createProject() throws Exception{

        Project project = new Project(3, "project", "진행중");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(
                        post("/project")
                                .content(objectMapper.writeValueAsString(project))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void updateProject() throws Exception {
        Project updatedProject = new Project(1, "Updated testProject", "Updated test상태");
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedProjectJson = objectMapper.writeValueAsString(updatedProject);

        mockMvc.perform(
                        put("/project/{projectId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedProjectJson)
                )
                .andExpect(status().isOk());

    }

    @Test
    void deleteProject() throws Exception {
        mockMvc.perform(
                        delete("/project/{projectId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

}