package com.cmpe281.hw3.controllers;

import com.cmpe281.hw3.models.*;
import com.cmpe281.hw3.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.inject.*;

@RestController
@RequestMapping( "cmpe281harishkumarkv013/rest" )
public class ProjectController {

    @Inject
    ProjectService projectService;

/*
    @PostConstruct
    public void init()  {
        System.out.println("Inside Init Method");
       // projectService = new ProjectService ();
    }
*/

    @RequestMapping(
            value = "/project",
            method = RequestMethod.GET,
            headers = "Accept=*/*",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> getAllProjects()   {
        System.out.println("Fetching");
        try {
            ProjectList projectList = projectService.fetchAll ();
            return new ResponseEntity<> (projectList, HttpStatus.OK);
        }catch(Exception e)   {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/project/{id}",
            method = RequestMethod.GET,
           headers = "Accept=*/*",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> getProject(@PathVariable(value="id") Integer id)   {
        System.out.println("ID = " + id);
        try {
            Project project = projectService.fetch(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/project",
            method = RequestMethod.POST,
           headers = "Accept=*/*",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> createProject(@RequestBody Project project)    {
        try {
            Project result = projectService.insert (project);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getId()).toUri());
            return new ResponseEntity<> (result, httpHeaders, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(
            value = "/project/{id}",
            method = RequestMethod.PUT,
           headers = "Accept=*/*",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<?> updateProject(@PathVariable(value="id") Integer id,
                               @RequestBody Project project)    {

        System.out.println(project.getId ());
        System.out.println(project.getName ());
        System.out.println(project.getBudget ());
        try {
            project  = projectService.update (id, project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/project/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<?> deleteProject(@PathVariable(value="id") Integer id)    {
        System.out.println("ID = " + id);
        try {
            projectService.delete (id);
            return new ResponseEntity<> (HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }
}
