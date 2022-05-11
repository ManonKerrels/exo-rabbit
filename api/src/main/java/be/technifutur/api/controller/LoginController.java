package be.technifutur.api.controller;

import be.technifutur.api.models.LoginForm;
import be.technifutur.api.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public String login(@RequestBody LoginForm form){
        return service.login(form);
    }
}
