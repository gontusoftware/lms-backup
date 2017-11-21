package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Assignment;
import org.gontu.lms.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
@RestController
public class AssignmentRestController {
 
    @Autowired
    AssignmentService<Assignment> assignmentService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Assignments--------------------------------------------------------
     
    @RequestMapping(value = "/assignment", method = RequestMethod.GET)
    public ResponseEntity<Set<Assignment>> listAllAssignments() {
        Set<Assignment> assignments = new HashSet(assignmentService.getAll());
        if(assignments.isEmpty()){	
            return new ResponseEntity<Set<Assignment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Assignment>>(assignments, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Assignment--------------------------------------------------------
     
    @RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Assignment> getAssignment(@PathVariable("id") long id) {
        System.out.println("Fetching Assignment with id " + id);
        Assignment assignment = assignmentService.findById(id);
        if (assignment == null) {
            System.out.println("Assignment with id " + id + " not found");
            return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Assignment--------------------------------------------------------
     
    @RequestMapping(value = "/assignment", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createAssignment(@RequestBody Assignment assignment, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Assignment " + assignment.getName());
 
//        if (assignmentService.isExists(assignment)) {
//            System.out.println("A Assignment with name " + assignment.getAssignmentName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        assignmentService.save(assignment);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/assignment/{id}").buildAndExpand(assignment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Assignment --------------------------------------------------------
     
    @RequestMapping(value = "/assignment/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") long id, @RequestBody Assignment assignment) {
        System.out.println("Updating Assignment " + assignment);
         
        Assignment currentAssignment = assignmentService.findById(id);
         
        if (currentAssignment==null) {
            System.out.println("Assignment with id " + id + " not found");
            return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
        }
 
        assignment.setId(currentAssignment.getId());
        //update assignment here

         
        assignmentService.update(assignment);
        return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
    }
 
    //------------------- Delete a Assignment --------------------------------------------------------
     
    @RequestMapping(value = "/assignment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Assignment> deleteAssignment(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Assignment with id " + id);
 
        Assignment assignment = assignmentService.findById(id);
        if (assignment == null) {
            System.out.println("Unable to delete. Assignment with id " + id + " not found");
            return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
        }
 
        assignmentService.delete(id);
        return new ResponseEntity<Assignment>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Assignments --------------------------------------------------------
     
    @RequestMapping(value = "/assignment", method = RequestMethod.DELETE)
    public ResponseEntity<Assignment> deleteAllAssignments() {
        System.out.println("Deleting All Assignments");
 
        assignmentService.deleteAll();
        return new ResponseEntity<Assignment>(HttpStatus.NO_CONTENT);
    }
 
}