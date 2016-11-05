package com.cmpe281.hw3.services;

import com.cmpe281.hw3.models.*;
import com.cmpe281.hw3.repository.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.function.*;

/**
 * Harish Kumar K V
 */
@Service
public class ProjectService {

    @Inject
    ProjectRepository projectRepository;

    public ProjectService() {}

    public ProjectList fetchAll () {
        Iterable<Project> employees = projectRepository.findAll ();
        final ProjectList projectList = new ProjectList ();
        employees.forEach (new Consumer<Project> () {
            @Override
            public void accept (Project employee) {
                projectList.getProjects ().add (employee);
            }
        });
        return projectList;
    }

    public Project fetch (Integer id) throws Exception {
        return projectExists (id);
    }

    public Project insert (Project project) throws Exception {
        if (projectRepository.findOne (project.getId ()) != null) {
            throw new Exception ();
        }
        return projectRepository.save (project);
    }

    public Project update (Integer id, Project project) throws Exception {
        projectExists (id);
        return projectRepository.save (project);
    }

    public void delete (Integer id) throws Exception {
        projectExists (id);
        projectRepository.delete (id);
    }

    private Project projectExists (Integer id) throws Exception {
        Project project = projectRepository.findOne (id);
        if (project == null) {
            throw new Exception ();
        }
        return project;
    }
}
