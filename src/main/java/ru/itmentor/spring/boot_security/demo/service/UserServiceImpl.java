package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UserDAO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void create(User user, String[] selectedRoles) {
        userDao.create(user, selectedRoles);
    }

    @Transactional(readOnly = true)
    @Override
    public User read(Long id) {
        return userDao.read(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Transactional
    @Override
    public void update(User user, String[] selectedRoles) {
        userDao.update(user, selectedRoles);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getList() {
        return userDao.getList();
    }

    @Override
    public List<Role> getRoles() {
        return userDao.getRoles();
    }
}
