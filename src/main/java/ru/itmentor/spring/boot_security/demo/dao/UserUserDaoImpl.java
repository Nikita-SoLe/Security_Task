package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class UserUserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(User user, String[] selectedRoles) {
        manager.persist(createUser(user, selectedRoles));
    }

    @Override
    public User read(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return manager.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void update(User user, String[] selectedRoles) {
        manager.merge(createUser(user, selectedRoles));
    }

    @Override
    public void delete(User user) {
        manager.remove(manager.find(User.class, user.getId()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getList() {
        return manager.createQuery("from User").getResultList();
    }

    @Override
    public List<Role> getRoles() {
        return manager.createQuery("from Role", Role.class).getResultList();
    }

    private User createUser(User user, String[] selectedRoles) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setLastName(user.getLastName());
        user1.setDepartment(user.getDepartment());
        user1.setSalary(user.getSalary());

        List<String> roles = Arrays.asList(selectedRoles);
        getRoles().forEach(role -> {
            if (roles.contains(role.getNameRoles())) {
                user1.addRole(role);
            }
        });
        return user1;
    }
}
