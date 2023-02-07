package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.UserSkillDaoInter;
import org.company.entity.Skill;
import org.company.entity.User;
import org.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT" +
                    " u.*," +
                    " us.skill_id," +
                    " s.NAME AS skill_name," +
                    " us.power" +
                    " FROM" +
                    " user_skill us" +
                    " LEFT JOIN USER u ON us.user_id = u.id" +
                    " LEFT JOIN skill s ON us.skill_id = s.id" +
                    " WHERE" +
                    " us.user_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                UserSkill userSkill = getUserSkill(resultSet);

                result.add(userSkill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
