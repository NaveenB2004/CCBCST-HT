package PostLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NaveenB2004
 */
public class Results extends javax.swing.JFrame {

    /**
     * Creates new form TestResults
     */
    public Results() {
        initComponents();
        startup();
    }

    public static String eventId;
    public static String event;
    Connection conn = Main.Database.conn();
    DefaultTableModel model;

    private void startup() {
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jComboBox1.removeAllItems();
        if (event.equals("test")) {
            evtTest();
        } else {
            evtActivity();
        }
    }

    private void evtTest() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM tests WHERE id='" + eventId + "'");
            while (rs.next()) {
                jLabel4.setText(eventId);
                jLabel5.setText(rs.getString("name"));
                jComboBox1.addItem(rs.getString("date"));
                jLabel8.setText(""+rs.getInt("defaultMark"));
                Statement stmt0 = conn.createStatement();
                ResultSet rs0 = stmt0.executeQuery("SELECT COUNT(id) "
                        + "FROM testMarks WHERE testId='" + eventId + "'");
                while (rs0.next()) {
                    Statement stmt1 = conn.createStatement();
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs1;
                    ResultSet rs2;
                    if (rs0.getInt(1) == 0) {
                        rs2 = stmt2.executeQuery("SELECT id, callName, nameWithInitials, class "
                                + "FROM scouts");
                        while (rs2.next()) {
                            Object[] row = {rs2.getString(1), rs2.getString(2),
                                rs2.getString(3), rs2.getString(4), 0};
                            model.addRow(row);
                        }
                    } else {
                        int marks;
                        rs1 = stmt1.executeQuery("SELECT scoutId, marks "
                                + "FROM testMarks WHERE testId='" + eventId + "'");
                        while (rs1.next()) {
                            marks = rs1.getInt(2);
                            rs2 = stmt2.executeQuery("SELECT id, callName, nameWithInitials, class "
                                    + "FROM scouts "
                                    + "WHERE id='" + rs1.getInt(1) + "'");
                            while (rs2.next()) {
                                Object[] row = {rs2.getString(1), rs2.getString(2),
                                    rs2.getString(3), rs2.getString(4), marks};
                                model.addRow(row);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void evtActivity() {
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int x = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM activities WHERE id='" + eventId + "'");
            while (rs.next()) {
                jLabel4.setText(eventId);
                jLabel5.setText(rs.getString("name"));
                jLabel8.setText("defaultMark");
                Statement stmt0 = conn.createStatement();
                ResultSet rs0 = stmt0.executeQuery("SELECT DISTINCT date "
                        + "FROM activityMarks "
                        + "ORDER BY date WHERE activityId='" + eventId + "' "
                        + "DESC");
                while (rs0.next()) {
                    if (!rs0.getString(1).equals(todayDate) && x == 0) {
                        jComboBox1.addItem(todayDate);
                        x++;
                    }
                    jComboBox1.addItem(rs0.getString(1));
                }
                Statement stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(id) "
                        + "FROM activityMarks WHERE activityId='" + eventId + "' "
                        + "AND date='" + todayDate + "'");
                while (rs1.next()) {
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2;
                    int marks;
                    if (rs1.getInt(1) == 0) {
                        rs2 = stmt2.executeQuery("SELECT id, callName, nameWithInitials, class "
                                + "FROM scouts");
                        while (rs2.next()) {
                            Object[] row = {rs2.getString(1), rs2.getString(2),
                                rs2.getString(3), rs2.getString(4), 0};
                            model.addRow(row);
                        }
                    } else {
                        rs2 = stmt2.executeQuery("SELECT marks, scoutId "
                                + "FROM activityMarks WHERE activityId='" + eventId + "' "
                                + "AND date='" + todayDate + "'");
                        while (rs2.next()) {
                            marks = rs2.getInt(1);
                            Statement stmt3 = conn.createStatement();
                            ResultSet rs3 = stmt3.executeQuery("SELECT id, callName, nameWithInitials, class "
                                    + "FROM scouts WHERE id='" + rs2.getInt(2) + "'");
                            while (rs3.next()) {
                                Object[] row = {rs3.getString(1), rs3.getString(2),
                                    rs3.getString(3), rs3.getString(4), marks};
                                model.addRow(row);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Test Results");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ID : ");

        jLabel2.setText("Name : ");

        jLabel3.setText("Date : ");

        jLabel4.setText("---");

        jLabel5.setText("---");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Call Name", "Name with Initials", "Class", "Earned Marks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Default Mark : ");

        jLabel8.setText("---");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (jComboBox1.getSelectedItem() != null) {
            if (!jComboBox1.getSelectedItem().toString().equals(todayDate) && event.equals("activity")) {
                try {
                    int marks;
                    model.setRowCount(0);
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("SELECT marks, scoutId "
                            + "FROM activityMarks WHERE activityId='" + eventId + "' "
                            + "AND date='" + jComboBox1.getSelectedItem().toString() + "'");
                    while (rs2.next()) {
                        marks = rs2.getInt(1);
                        Statement stmt3 = conn.createStatement();
                        ResultSet rs3 = stmt3.executeQuery("SELECT id, callName, nameWithInitials, class "
                                + "FROM scouts WHERE id='" + rs2.getInt(2) + "'");
                        while (rs3.next()) {
                            Object[] row = {rs3.getString(1), rs3.getString(2),
                                rs3.getString(3), rs3.getString(4), marks};
                            model.addRow(row);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (event.equals("test")) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(id) FROM "
                        + "testMarks WHERE testId='" + eventId + "'");
                while (rs.next()) {
                    Statement stmt0 = conn.createStatement();
                    if (rs.getInt(1) == 0) {
                        for (int i = 0; i < jTable1.getRowCount(); i++) {
                            stmt0.executeUpdate("INSERT INTO testMarks "
                                    + "(scoutId, testId, marks) VALUES "
                                    + "('" + model.getValueAt(i, 0) + "', "
                                    + "'" + eventId + "', '" + model.getValueAt(i, 4) + "')");
                        }
                    } else {
                        for (int i = 0; i < jTable1.getRowCount(); i++) {
                            stmt0.executeUpdate("UPDATE testMarks "
                                    + "SET marks='" + model.getValueAt(i, 4) + "' "
                                    + "WHERE scoutId='" + model.getValueAt(i, 0) + "' "
                                    + "AND testId='" + eventId + "'");
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Success!");
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Error!\n" + e);
            }
        } else {
            String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(id) "
                        + "FROM activityMarks "
                        + "WHERE date='" + todayDate + "' AND activityId='" + eventId + "'");
                while (rs.next()) {
                    Statement stmt0 = conn.createStatement();
                    if (rs.getInt(1) == 0) {
                        for (int i = 0; i > jTable1.getRowCount(); i++) {
                            stmt0.executeUpdate("INSERT INTO activityMarks "
                                    + "(scoutId, activityId, date, marks) VALUES "
                                    + "('" + model.getValueAt(i, 0) + "', "
                                    + "'" + eventId + "', '" + todayDate + "', "
                                    + "'" + model.getValueAt(i, 4) + "')");
                        }
                    } else {
                        for (int i = 0; i > jTable1.getRowCount(); i++) {
                            stmt0.executeUpdate("UPDATE activityMarks "
                                    + "SET marks='" + model.getValueAt(i, 4) + "' "
                                    + "WHERE scoutId='" + model.getValueAt(i, 0) + "' AND "
                                    + "activityId='" + eventId + "' AND "
                                    + "date='" + todayDate + "'");
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Success!");
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Error!\n" + e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Results().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
