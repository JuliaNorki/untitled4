package web.config.dao;

import org.springframework.stereotype.Repository;
import web.config.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> getUserList() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
        //createQuery() - создать объект Query для выполнения JPQL запроса.
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
        //find() либо JPQL запросами. В результате жадно загружены будут все не-lazy атрибуты представления.
        //Если в данный метод передать null, либо не вызывать его вообще, загрузка будет производиться в соответствие с правилами аннотаций сущностей.
        //Представления, явно переданные в метод find() или установленные в объекте Query имеют приоритет над установленным данным методом.
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
        //persist() - вводит новый экземпляр сущности в персистентный контекст.
        //При коммите транзакции командой SQL INSERT в БД будет создана соответствующая запись.
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUser(id);
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setAge(user.getAge());
      //  updateUser.setAge(user.getAge());
        entityManager.merge(updateUser);//- переносит состояние отсоединенного экземпляра сущности в персистентный контекст следующим образом:
        // из БД загружается экземпляр с тем же идентификатором, в него переносится состояние переданного Detached экземпляра и возвращается загруженный Managed экземпляр.
        // Далее надо работать именно с возвращенным Managed экземпляром.
        // При коммите транзакции командой SQL UPDATE в БД будет сохранено состояние данного экземпляра.
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

}
