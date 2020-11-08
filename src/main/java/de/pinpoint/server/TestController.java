package de.pinpoint.server;

import de.pinpoint.server.user.UserInfo;
import de.pinpoint.server.user.UserNotFoundException;
import de.pinpoint.server.user.UserService;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    UserService service;

    @RequestMapping("/")
    public String index() {
        return "Hello world!";
    }

    @RequestMapping("/randomToken")
    public String randomToken() {
        return UUID.randomUUID().toString();
    }


    @RequestMapping("/user/{id}")
    public UserInfo getUser(@PathVariable @Validated UUID id) throws UserNotFoundException {
        return service.getUser(id);
    }

    @RequestMapping("/list")
    public Collection<UserInfo> listUsers() {
        Collection<UserInfo> list = service.getUsers();
        return list;
    }

    @RequestMapping("/update")
    public void updateUser(@RequestBody UpdateUserRequest request) {
        service.updateUser(request.getUser());
    }
}
