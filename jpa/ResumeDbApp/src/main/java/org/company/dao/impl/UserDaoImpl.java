package org.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.UserDaoInter;
import org.company.entity.Country;
import org.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> allUser = new ArrayList<>();
        try (Connection connection = connect()) {

            String sql = "SELECT"
                    + " u.*,"
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " from user u"
                    + " LEFT JOIN country n on u.nationality_id=n.id"
                    + " LEFT JOIN country c on u.birthplace_id=c.id where 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += "and u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                sql += "and u.surname=? ";
            }
            if (nationalityId != null) {
                sql += "and u.nationality_id=?";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                preparedStatement.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                preparedStatement.setString(i, surname);
                i++;
            }
            if (nationalityId != null) {
                preparedStatement.setInt(i, nationalityId);
            }

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
//                User user = getUser(resultSet);
//                allUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;
    }

    @Override
    public User getById(int userId) {
        EntityManager entityManager = em();

        User user = entityManager.find(User.class, userId);
        entityManager.close();
        return user;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from user where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                result = getUserSimple(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from user where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                result = getUserSimple(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User user) {
        user.setPassword(crypt.hashToString(4, user.getPassword().toCharArray()));

        EntityManager entityManager = em();

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        return true;
    }

//    public static void main(String[] args) {
//        User user = new User(0, "test", "test", "test", "test", null, "test", null, null, null);
//        user.setPassword("12345");
//        new UserDaoImpl().addUser(user);
//    }
    @Override
    public boolean updateUser(User user) {
        EntityManager entityManager = em();

        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager entityManager = em();

        User user = entityManager.find(User.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        return true;
    }
}
