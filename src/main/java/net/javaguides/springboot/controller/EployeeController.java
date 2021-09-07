package net.javaguides.springboot.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class EployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get all emplyees
    @GetMapping("/employees")
    public List<Employee> getAllEmplyees(){
        return employeeRepository.findAll();
    }

    @GetMapping( "/employees/{id}" )
    public Employee findOne( @PathVariable Long id ){
        return this.employeeRepository.findById( id )
                .orElseThrow(  );
    }

    //create employee rest api
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postController(  @RequestBody Employee employee ){

        Employee save = employeeRepository.save(employee);
        return save;
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id) {
        this.employeeRepository.findById(id)
                .orElseThrow();
        employeeRepository.deleteById(id);
        System.out.println("Deleted");
    }




    //LoginTest
    @GetMapping("/login")
    public String testLogin(){
        System.out.println("Hallo Login");
        String keyString= "Hall";

        Calendar instance = Calendar.getInstance();
        Date issuedAt= instance.getTime();
        Date expirationDate = new Date(instance.getTimeInMillis() + (10 * 60 * 1000));
        Set<String> claims= new HashSet<>();
        claims.add("ADMIN");
        claims.add("USER");

        byte[] keyBytes = Base64.getDecoder().decode(keyString.getBytes());
        Key key = new SecretKeySpec(keyBytes,  SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setIssuer("NETSTORSYS")
                .setSubject("UserName")
                .claim("pms", claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login_sendToken_back(@RequestBody String body){
        System.out.println(body);
        System.out.println("Hallo Login");
        return "halloToken";

    }
    @GetMapping("/token")
    public HttpStatus test_login_bestaetigeToken(){
        return HttpStatus.OK;

    }

    @PostMapping("/token")
    public HttpStatus login_bestaetigeToken(@RequestBody String token){
        System.out.println(token);
        return HttpStatus.OK;

    }



}
