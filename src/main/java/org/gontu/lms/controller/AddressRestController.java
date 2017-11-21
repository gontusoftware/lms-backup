package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Address;
import org.gontu.lms.service.AddressService;
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
public class AddressRestController {
 
    @Autowired
    AddressService<Address> addressService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Addresss--------------------------------------------------------
     
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ResponseEntity<Set<Address>> listAllAddresss() {
        Set<Address> addresss = new HashSet(addressService.getAll());
        if(addresss.isEmpty()){	
            return new ResponseEntity<Set<Address>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Address>>(addresss, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Address--------------------------------------------------------
     
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
        System.out.println("Fetching Address with id " + id);
        Address address = addressService.findById(id);
        if (address == null) {
            System.out.println("Address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Address--------------------------------------------------------
     
    @RequestMapping(value = "/address", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createAddress(@RequestBody Address address, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Address " + address.getAddressDetail());
 
//        if (addressService.isExists(address)) {
//            System.out.println("A Address with name " + address.getAddressName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        addressService.save(address);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Address --------------------------------------------------------
     
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
        System.out.println("Updating Address " + address);
         
        Address currentAddress = addressService.findById(id);
         
        if (currentAddress==null) {
            System.out.println("Address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
 
        address.setId(currentAddress.getId());
        //update address here

         
        addressService.update(address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
 
    //------------------- Delete a Address --------------------------------------------------------
     
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Address with id " + id);
 
        Address address = addressService.findById(id);
        if (address == null) {
            System.out.println("Unable to delete. Address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
 
        addressService.delete(id);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Addresss --------------------------------------------------------
     
    @RequestMapping(value = "/address", method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAllAddresss() {
        System.out.println("Deleting All Addresss");
 
        addressService.deleteAll();
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }
 
}