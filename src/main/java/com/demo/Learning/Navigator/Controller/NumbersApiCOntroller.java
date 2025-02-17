package com.demo.Learning.Navigator.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/hidden-feature")
public class NumbersApiCOntroller {
    @GetMapping("/{number}")
    public ResponseEntity<?>getRandomNumber(@PathVariable Integer number){
        RestTemplate restTemplate=new RestTemplate();
        String url="http://numbersapi.com/"+number;
        return new ResponseEntity<>( restTemplate.getForObject(url,String.class), HttpStatus.OK);
    }


}
