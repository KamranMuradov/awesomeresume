package org.company.dao.inter;

import org.company.entity.Skill;

import java.util.List;

public interface SkillDaoInter {

    List<Skill> getAll();

    Skill getById(int skillId);

    boolean addSkill(Skill skill);

    boolean updateSkill(Skill skill);

    boolean removeSkill(int id);

}
