package web.config.service;

import web.config.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUser(int id);

    public void save(User user);

    public void updateUser(int id, User user);

    public void deleteUser(int id);
}
