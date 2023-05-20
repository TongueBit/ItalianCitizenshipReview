package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class ResourceController {

    @GetMapping("/js/landing-page.js")
    public ResponseEntity<Resource> getlandingPageJs() {
        // Load and return the "directory.js" file as a resource
        Resource resource = new ClassPathResource("static/js/landing-page.js");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(resource);
    }
    @GetMapping("/js/directory.js")
    public ResponseEntity<Resource> getDirectoryJs() {
        // Load and return the "directory.js" file as a resource
        Resource resource = new ClassPathResource("static/js/directory.js");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(resource);
    }

    @GetMapping("/css/style.css")
    public ResponseEntity<Resource> getCss()  {
    Resource resource = new ClassPathResource("/static/css/styl.css");
    return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/css"))
            .body(resource);
    }
}
