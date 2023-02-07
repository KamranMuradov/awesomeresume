package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.SkillDaoInter;
import org.company.entity.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    private Skill getSkill(ResultSet resultSet) throws Exception {
        String name = resultSet.getString("name");

        return new Skill(null, name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from skill");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Skill skill = getSkill(resultSet);
                result.add(skill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
