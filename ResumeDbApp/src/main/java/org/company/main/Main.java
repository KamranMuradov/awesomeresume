package org.company.main;


import org.company.dao.inter.CountryDaoInter;
import org.company.dao.inter.UserSkillDaoInter;
import org.company.entity.Skill;
import org.company.entity.User;
import org.company.entity.UserSkill;


public class Main {


    public static void main(String[] args) throws Exception {
        UserSkillDaoInter instance = Context.instanceUserSkillDao();
        instance.addUserSkill(new UserSkill(null, new User(7), new Skill(3,"PHP"),7));


    }
}
    

