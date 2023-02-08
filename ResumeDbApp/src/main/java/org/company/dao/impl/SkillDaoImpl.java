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
        List<Skill> allSkill = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from skill");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Skill skill = getSkill(resultSet);
                allSkill.add(skill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allSkill;
    }

    @Override
    public Skill getById(int skillId) {
        Skill skill = null;
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("select from skill where id  = " + skillId);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                skill = getSkill(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public boolean addSkill(Skill skill) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into skill (name) values (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, skill.getName());
            result = preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update  skill set name = ? where id = ?");
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());

            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeSkill(int id) {
        boolean result;

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from skill where id =" + id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
