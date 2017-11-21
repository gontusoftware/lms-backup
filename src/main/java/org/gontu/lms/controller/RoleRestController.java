package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Role;
import org.gontu.lms.service.RoleService;
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
public class RoleRestController {
 
    @Autowired
    RoleService<Role> roleService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Roles--------------------------------------------------------
     
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<Set<Role>> listAllRoles() {
        Set<Role> roles = new HashSet(roleService.getAll());
        if(roles.isEmpty()){	
            return new ResponseEntity<Set<Role>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Role>>(roles, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Role--------------------------------------------------------
     
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
        System.out.println("Fetching Role with id " + id);
        Role role = roleService.findById(id);
        if (role == null) {
            System.out.println("Role with id " + id + " not found");
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Role--------------------------------------------------------
     
    @RequestMapping(value = "/role", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Role " + role.getName());
 
//        if (roleService.isExists(role)) {
//            System.out.println("A Role with name " + role.getRoleName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        roleService.save(role);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/role/{id}").buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Role --------------------------------------------------------
     
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        System.out.println("Updating Role " + role);
         
        Role currentRole = roleService.findById(id);
         
        if (currentRole==null) {
            System.out.println("Role with id " + id + " not found");
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
 
        role.setId(currentRole.getId());
        //update role here

         
        roleService.update(role);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }
 
    //------------------- Delete a Role --------------------------------------------------------
     
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteRole(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Role with id " + id);
 
        Role role = roleService.findById(id);
        if (role == null) {
            System.out.println("Unable to delete. Role with id " + id + " not found");
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
 
        roleService.delete(id);
        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Roles --------------------------------------------------------
     
    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteAllRoles() {
        System.out.println("Deleting All Roles");
 
        roleService.deleteAll();
        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
    }
 
}