/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.company.resume.panel;

import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.company.dao.inter.SkillDaoInter;
import org.company.dao.inter.UserSkillDaoInter;
import org.company.entity.Skill;
import org.company.entity.User;
import org.company.entity.UserSkill;
import org.company.main.Context;
import org.company.resume.config.Config;

/**
 *
 * @author KamranMuradov
 */
public class SkillsPanel extends javax.swing.JPanel {

    private SkillDaoInter skillDao = Context.instanceSkillDao();
    private UserSkillDaoInter userSkillDao = Context.instanceUserSkillDao();
    List<UserSkill> list;

    /**
     * Creates new form SkillsPanel
     */
    public SkillsPanel() {
        initComponents();

    }

    private void fillWindow() {
        List<Skill> skills = skillDao.getAll();
        for (Skill skill : skills) {
            cbSkills.addItem(skill);
        }
        fillTable();
    }

    private void fillTable() {
        User user = Config.loggedInUser;
        int id = user.getId();
        list = userSkillDao.getAllSkillByUserId(id);

        Vector<Vector> rows = new Vector<>();
        for (UserSkill userSkill : list) {
            Vector<Object> row = new Vector<>();
            row.add(userSkill.getSkill());
            row.add(userSkill.getPower());
            rows.add(row);
        }

        Vector<String> columns = new Vector<>();
        columns.add("Skill");
        columns.add("Power");

        DefaultTableModel defaultTableModel = new DefaultTableModel(rows, columns);
        tblSkills.setModel(defaultTableModel);
    }

    public void fillUserComponents() {
        fillWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSkills = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblSkill = new javax.swing.JLabel();
        cbSkills = new javax.swing.JComboBox<>();
        sliderPower = new javax.swing.JSlider();
        txtSkills = new javax.swing.JTextField();
        lblPower = new javax.swing.JLabel();
        btnAdd = new javax.swing.JToggleButton();
        btnDelete = new javax.swing.JToggleButton();
        btnSave = new javax.swing.JToggleButton();

        tblSkills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSkills);

        lblSkill.setText("Skill:");

        sliderPower.setMaximum(10);
        sliderPower.setMinimum(1);
        sliderPower.setValue(1);

        lblPower.setText("Power:");

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("-");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSkill)
                        .addGap(18, 18, 18)
                        .addComponent(cbSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPower)
                        .addGap(18, 18, 18)
                        .addComponent(sliderPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSkill)
                    .addComponent(cbSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPower)
                    .addComponent(sliderPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblSkills.getSelectedRow();
        UserSkill selectedUserSkill = list.get(selectedRow);
        userSkillDao.removeUserSkill(selectedUserSkill.getId());
        fillWindow();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String skillName = txtSkills.getText();
        Skill skill;
        if (skillName != null && !skillName.trim().isEmpty()) {
            skill = new Skill(0, skillName);
            skillDao.addSkill(skill);
        } else {
            skill = (Skill) cbSkills.getSelectedItem();
        }

        int power = sliderPower.getValue();
        UserSkill userSkill = new UserSkill(null, Config.loggedInUser, skill, power);
        userSkillDao.addUserSkill(userSkill);
        fillWindow();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        UserSkill selectedUserSkill = list.get(tblSkills.getSelectedRow());

        String skillName = txtSkills.getText();
        Skill skill;
        if (skillName != null && !skillName.trim().isEmpty()) {
            skill = new Skill(0, skillName);
            skillDao.addSkill(skill);
        } else {
            skill = (Skill) cbSkills.getSelectedItem();
        }
        int power = sliderPower.getValue();

        selectedUserSkill.setSkill(skill);
        selectedUserSkill.setPower(power);

        userSkillDao.updateUserSkill(selectedUserSkill);
        fillWindow();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAdd;
    private javax.swing.JToggleButton btnDelete;
    private javax.swing.JToggleButton btnSave;
    private javax.swing.JComboBox<Skill> cbSkills;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPower;
    private javax.swing.JLabel lblSkill;
    private javax.swing.JSlider sliderPower;
    private javax.swing.JTable tblSkills;
    private javax.swing.JTextField txtSkills;
    // End of variables declaration//GEN-END:variables
}
