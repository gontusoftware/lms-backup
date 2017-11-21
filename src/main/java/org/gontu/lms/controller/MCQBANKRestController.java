package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.MCQBANK;
import org.gontu.lms.service.MCQBANKService;
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
public class MCQBANKRestController {
 
    @Autowired
    MCQBANKService<MCQBANK> mcqbankService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All MCQBANKs--------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank", method = RequestMethod.GET)
    public ResponseEntity<Set<MCQBANK>> listAllMCQBANKs() {
        Set<MCQBANK> mcqbanks = new HashSet(mcqbankService.getAll());
        if(mcqbanks.isEmpty()){	
            return new ResponseEntity<Set<MCQBANK>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<MCQBANK>>(mcqbanks, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single MCQBANK--------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MCQBANK> getMCQBANK(@PathVariable("id") long id) {
        System.out.println("Fetching MCQBANK with id " + id);
        MCQBANK mcqbank = mcqbankService.findById(id);
        if (mcqbank == null) {
            System.out.println("MCQBANK with id " + id + " not found");
            return new ResponseEntity<MCQBANK>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MCQBANK>(mcqbank, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a MCQBANK--------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createMCQBANK(@RequestBody MCQBANK mcqbank, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating MCQBANK " + mcqbank.getName());
 
//        if (mcqbankService.isExists(mcqbank)) {
//            System.out.println("A MCQBANK with name " + mcqbank.getMCQBANKName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        mcqbankService.save(mcqbank);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mcqbank/{id}").buildAndExpand(mcqbank.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a MCQBANK --------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MCQBANK> updateMCQBANK(@PathVariable("id") long id, @RequestBody MCQBANK mcqbank) {
        System.out.println("Updating MCQBANK " + mcqbank);
         
        MCQBANK currentMCQBANK = mcqbankService.findById(id);
         
        if (currentMCQBANK==null) {
            System.out.println("MCQBANK with id " + id + " not found");
            return new ResponseEntity<MCQBANK>(HttpStatus.NOT_FOUND);
        }
 
        mcqbank.setId(currentMCQBANK.getId());
        //update mcqbank here

         
        mcqbankService.update(mcqbank);
        return new ResponseEntity<MCQBANK>(mcqbank, HttpStatus.OK);
    }
 
    //------------------- Delete a MCQBANK --------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MCQBANK> deleteMCQBANK(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting MCQBANK with id " + id);
 
        MCQBANK mcqbank = mcqbankService.findById(id);
        if (mcqbank == null) {
            System.out.println("Unable to delete. MCQBANK with id " + id + " not found");
            return new ResponseEntity<MCQBANK>(HttpStatus.NOT_FOUND);
        }
 
        mcqbankService.delete(id);
        return new ResponseEntity<MCQBANK>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All MCQBANKs --------------------------------------------------------
     
    @RequestMapping(value = "/mcqbank", method = RequestMethod.DELETE)
    public ResponseEntity<MCQBANK> deleteAllMCQBANKs() {
        System.out.println("Deleting All MCQBANKs");
 
        mcqbankService.deleteAll();
        return new ResponseEntity<MCQBANK>(HttpStatus.NO_CONTENT);
    }
 
}