package shopping.car.service.user;

import java.util.List;
import java.util.Optional;

import shopping.car.models.LoginInfoModel;
import shopping.car.models.User;

public interface UserService {

    List<User> findAllUsers();

    Optional<User> findUserById(Integer id);

    void save(User user);

    void remove(User user);

    Optional<User> authenticate(LoginInfoModel loginInfoModel);
}