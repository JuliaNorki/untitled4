package web.config.dao;

import web.config.model.User;
import java.util.List;

public interface UserDAO {
    public List<User> getUserList();

    User getUser(int id);

    void save(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);


}
