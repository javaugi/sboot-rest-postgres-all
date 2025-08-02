 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import static com.spring5.controller.UserController.E_LIST;
import static com.spring5.controller.UserController.F_N_LIST;
import static com.spring5.controller.UserController.L_N_LIST;
import static com.spring5.controller.UserController.getAlphaNumericString;
import static com.spring5.controller.UserController.getTwoDigitString;
import com.spring5.entity.User;
import com.spring5.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/angular")
//http://localhost:8080/users
@CrossOrigin(origins = "http://localhost:4200")
public class UserAngularController implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(UserAngularController.class);

    // standard constructors
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    private int totalCount = 20;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("UserAngularController with args {}", Arrays.toString(args)); 
        totalCount = 20;
        createUsers();
        log.info("UserAngularController Users created ..."); 
        userRepository.findAll().forEach(System.out::println);
        log.info("Done UserAngularController run setup"); 
    }
    
    private List<User> createUsers() {

        List<User> returnValue = new ArrayList();
        User user = null;
        Random rand = new Random();
        for (int i = 0; i < totalCount; i++) {
            user = new User();
            user.setFirstName(F_N_LIST.get(rand.nextInt(F_N_LIST.size())));
            user.setLastName(L_N_LIST.get(rand.nextInt(L_N_LIST.size())));
            user.setUsername(user.getFirstName().substring(0, 1).toLowerCase() + user.getLastName().toLowerCase() + getTwoDigitString(2));
            user.setUserEmail(getAlphaNumericString(5) + E_LIST.get(rand.nextInt(E_LIST.size())));

            userRepository.save(user);
            log.info("createUsers i user created "); 
            returnValue.add(user);
        }

        //userPagingRepositary.saveAll(returnValue);
        //userCrudRepo.saveAll(returnValue);
        return returnValue;
    }
}