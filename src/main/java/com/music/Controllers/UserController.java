package com.music.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.music.Repository.UserRepository;
import com.music.models.User;
@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    @GetMapping("/Login")
    public String Login(){
        return "Login";
    }

    @GetMapping("/Register")
    public String Register(){
        return "Signup";
    }

    @PostMapping("/home")
    public String Home(@RequestParam("email") String email, @RequestParam("password") String password){
       User u=userRepository.findByEmail(email);
         if(u!=null){
              if(u.getPassword().equals(password)){
                return "index";
              }
              else{
                return "Login";
              }
         }
         else{
             return "Login";
         }
    }
    


@PostMapping("/signup")
    public String Signup(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("username") String username){
        User u=new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setUsername(username);
        userRepository.save(u);
        return "Login";
    }
}