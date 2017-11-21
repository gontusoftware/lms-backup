package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Module;
import org.gontu.lms.service.ModuleService;
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
public class ModuleRestController {
 
    @Autowired
    ModuleService<Module> moduleService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Modules--------------------------------------------------------
     
    @RequestMapping(value = "/module", method = RequestMethod.GET)
    public ResponseEntity<Set<Module>> listAllModules() {
        Set<Module> modules = new HashSet(moduleService.getAll());
        if(modules.isEmpty()){	
            return new ResponseEntity<Set<Module>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Module>>(modules, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Module--------------------------------------------------------
     
    @RequestMapping(value = "/module/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Module> getModule(@PathVariable("id") long id) {
        System.out.println("Fetching Module with id " + id);
        Module module = moduleService.findById(id);
        if (module == null) {
            System.out.println("Module with id " + id + " not found");
            return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Module>(module, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Module--------------------------------------------------------
     
    @RequestMapping(value = "/module", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createModule(@RequestBody Module module, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Module " + module.getName());
 
//        if (moduleService.isExists(module)) {
//            System.out.println("A Module with name " + module.getModuleName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        moduleService.save(module);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/module/{id}").buildAndExpand(module.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Module --------------------------------------------------------
     
    @RequestMapping(value = "/module/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Module> updateModule(@PathVariable("id") long id, @RequestBody Module module) {
        System.out.println("Updating Module " + module);
         
        Module currentModule = moduleService.findById(id);
         
        if (currentModule==null) {
            System.out.println("Module with id " + id + " not found");
            return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
        }
 
        module.setId(currentModule.getId());
        //update module here

         
        moduleService.update(module);
        return new ResponseEntity<Module>(module, HttpStatus.OK);
    }
 
    //------------------- Delete a Module --------------------------------------------------------
     
    @RequestMapping(value = "/module/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Module> deleteModule(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Module with id " + id);
 
        Module module = moduleService.findById(id);
        if (module == null) {
            System.out.println("Unable to delete. Module with id " + id + " not found");
            return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
        }
 
        moduleService.delete(id);
        return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Modules --------------------------------------------------------
     
    @RequestMapping(value = "/module", method = RequestMethod.DELETE)
    public ResponseEntity<Module> deleteAllModules() {
        System.out.println("Deleting All Modules");
 
        moduleService.deleteAll();
        return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
    }
 
}