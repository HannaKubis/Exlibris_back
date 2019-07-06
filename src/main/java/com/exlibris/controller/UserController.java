package com.exlibris.controller;

import com.exlibris.domain.mapper.UserMapper;
import com.exlibris.domain.model.mail.Mail;
import com.exlibris.domain.model.user.User;
import com.exlibris.domain.model.user.UserDto;
import com.exlibris.exception.UserNotFoundException;
import com.exlibris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author tomirszulc on 2019-06-29
 */
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    private static String BASE_URL = "http://localhost:8302/api/mail-service";

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser(@RequestParam String name) {
        return userService.findOne(name);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) {
        Mail welcomeMail = new Mail(user.getEmail(), "Welcome to Exlibris", user.getUsername());

        WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();
        webClient
                .post()
                .uri("/send")
                .body(BodyInserters.fromPublisher(Mono.just(welcomeMail), Mail.class))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return userService.save(user);
}

    @RequestMapping(value = "/activate/", method = RequestMethod.POST)
    public User activateUser(@PathVariable String name) {
        User user = userService.findOne(name);
        user.setActivated(true);
        return userService.save(userMapper.maptToUserDto(user));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
