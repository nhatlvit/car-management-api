package shopping.car.service.user;

import shopping.car.models.LoginInfoModel;
import shopping.car.models.User;
import shopping.car.repository.UserRepository;
import shopping.car.utils.EncodeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
    	userRepository.save(user);
    }

    @Override
    public void remove(User user) {
    	userRepository.delete(user);
    }

    @Override
    public Optional<User> authenticate(LoginInfoModel loginInfoModel) {
    	User user = userRepository.findUserByLoginInfo(loginInfoModel.getUsername(), EncodeUtils.encodePassword(loginInfoModel.getPassword()));
    	Optional<User> opt = Optional.ofNullable(user);
    	return opt;
    }
}