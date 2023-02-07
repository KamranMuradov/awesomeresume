package org.company.main;


import org.company.dao.inter.CountryDaoInter;


public class Main {


    public static void main(String[] args) throws Exception {
        CountryDaoInter instance = Context.instanceCountryDao();
        instance.removeCountry(4);


    }
}
    

