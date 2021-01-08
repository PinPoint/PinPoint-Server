package de.pinpoint.server.request;

import de.pinpoint.server.request.validate.RequestValidationException;
import de.pinpoint.server.response.Response;
import de.pinpoint.server.response.UserListResponse;
import de.pinpoint.server.response.UuidResponse;
import de.pinpoint.server.user.UserInfo;
import de.pinpoint.server.user.UserService;
import de.pinpoint.server.user.UuidGenerator;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The Purpose of an instance of this class is to handle incoming requests and return a matching response.
 * Requests should already be validated at this point.
 */
@Service
public class RequestHandler {
    @Autowired
    private UserService service;
    private UuidGenerator uuidGen = new UuidGenerator();
    private Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public Response handleUuidRequest(UuidRequest validRequest) {
        logger.info(validRequest.getUserId() + " request new uuid");
        UUID proposed = validRequest.getUserId();
        if (service.userExists(proposed)) {
            proposed = uuidGen.generateUuid();
        }
        return new UuidResponse(proposed);
    }

    public Response handleUserInfoPostRequest(UserInfoPostRequest validRequest) {
        logger.info(validRequest.getUserId() + " post UserInfo");
        UserInfo userInfo = validRequest.getUserInfo();
        service.updateUser(userInfo);
        return new Response();
    }

    public Response handleUserListRequest(UserListRequest validRequest){
        logger.info(validRequest.getUserId() + " request UserList");
        List<UserInfo> list = service.getUsers(validRequest.getUserId());
        return new UserListResponse(list);
    }
}