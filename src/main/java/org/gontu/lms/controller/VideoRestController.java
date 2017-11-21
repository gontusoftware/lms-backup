package org.gontu.lms.controller;
 
import java.util.HashSet;
import java.util.Set;

import org.gontu.lms.model.user.Video;
import org.gontu.lms.service.VideoService;
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
public class VideoRestController {
 
    @Autowired
    VideoService<Video> videoService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Videos--------------------------------------------------------
     
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public ResponseEntity<Set<Video>> listAllVideos() {
        Set<Video> videos = new HashSet(videoService.getAll());
        if(videos.isEmpty()){	
            return new ResponseEntity<Set<Video>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Video>>(videos, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Video--------------------------------------------------------
     
    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Video> getVideo(@PathVariable("id") long id) {
        System.out.println("Fetching Video with id " + id);
        Video video = videoService.findById(id);
        if (video == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Video--------------------------------------------------------
     
    @RequestMapping(value = "/video", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> createVideo(@RequestBody Video video, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Video " + video.getName());
 
//        if (videoService.isExists(video)) {
//            System.out.println("A Video with name " + video.getVideoName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        videoService.save(video);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/video/{id}").buildAndExpand(video.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Video --------------------------------------------------------
     
    @RequestMapping(value = "/video/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Video> updateVideo(@PathVariable("id") long id, @RequestBody Video video) {
        System.out.println("Updating Video " + video);
         
        Video currentVideo = videoService.findById(id);
         
        if (currentVideo==null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
 
        video.setId(currentVideo.getId());
        //update video here

         
        videoService.update(video);
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }
 
    //------------------- Delete a Video --------------------------------------------------------
     
    @RequestMapping(value = "/video/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Video> deleteVideo(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Video with id " + id);
 
        Video video = videoService.findById(id);
        if (video == null) {
            System.out.println("Unable to delete. Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
 
        videoService.delete(id);
        return new ResponseEntity<Video>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Videos --------------------------------------------------------
     
    @RequestMapping(value = "/video", method = RequestMethod.DELETE)
    public ResponseEntity<Video> deleteAllVideos() {
        System.out.println("Deleting All Videos");
 
        videoService.deleteAll();
        return new ResponseEntity<Video>(HttpStatus.NO_CONTENT);
    }
 
}