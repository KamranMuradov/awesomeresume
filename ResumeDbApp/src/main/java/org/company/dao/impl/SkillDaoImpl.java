package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.SkillDaoInter;
import org.company.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    private Skill getSkill(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Skill(id, name);
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

    @Override
    public Skill getById(int skillId) {
        Skill result = null;
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select from skill where id  = " + skillId);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result = getSkill(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addSkill(Skill skill) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into skill (name) values (?)");
            preparedStatement.setString(1, skill.getName());

            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update  skill set name = ? where id = ?");
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());

            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeSkill(int id) {
        Statement statement;
        try (Connection connection = connect()) {
            statement = connection.createStatement();
            return statement.execute("delete from skill where id =" + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
