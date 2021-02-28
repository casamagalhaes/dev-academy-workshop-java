package br.com.workshop.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path="/")
    public String hello() {
        return "Hello, World!";
    }

    @RequestMapping(method = RequestMethod.GET, path="/helloworld")
    public String helloworld() {
        return "Hello, World!";
    }
    
}
