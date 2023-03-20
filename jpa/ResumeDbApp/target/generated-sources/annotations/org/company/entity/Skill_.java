package org.company.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.company.entity.UserSkill;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-20T15:02:11")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, String> name;
    public static volatile SingularAttribute<Skill, Integer> id;
    public static volatile ListAttribute<Skill, UserSkill> userSkillList;

}