package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void create(User user, String[] selectedRoles);
    User read(Long id);
    User getByName(String name);
    void update(User user, String[] selectedRoles);
    void delete(User user);
    List<User> getList();
    List<Role> getRoles();
}
