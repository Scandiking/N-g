/*
"Finally, we create a controller that provides APIs for creating, retrieving, updating, deleting, and finding Tutorials.
controller/TutorialController.java

Antakelig trengs ikke denne filen, men for Task, Person og Room trengs den.
 */


package com.nag.spring_jpa_ng.controller;

import com.nag.spring_jpa_ng.repository.N_gRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/api")

public class N_gController {

        @Autowired
        N_gRepository n_gRepository;

        @GetMapping("/n_g")
        public
}
