package org.company.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.company.entity.Skill;
import org.company.entity.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-20T14:16:55")
@StaticMetamodel(UserSkill.class)
public class UserSkill_ { 

    public static volatile SingularAttribute<UserSkill, Skill> skill;
    public static volatile SingularAttribute<UserSkill, Integer> id;
    public static volatile SingularAttribute<UserSkill, Integer> power;
    public static volatile SingularAttribute<UserSkill, User> user;

}