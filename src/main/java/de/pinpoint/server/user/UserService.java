package de.pinpoint.server.user;

import de.pinpoint.server.Constants;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private Collection<PinpointUser> users = Collections.synchronizedList(new ArrayList<>());

    private PinpointUser createUser(UserInfo info) {
        PinpointUser user = new PinpointUser(info.getUuid(), info.getName());
        user.setPosition(info.getPosition());
        user.setLastPositionUpdateStamp(System.currentTimeMillis());
        this.users.add(user);
        return user;
    }

    public void updateUser(UserInfo info) {
        PinpointUser user = getOrCreateUser(info);
        user.setLastPositionUpdateStamp(System.currentTimeMillis());
        user.setAlive(true);
        user.setPosition(info.getPosition());
        user.setName(info.getName());
    }

    private PinpointUser getOrCreateUser(UserInfo info) {
        try {
            return getUserByUuid(info.getUuid());
        } catch (UserNotFoundException ex) {
            return createUser(info);
        }
    }

    private PinpointUser getUserByUuid(UUID uuid) throws UserNotFoundException {
        synchronized (users) {
            return this.users
                    .stream()
                    .filter(u -> u.getUuid().equals(uuid))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException(uuid));
        }
    }

    public UserInfo getUser(UUID uuid) throws UserNotFoundException {
        return this.getUserByUuid(uuid).getInfo();
    }

    public Collection<UserInfo> getUsers() {
        this.updateTtl();
        synchronized (users) {
            return this.users.stream().map(u -> u.getInfo()).collect(Collectors.toList());
        }
    }

    private void updateTtl() {
        synchronized (users) {
            Iterator<PinpointUser> itr = this.users.iterator();
            while (itr.hasNext()) {
                PinpointUser user = itr.next();
                if (System.currentTimeMillis() - user.getLastPositionUpdateStamp() > Constants.POSITION_TTL) {
                    user.setAlive(false);
                    itr.remove();
                }
            }
        }
    }
}