package com.example.recruttask.backendspringboot.Controller;


import com.example.recruttask.backendspringboot.Service.UserService;
import com.example.recruttask.backendspringboot.search.UserSearchValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.recruttask.backendspringboot.entity.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello there";
    }

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> allUsers = userService.findAll();
        //System.out.println(allUsers.toString());

        return allUsers;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user){
        System.out.println(user);
        user = userService.save(user);
        return user;
    }

    @PostMapping(value = "/users/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<User>> search(@RequestBody UserSearchValues userSearchValues){

        String name = userSearchValues.getName() != null ? userSearchValues.getName() : null;
        String surname = userSearchValues.getSurname() != null ? userSearchValues.getSurname() : null;
        String login = userSearchValues.getLogin() != null ? userSearchValues.getLogin() : null;

        String sortColumn = userSearchValues.getSortColumn() != null ? userSearchValues.getSortColumn() : null;
        String sortDirection = userSearchValues.getSortDirection() != null ? userSearchValues.getSortDirection() : null;

        Integer pageNumber = userSearchValues.getPageNumber() != null ? userSearchValues.getPageNumber() : null;
        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);


        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, sort);

        return ResponseEntity.ok(userService.findByParams(name, surname, login, pageRequest));
    }

}
