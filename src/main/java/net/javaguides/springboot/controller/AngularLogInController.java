package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.AngularUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v2/")
public class AngularLogInController {
    private AngularUser[] angularUsers;

    //Angular fragt nach Loggin
    @PostMapping("/login")
    public String login_sendToken_back(@RequestBody String authentikationHeader){
        System.out.println(authentikationHeader);
        System.out.println("Hallo Login");
        return "halloToken";

    }
    @PostMapping("/token")
    public HttpStatus login_bestaetigeToken(@RequestBody String token){
        System.out.println(token);
        return HttpStatus.OK;

    }
    @GetMapping("/login")
    public String testLogin(@RequestBody String authentikationHeader){
        System.out.println(authentikationHeader);
        System.out.println("Hallo Login");
        return "halloToken";

    }
    @GetMapping("/token")
    public HttpStatus test_login_bestaetigeToken(@RequestBody String token){
        System.out.println(token);
        return HttpStatus.OK;

    }







}
