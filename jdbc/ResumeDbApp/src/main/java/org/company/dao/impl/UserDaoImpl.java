package org.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.UserDaoInter;
import org.company.entity.Country;
import org.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String profileDesc = resultSet.getString("profile_description");
        String address = resultSet.getString("address");
        Date birthdate = resultSet.getDate("birthdate");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");
        String nationalityStr = resultSet.getString("nationality");
        String birthPlaceStr = resultSet.getString("birthplace");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthPlaceStr, null);

        return new User(id, name, surname, email, phone, profileDesc, address, birthdate, nationality, birthplace);
    }private User getUserSimple(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String profileDesc = resultSet.getString("profile_description");
        String address = resultSet.getString("address");
        Date birthdate = resultSet.getDate("birthdate");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");


        User user = new User(id, name, surname, email, phone, profileDesc, address, birthdate, null, null);
        user.setPassword(resultSet.getString("password"));
        return user;
    }

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
                User user = getUser(resultSet);
                allUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;
    }

    @Override
    public User getById(int userId) {
        User user = null;
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("SELECT"
                    + " u.*,"
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " from user u"
                    + " LEFT JOIN country n on u.nationality_id = n.id"
                    + " LEFT JOIN country c on u.birthplace_id = c.id where u.id = " + userId);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                result = getUserSimple(resultSet);
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
                result = getUserSimple(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();
    @Override
    public boolean addUser(User user) {
        boolean result;

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user (name, surname, email, password, phone, profile_description, address) values (?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, crypt.hashToString(4, user.getPassword().toCharArray()));
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getProfileDesc());
            preparedStatement.setString(7, user.getAddress());

            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        User user = new User(0, "test", "test", "test", "test", null, "test", null, null, null);
        user.setPassword("12345");
        new UserDaoImpl().addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        boolean result;

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update user set name = ?, surname = ?, email = ?, phone = ?, profile_description = ?, address = ?, birthdate = ?, birthplace_id = ?, nationality_id = ? where id = ?"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getProfileDesc());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setDate(7, user.getBirthdate());
            preparedStatement.setInt(8, user.getBirthPlace().getId());
            preparedStatement.setInt(9, user.getNationality().getId());
            preparedStatement.setInt(10, user.getId());
            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeUser(int id) {
        boolean result;

        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            result = statement.execute("delete from user where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
