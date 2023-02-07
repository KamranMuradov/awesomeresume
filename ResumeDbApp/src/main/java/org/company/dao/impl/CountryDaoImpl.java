package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.CountryDaoInter;
import org.company.entity.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
    private Country getCountry(ResultSet resultSet) throws Exception {
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");
        return new Country(null, name, nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("select * from country");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Country country = getCountry(resultSet);
                result.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
