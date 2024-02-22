package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.repository.ProjectMemberRepository;
import com.nhnacademy.project.service.ProjectMemberService;
import com.nhnacademy.project.entity.ProjectMember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberRepository repository;

    @Override
    public List<ProjectMember> getAllProjectMembers() {
        return repository.findAll();
    }

    @Override
    public ProjectMember getProjectMember(ProjectMember.Pk pk) {
        return repository.findById(pk).orElse(null);
    }

    @Override
    public void createProjectMember(ProjectMember projectMember) {
        repository.save(projectMember);
    }

    @Override
    public void deleteProjectMember(ProjectMember.Pk pk) {
        repository.deleteById(pk);
    }

    @Override
    public void updateProjectMember(ProjectMember projectMember) {
        if (repository.existsById(projectMember.getPk())) {
            repository.save(projectMember);
        }
    }
}