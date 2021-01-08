package de.pinpoint.server.user;

import de.pinpoint.server.Constants;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The purpose of a UserService in this context is to manage
 * a collection active Users (updating, timeouting etc.)
 * <p>
 * Also all public methods of this class should be thread safe.
 */
@Service
public class UserService {
    private Collection<PinpointUser> users = Collections.synchronizedList(new ArrayList<>());

    private PinpointUser createUser(UserInfo info) {
        PinpointUser user = new PinpointUser(info.getUuid(), info.getName(), info.getColor());
        user.updatePosition(info.getPosition());
        this.users.add(user);
        return user;
    }

    public void updateUser(UserInfo info) {
        PinpointUser user = getOrCreateUser(info);
        user.updatePosition(info.getPosition());
        user.setName(info.getName());
        user.setColor(info.getColor());
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

    public boolean userExists(UUID uuid) {
        synchronized (users) {
            return this.users.stream().anyMatch(u -> u.getUuid().equals(uuid));
        }
    }

    public UserInfo getUser(UUID uuid) throws UserNotFoundException {
        return this.getUserByUuid(uuid).getInfo();
    }

    public List<UserInfo> getUsers(UUID except) {
        this.updateTtl();
        synchronized (users) {
            return this.users.stream().map(u -> u.getInfo()).filter(u -> !u.getUuid().equals(except)).collect(Collectors.toList());
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