package org.company.dao.inter;

import org.company.entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkill> getAllSkillByUserId(int userId);

}
