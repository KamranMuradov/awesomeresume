package org.company.dao.impl;

import org.company.dao.inter.AbstractDao;
import org.company.dao.inter.EmploymentHistoryDaoInter;
import org.company.entity.EmploymentHistory;
import org.company.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception {
        String header = resultSet.getString("header");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        String jobDescription = resultSet.getString("job_description");
        int userId = resultSet.getInt("user_id");

        return new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> allEmploymentHistory = new ArrayList<>();
        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from employment_history" +
                    " where user_id = ?");
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
}
