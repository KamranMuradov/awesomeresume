package org.company.main;

import org.company.dao.impl.*;
import org.company.dao.impl.CountryDaoImpl;
import org.company.dao.impl.EmploymentHistoryDaoImpl;
import org.company.dao.impl.SkillDaoImpl;
import org.company.dao.impl.UserDaoImpl;
import org.company.dao.impl.UserSkillDaoImpl;
import org.company.dao.inter.*;
import org.company.dao.inter.CountryDaoInter;
import org.company.dao.inter.EmploymentHistoryDaoInter;
import org.company.dao.inter.SkillDaoInter;
import org.company.dao.inter.UserDaoInter;
import org.company.dao.inter.UserSkillDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }
}
