package de.pinpoint.server;

import de.pinpoint.server.request.RequestHandler;
import de.pinpoint.server.request.UserInfoPostRequest;
import de.pinpoint.server.request.UserListRequest;
import de.pinpoint.server.request.UuidRequest;
import de.pinpoint.server.request.validate.RequestValidationException;
import de.pinpoint.server.request.validate.UserInfoPostRequestValidator;
import de.pinpoint.server.request.validate.UserListRequestValidator;
import de.pinpoint.server.request.validate.UuidRequestValidator;
import de.pinpoint.server.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PinpointRestController {

    @Autowired
    RequestHandler handler;

    @RequestMapping("/")
    public String index() {
        return "Hi there :)";
    }

    @RequestMapping("/generate")
    public Response generate(@RequestBody UuidRequest request) {
        try {
            new UuidRequestValidator().validate(request);
        } catch (RequestValidationException ex) {
            return new Response(1, ex.getMessage());
        }
        return handler.handleUuidRequest(request);
    }

    @RequestMapping("/update")
    public Response updateUser(@RequestBody UserInfoPostRequest request) {
        try {
            new UserInfoPostRequestValidator().validate(request);
        } catch (RequestValidationException ex) {
            return new Response(1, ex.getMessage());
        }
        return handler.handleUserInfoPostRequest(request);
    }

    @RequestMapping("/list")
    public Response getUserList(@RequestBody UserListRequest request) {
        try {
            new UserListRequestValidator().validate(request);
        } catch (RequestValidationException ex) {
            return new Response(3, ex.getMessage());
        }
        return handler.handleUserListRequest(request);
    }
}
