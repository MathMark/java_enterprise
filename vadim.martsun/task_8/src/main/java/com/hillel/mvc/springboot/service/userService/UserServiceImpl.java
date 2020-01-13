package com.hillel.mvc.springboot.service.userService;

import com.hillel.mvc.springboot.dao.userRepository.UserRepository;
import com.hillel.mvc.springboot.model.User;
import com.hillel.mvc.springboot.model.mappers.userMapper.UserMapper;
import com.hillel.mvc.springboot.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserRequest getUserRequestById(int id) {
        User user = userRepository.getUserById(id);
        return new UserRequest(user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getBirthDate().toString(),
                user.getGender().toString());
    }

    @Override
    public void save(UserRequest userRequest) {
        User user = userMapper.getUser(userRequest);
        userRepository.save(user);
    }

    @Override
    public void update(UserRequest userRequest) {
        userRepository.update(userRequest.getId(), userRequest);
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }
}
