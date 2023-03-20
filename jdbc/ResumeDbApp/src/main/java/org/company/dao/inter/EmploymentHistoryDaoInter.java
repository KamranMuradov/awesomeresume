package org.company.dao.inter;

import org.company.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

    boolean addEmploymentHistory(EmploymentHistory employmentHistory);

    boolean updateEmploymentHistory(EmploymentHistory employmentHistory);

    boolean removeEmploymentHistory(int id);

}
