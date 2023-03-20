package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.UserSkillDaoInter;
import org.company.entity.Skill;
import org.company.entity.User;
import org.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static org.company.dao.inter.AbstractDao.connect;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("user_skill_id");
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(id, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> allUserSkill = new ArrayList<>();
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT"
                    + " us.id AS user_skill_id,"
                    + " u.*,"
                    + " us.skill_id,"
                    + " s.name AS skill_name,"
                    + " us.power"
                    + " FROM"
                    + " user_skill us"
                    + " LEFT JOIN user u ON us.user_id = u.id"
                    + " LEFT JOIN skill s ON us.skill_id = s.id"
                    + " WHERE"
                    + " us.user_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                UserSkill userSkill = getUserSkill(resultSet);

                allUserSkill.add(userSkill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUserSkill;
    }

    @Override
    public boolean addUserSkill(UserSkill userSkill) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_skill (user_id, skill_id, power) values (?, ?, ?)");
            preparedStatement.setInt(1, userSkill.getUser().getId());
            preparedStatement.setInt(2, userSkill.getSkill().getId());
            preparedStatement.setInt(3, userSkill.getPower());

            result = preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update user_skill set user_id = ?, skill_id = ?, power = ? where id = ?");
            preparedStatement.setInt(1, userSkill.getUser().getId());
            preparedStatement.setInt(2, userSkill.getSkill().getId());
            preparedStatement.setInt(3, userSkill.getPower());
            preparedStatement.setInt(4, userSkill.getId());

            result = preparedStatement.execute();
        } catch (Exception e) {
            System.err.println("PROBLEM");
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeUserSkill(int id) {
        boolean result;

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from user_skill where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
