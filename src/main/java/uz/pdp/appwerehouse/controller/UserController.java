package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.entity.User;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.payload.UserDto;
import uz.pdp.appwerehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREAT
    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    // GET ALL
    @GetMapping("/allUser")
    public List<User> allUser(){
        return userService.allUser();
    }

    // GET BY ID
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteUserById/{id}")
    public Result deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    // UPDATE BY ID
    @PutMapping("/editUserById/{id}")
    public Result editUserById(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUserById(id, userDto);
    }
}
