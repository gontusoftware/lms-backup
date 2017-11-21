package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.MCQ;
import org.gontu.lms.service.MCQService;
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
public class MCQRestController {
 
    @Autowired
    MCQService<MCQ> mcqService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All MCQs--------------------------------------------------------
     
    @RequestMapping(value = "/mcq", method = RequestMethod.GET)
    public ResponseEntity<Set<MCQ>> listAllMCQs() {
        Set<MCQ> mcqs = new HashSet(mcqService.getAll());
        if(mcqs.isEmpty()){	
            return new ResponseEntity<Set<MCQ>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<MCQ>>(mcqs, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single MCQ--------------------------------------------------------
     
    @RequestMapping(value = "/mcq/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MCQ> getMCQ(@PathVariable("id") long id) {
        System.out.println("Fetching MCQ with id " + id);
        MCQ mcq = mcqService.findById(id);
        if (mcq == null) {
            System.out.println("MCQ with id " + id + " not found");
            return new ResponseEntity<MCQ>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MCQ>(mcq, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a MCQ--------------------------------------------------------
     
    @RequestMapping(value = "/mcq", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createMCQ(@RequestBody MCQ mcq, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating MCQ " + mcq.getQuestion());
 
//        if (mcqService.isExists(mcq)) {
//            System.out.println("A MCQ with name " + mcq.getMCQName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        mcqService.save(mcq);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mcq/{id}").buildAndExpand(mcq.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a MCQ --------------------------------------------------------
     
    @RequestMapping(value = "/mcq/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MCQ> updateMCQ(@PathVariable("id") long id, @RequestBody MCQ mcq) {
        System.out.println("Updating MCQ " + mcq);
         
        MCQ currentMCQ = mcqService.findById(id);
         
        if (currentMCQ==null) {
            System.out.println("MCQ with id " + id + " not found");
            return new ResponseEntity<MCQ>(HttpStatus.NOT_FOUND);
        }
 
        mcq.setId(currentMCQ.getId());
        //update mcq here

         
        mcqService.update(mcq);
        return new ResponseEntity<MCQ>(mcq, HttpStatus.OK);
    }
 
    //------------------- Delete a MCQ --------------------------------------------------------
     
    @RequestMapping(value = "/mcq/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MCQ> deleteMCQ(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting MCQ with id " + id);
 
        MCQ mcq = mcqService.findById(id);
        if (mcq == null) {
            System.out.println("Unable to delete. MCQ with id " + id + " not found");
            return new ResponseEntity<MCQ>(HttpStatus.NOT_FOUND);
        }
 
        mcqService.delete(id);
        return new ResponseEntity<MCQ>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All MCQs --------------------------------------------------------
     
    @RequestMapping(value = "/mcq", method = RequestMethod.DELETE)
    public ResponseEntity<MCQ> deleteAllMCQs() {
        System.out.println("Deleting All MCQs");
 
        mcqService.deleteAll();
        return new ResponseEntity<MCQ>(HttpStatus.NO_CONTENT);
    }
 
}