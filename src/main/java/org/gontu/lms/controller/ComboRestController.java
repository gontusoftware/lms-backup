package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Combo;
import org.gontu.lms.service.ComboService;
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
public class ComboRestController {
 
    @Autowired
    ComboService<Combo> comboService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Combos--------------------------------------------------------
     
    @RequestMapping(value = "/combo", method = RequestMethod.GET)
    public ResponseEntity<Set<Combo>> listAllCombos() {
        Set<Combo> combos = new HashSet(comboService.getAll());
        if(combos.isEmpty()){	
            return new ResponseEntity<Set<Combo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Combo>>(combos, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Combo--------------------------------------------------------
     
    @RequestMapping(value = "/combo/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Combo> getCombo(@PathVariable("id") long id) {
        System.out.println("Fetching Combo with id " + id);
        Combo combo = comboService.findById(id);
        if (combo == null) {
            System.out.println("Combo with id " + id + " not found");
            return new ResponseEntity<Combo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Combo>(combo, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Combo--------------------------------------------------------
     
    @RequestMapping(value = "/combo", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createCombo(@RequestBody Combo combo, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Combo " + combo.getName());
 
//        if (comboService.isExists(combo)) {
//            System.out.println("A Combo with name " + combo.getComboName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        comboService.save(combo);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/combo/{id}").buildAndExpand(combo.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Combo --------------------------------------------------------
     
    @RequestMapping(value = "/combo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Combo> updateCombo(@PathVariable("id") long id, @RequestBody Combo combo) {
        System.out.println("Updating Combo " + combo);
         
        Combo currentCombo = comboService.findById(id);
         
        if (currentCombo==null) {
            System.out.println("Combo with id " + id + " not found");
            return new ResponseEntity<Combo>(HttpStatus.NOT_FOUND);
        }
 
        combo.setId(currentCombo.getId());
        //update combo here

         
        comboService.update(combo);
        return new ResponseEntity<Combo>(combo, HttpStatus.OK);
    }
 
    //------------------- Delete a Combo --------------------------------------------------------
     
    @RequestMapping(value = "/combo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Combo> deleteCombo(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Combo with id " + id);
 
        Combo combo = comboService.findById(id);
        if (combo == null) {
            System.out.println("Unable to delete. Combo with id " + id + " not found");
            return new ResponseEntity<Combo>(HttpStatus.NOT_FOUND);
        }
 
        comboService.delete(id);
        return new ResponseEntity<Combo>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Combos --------------------------------------------------------
     
    @RequestMapping(value = "/combo", method = RequestMethod.DELETE)
    public ResponseEntity<Combo> deleteAllCombos() {
        System.out.println("Deleting All Combos");
 
        comboService.deleteAll();
        return new ResponseEntity<Combo>(HttpStatus.NO_CONTENT);
    }
 
}