package org.company.main;

import org.company.dao.inter.UserDaoInter;
import org.company.entity.User;

public class Main {

    public static void main(String[] args) {
        UserDaoInter dao = Context.instanceUserDao();
        User user = dao.getById(7);
//        System.out.println(user.getNationality().getName());
//        System.out.println(user.getEmail());
        dao.hashCode();
    }

}
