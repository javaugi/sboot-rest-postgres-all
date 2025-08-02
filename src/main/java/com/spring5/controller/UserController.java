package com.spring5.controller;

import com.spring5.entity.User;
import java.util.Locale;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring5.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;

// http://learningprogramming.net/java/spring-mvc/pagination-with-spring-data-jpa-in-spring-mvc/
// https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-pagination-example/
// https://examples.javacodegeeks.com/enterprise-java/spring/data/spring-data-jparepository-example/
//
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /*
    @GetMapping("/")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", userService.findAll());
        return "editUsers";
    }
    // */

    @GetMapping("/editUsers")
    public String editUser(Locale locale, Model model) {
        model.addAttribute("users", userService.findAll());
        return "editUsers";
    }

    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            return "editUsers";
        }

        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/listUsers")
    public String listUsers(Locale locale, Model model, HttpServletRequest request, ModelMap modelMap) {
        System.out.println("listUsers");
        List<User> users = createUsers();

        //List<User> users = userService.listUsers(); //userPagingRepositary.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(users);
        long count = users.size();
        int pageSize = 5;
        long pages = count / pageSize;
        System.out.println("total " + count + "-pages=" + pages);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        //model.addAttribute("users", userService.listUsers());
        return "listUsers";
    }

    private List<User> createUsers() {

        List<User> returnValue = new ArrayList();
        User user = null;
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            user = new User();
            user.setUsername(F_N_LIST.get(rand.nextInt(F_N_LIST.size()))
                    + " " + L_N_LIST.get(rand.nextInt(L_N_LIST.size())));
            user.setUserEmail(getAlphaNumericString(5) + E_LIST.get(rand.nextInt(E_LIST.size())));

            userService.save(user);
            returnValue.add(user);
        }

        //userPagingRepositary.saveAll(returnValue);
        //userCrudRepo.saveAll(returnValue);
        return returnValue;
    }

    static String getAlphaNumericString(int n) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length()  * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
    
    static String getTwoDigitString(int n) {
        // chose a Character random from this String
        String numericString = "0123456789";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (numericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(numericString.charAt(index));
        }

        return sb.toString();
    }    

    static final String[] FIRST_NAMES = {"Alex", "Arby", "Allen", "Abbey", "Ashley", "Ben", "Bill", "Carol", "Dan", "Don", "Doug", "Ernie", "Gary", "Jax", "Jon", "Jeff", "Jessica", "Kevin", "Shannon"};
    static final String[] LAST_NAMES = {"Alton", "Aleon", "Atux", "Lee", "Swift", "Liu", "Alexon", "Alatian", "Smith", "Smita", "Will", "Wall", "Zina"};
    static final String[] EMAILS = {"aaa@ciminc.com", "a12@ciminc.com", "a2a@ciminc.com", "alibaba@ciminc.com", "alab@ciminc.com", "alex@ciminc.com",
        "arby@ciminc.com", "Allen@ciminc.com", "Abbey@ciminc.com", "Ashley@ciminc.com", "Ben@ciminc.com", "Bill@ciminc.com", "Carol@ciminc.com",
        "Dan@ciminc.com", "Don@ciminc.com", "Doug@ciminc.com", "Ernie@ciminc.com", "Gary@ciminc.com", "Jax@ciminc.com", "Jon@ciminc.com",
        "Jeff@ciminc.com", "Jessica@ciminc.com", "Kevin@ciminc.com", "Shannon@ciminc.com"};
    public static final List<String> F_N_LIST = Arrays.asList(FIRST_NAMES);
    public static final List<String> L_N_LIST = Arrays.asList(LAST_NAMES);
    public static final List<String> E_LIST = Arrays.asList(EMAILS);
}
