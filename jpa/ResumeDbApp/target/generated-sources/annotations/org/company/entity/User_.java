package org.company.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.company.entity.Country;
import org.company.entity.EmploymentHistory;
import org.company.entity.UserSkill;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-20T15:02:11")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Date> birthdate;
    public static volatile ListAttribute<User, EmploymentHistory> employmentHistoryList;
    public static volatile SingularAttribute<User, Country> birthPlace;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Country> nationality;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> profileDesc;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile ListAttribute<User, UserSkill> userSkillList;

}