package org.company.dao.inter;

import org.company.entity.Country;

import java.util.List;

public interface CountryDaoInter {

    List<Country> getAll();

    Country getById(int countryId);

    boolean addCountry(Country country);

    boolean updateCountry(Country country);

    boolean removeCountry(int id);

}
