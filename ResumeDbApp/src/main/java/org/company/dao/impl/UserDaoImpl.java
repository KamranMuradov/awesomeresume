package org.company.dao.impl;

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
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("SELECT"
                    + " u.*,"
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " from user u"
                    + " LEFT JOIN country n on u.nationality_id=n.id"
                    + " LEFT JOIN country c on u.birthplace_id=c.id");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = getUser(resultSet);

                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("SELECT"
                    + " u.*,"
                    + " n.nationality, "
                    + " c.name as birthplace "
                    + " from user u"
                    + " LEFT JOIN country n on u.nationality_id=n.id"
                    + " LEFT JOIN country c on u.birthplace_id=c.id where u.id = " + userId);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result = getUser(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user (name, surname, email, phone, profile_description, address) values (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getProfileDesc());
            preparedStatement.setString(6, user.getAddress());

            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update user set name = ?, surname = ?, email = ?, phone = ?, profile_description = ?, address = ?, birthdate = ? where id = ?"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getProfileDesc());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setDate(7, user.getBirthdate());
            preparedStatement.setInt(8, user.getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
