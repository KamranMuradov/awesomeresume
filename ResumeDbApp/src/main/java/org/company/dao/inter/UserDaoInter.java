package org.company.dao.inter;

import org.company.entity.User;

import java.util.List;

public interface UserDaoInter {

    List<User> getAll();

    User getById(int id);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean removeUser(int id);

}
