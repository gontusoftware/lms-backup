package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Course;
import org.gontu.lms.service.CourseService;
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
public class CourseRestController {
 
    @Autowired
    CourseService<Course> courseService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Courses--------------------------------------------------------
     
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity<Set<Course>> listAllCourses() {
        Set<Course> courses = new HashSet(courseService.getAll());
        if(courses.isEmpty()){	
            return new ResponseEntity<Set<Course>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Course>>(courses, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Course--------------------------------------------------------
     
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Course> getCourse(@PathVariable("id") long id) {
        System.out.println("Fetching Course with id " + id);
        Course course = courseService.findById(id);
        if (course == null) {
            System.out.println("Course with id " + id + " not found");
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Course--------------------------------------------------------
     
    @RequestMapping(value = "/course", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Course " + course.getName());
 
//        if (courseService.isExists(course)) {
//            System.out.println("A Course with name " + course.getCourseName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        courseService.save(course);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Course --------------------------------------------------------
     
    @RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
        System.out.println("Updating Course " + course);
         
        Course currentCourse = courseService.findById(id);
         
        if (currentCourse==null) {
            System.out.println("Course with id " + id + " not found");
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
 
        course.setId(currentCourse.getId());
        //update course here

         
        courseService.update(course);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }
 
    //------------------- Delete a Course --------------------------------------------------------
     
    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Course with id " + id);
 
        Course course = courseService.findById(id);
        if (course == null) {
            System.out.println("Unable to delete. Course with id " + id + " not found");
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
 
        courseService.delete(id);
        return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Courses --------------------------------------------------------
     
    @RequestMapping(value = "/course", method = RequestMethod.DELETE)
    public ResponseEntity<Course> deleteAllCourses() {
        System.out.println("Deleting All Courses");
 
        courseService.deleteAll();
        return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
    }
 
}