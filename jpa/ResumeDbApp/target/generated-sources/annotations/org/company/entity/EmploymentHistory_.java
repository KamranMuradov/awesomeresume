package org.company.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.company.entity.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-20T14:16:55")
@StaticMetamodel(EmploymentHistory.class)
public class EmploymentHistory_ { 

    public static volatile SingularAttribute<EmploymentHistory, Date> beginDate;
    public static volatile SingularAttribute<EmploymentHistory, Date> endDate;
    public static volatile SingularAttribute<EmploymentHistory, String> header;
    public static volatile SingularAttribute<EmploymentHistory, String> jobDescription;
    public static volatile SingularAttribute<EmploymentHistory, Integer> id;
    public static volatile SingularAttribute<EmploymentHistory, User> user;

}