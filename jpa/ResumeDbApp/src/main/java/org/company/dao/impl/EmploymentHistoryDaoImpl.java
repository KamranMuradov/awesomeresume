package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.EmploymentHistoryDaoInter;
import org.company.entity.EmploymentHistory;
import org.company.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static org.company.dao.inter.AbstractDao.connect;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String header = resultSet.getString("header");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        String jobDescription = resultSet.getString("job_description");
        int userId = resultSet.getInt("user_id");

        return new EmploymentHistory(id, header, beginDate, endDate, jobDescription, new User(userId));
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> allEmploymentHistory = new ArrayList<>();
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from employment_history"
                    + " where user_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                EmploymentHistory employmentHistory = getEmploymentHistory(resultSet);

                allEmploymentHistory.add(employmentHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEmploymentHistory;
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory employmentHistory) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employment_history (header, begin_date, end_date, job_description) values (?, ?, ?, ?)");
            preparedStatement.setString(1, employmentHistory.getHeader());
//            preparedStatement.setDate(2, employmentHistory.getBeginDate());
//            preparedStatement.setDate(3, employmentHistory.getEndDate());
            preparedStatement.setString(4, employmentHistory.getJobDescription());

            result = preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory employmentHistory) {
        boolean result;

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employment_history set header = ?, begin_date = ?, end_date = ?, job_description = ? where id = ?");
            preparedStatement.setString(1, employmentHistory.getHeader());
//            preparedStatement.setDate(2, employmentHistory.getBeginDate());
//            preparedStatement.setDate(3, employmentHistory.getEndDate());
            preparedStatement.setString(4, employmentHistory.getJobDescription());
            preparedStatement.setInt(5, employmentHistory.getId());

            result = preparedStatement.execute();
        } catch (Exception e) {
            System.err.println("PROBLEM");
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        boolean result;

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            result = statement.execute("delete from employment_history where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
