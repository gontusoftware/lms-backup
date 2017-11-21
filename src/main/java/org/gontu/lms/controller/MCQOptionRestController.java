package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.MCQOption;
import org.gontu.lms.service.MCQOptionService;
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
public class MCQOptionRestController {
 
    @Autowired
    MCQOptionService<MCQOption> mcqoptionService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All MCQOptions--------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption", method = RequestMethod.GET)
    public ResponseEntity<Set<MCQOption>> listAllMCQOptions() {
        Set<MCQOption> mcqoptions = new HashSet(mcqoptionService.getAll());
        if(mcqoptions.isEmpty()){	
            return new ResponseEntity<Set<MCQOption>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<MCQOption>>(mcqoptions, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single MCQOption--------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MCQOption> getMCQOption(@PathVariable("id") long id) {
        System.out.println("Fetching MCQOption with id " + id);
        MCQOption mcqoption = mcqoptionService.findById(id);
        if (mcqoption == null) {
            System.out.println("MCQOption with id " + id + " not found");
            return new ResponseEntity<MCQOption>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MCQOption>(mcqoption, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a MCQOption--------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createMCQOption(@RequestBody MCQOption mcqoption, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating MCQOption " + mcqoption.getOptn());
 
//        if (mcqoptionService.isExists(mcqoption)) {
//            System.out.println("A MCQOption with name " + mcqoption.getMCQOptionName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        mcqoptionService.save(mcqoption);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mcqoption/{id}").buildAndExpand(mcqoption.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a MCQOption --------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MCQOption> updateMCQOption(@PathVariable("id") long id, @RequestBody MCQOption mcqoption) {
        System.out.println("Updating MCQOption " + mcqoption);
         
        MCQOption currentMCQOption = mcqoptionService.findById(id);
         
        if (currentMCQOption==null) {
            System.out.println("MCQOption with id " + id + " not found");
            return new ResponseEntity<MCQOption>(HttpStatus.NOT_FOUND);
        }
 
        mcqoption.setId(currentMCQOption.getId());
        //update mcqoption here

         
        mcqoptionService.update(mcqoption);
        return new ResponseEntity<MCQOption>(mcqoption, HttpStatus.OK);
    }
 
    //------------------- Delete a MCQOption --------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MCQOption> deleteMCQOption(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting MCQOption with id " + id);
 
        MCQOption mcqoption = mcqoptionService.findById(id);
        if (mcqoption == null) {
            System.out.println("Unable to delete. MCQOption with id " + id + " not found");
            return new ResponseEntity<MCQOption>(HttpStatus.NOT_FOUND);
        }
 
        mcqoptionService.delete(id);
        return new ResponseEntity<MCQOption>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All MCQOptions --------------------------------------------------------
     
    @RequestMapping(value = "/mcqoption", method = RequestMethod.DELETE)
    public ResponseEntity<MCQOption> deleteAllMCQOptions() {
        System.out.println("Deleting All MCQOptions");
 
        mcqoptionService.deleteAll();
        return new ResponseEntity<MCQOption>(HttpStatus.NO_CONTENT);
    }
 
}