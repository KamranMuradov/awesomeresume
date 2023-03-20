package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.CountryDaoInter;
import org.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    private Country getCountry(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");
        return new Country(id, name, nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> allCountry = new ArrayList<>();
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("select * from country");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Country country = getCountry(resultSet);
                allCountry.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCountry;
    }

    @Override
    public Country getById(int countryId) {
        Country country = null;
        
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from country where id =" + countryId);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                country = getCountry(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean addCountry(Country country) {
        boolean result;
        
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into country (name, nationality) values (?,?)");
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getNationality());
            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateCountry(Country country) {
        boolean result;
        
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update country set name = ?, nationality = ? where id = ?");
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getNationality());
            preparedStatement.setInt(3, country.getId());
            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeCountry(int id) {
        boolean result;

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from country where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
