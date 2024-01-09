/*
A Frame
Create JFrame to add Menu Bar // cause JPanel does not support MenuBar

 */
package com.apex.ishwor.gui;


public class DashBoard extends javax.swing.JFrame {

    
    public DashBoard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        adduserMenuItem = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        searchUserMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        changepasswordMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        addstudentMenuItem = new javax.swing.JMenuItem();
        searchstudentMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adduserMenuItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        adduserMenuItem.setText("ManageUser");
        adduserMenuItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Add User");
        jMenuItem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        adduserMenuItem.add(jMenuItem1);

        searchUserMenuItem.setText("Search User");
        searchUserMenuItem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        searchUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserMenuItemActionPerformed(evt);
            }
        });
        adduserMenuItem.add(searchUserMenuItem);

        jMenuBar1.add(adduserMenuItem);

        jMenu2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu2.setText("Change Password");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        changepasswordMenuItem.setText("Change New Password");
        changepasswordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepasswordMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(changepasswordMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu1.setText("Manage Student");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        addstudentMenuItem.setText("Add Student");
        addstudentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addstudentMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(addstudentMenuItem);

        searchstudentMenuItem.setText("Search Student");
        searchstudentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchstudentMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchstudentMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        setContentPane(new AddUser());
        revalidate();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void searchUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserMenuItemActionPerformed
        setContentPane(new SearchUser());
        revalidate();
    }//GEN-LAST:event_searchUserMenuItemActionPerformed

    private void changepasswordMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepasswordMenuItemActionPerformed
        setContentPane(new ChangePassword());
        revalidate();
    }//GEN-LAST:event_changepasswordMenuItemActionPerformed

    private void addstudentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstudentMenuItemActionPerformed
        setContentPane(new AddStudent());
        revalidate();
    }//GEN-LAST:event_addstudentMenuItemActionPerformed

    private void searchstudentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchstudentMenuItemActionPerformed
        setContentPane(new SearchStudent());
        revalidate();
    }//GEN-LAST:event_searchstudentMenuItemActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addstudentMenuItem;
    private javax.swing.JMenu adduserMenuItem;
    private javax.swing.JMenuItem changepasswordMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem searchUserMenuItem;
    private javax.swing.JMenuItem searchstudentMenuItem;
    // End of variables declaration//GEN-END:variables
}
