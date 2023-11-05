package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author lmao
 */
public class TampilanGUI extends javax.swing.JFrame {
    
    /**
     * Creates new form TampilanGUI
     */
    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(rootPane, pesan);
    }
    ArrayList<MataKuliah> dataMataKuliah;

    private int masukkanData(Connection conn, String kodeMK, String namaMK, String sks, String semester) throws SQLException {
        PreparedStatement pst = conn.prepareStatement
        ("INSERT INTO mata_kuliah (kode_mata_kuliah, nama_mata_kuliah, sks, semester) VALUES (?, ?, ?, ?)");
        pst.setString(1, kodeMK);
        pst.setString(2, namaMK);
        pst.setInt(3, Integer.parseInt(sks)); // Mengubah sks menjadi tipe int
        pst.setInt(4, Integer.parseInt(semester)); // Mengubah semester menjadi tipe int
        return pst.executeUpdate();
    }

    private int ubahData
        (Connection conn, String kodeMK, String namaMK, String sks, String semester) throws SQLException {
        PreparedStatement pst = conn.prepareStatement
        ("UPDATE mata_kuliah SET nama_mata_kuliah=?, sks=?, semester=? WHERE kode_mata_kuliah=?");
        pst.setString(1, namaMK);
        pst.setInt(2, Integer.parseInt(sks));
        pst.setInt(3, Integer.parseInt(semester));
        pst.setString(4, kodeMK);
        return pst.executeUpdate();
    }

    private int hapusData(Connection conn, String kodeMK) throws SQLException {
        PreparedStatement pst = conn.prepareStatement
        ("DELETE FROM mata_kuliah WHERE kode_mata_kuliah=?");
        pst.setString(1, kodeMK);
        return pst.executeUpdate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void tampil(Connection conn) {
        dataMataKuliah.clear();
        try {
            String sql = "select * from mata_kuliah";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                MataKuliah1 data = new MataKuliah1();
                data.setKodeMK(String.valueOf(rs.getObject(1)));
                data.setNamaMK(String.valueOf(rs.getObject(2)));
                data.setSKS(String.valueOf(rs.getObject(3)));
                data.setSemester(String.valueOf(rs.getObject(4)));
                dataMataKuliah.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableMataKuliah.getModel();
            model.setRowCount(0);
            for (MataKuliah1 data : dataMataKuliah) {

                Object[] baris = new Object[4];
                baris[0] = data.getKodeMK();
                baris[1] = data.getNamaMK();
                baris[2] = data.getSKS();
                baris[3] = data.getSemester();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TampilanGUI() {
        try {
            dataMataKuliah = new ArrayList<>();
            initComponents();
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mahasiswa", "postgres", "123");
            tampil(conn);
        } catch (SQLException ex) {
            Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelKodeMataKuliah = new javax.swing.JLabel();
        jLabelNamaMataKuliah = new javax.swing.JLabel();
        jLabelSKS = new javax.swing.JLabel();
        jLabelSemester = new javax.swing.JLabel();
        jTextFieldKodeMK = new javax.swing.JTextField();
        jTextFieldNamaMK = new javax.swing.JTextField();
        jTextFieldSKS = new javax.swing.JTextField();
        jTextFieldSemester = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMataKuliah = new javax.swing.JTable();
        jButtonTambah = new javax.swing.JButton();
        jButtonUbah = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonCetak = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelKodeMataKuliah.setText("Kode Mata Kuliah");
        getContentPane().add(jLabelKodeMataKuliah, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 120, -1));

        jLabelNamaMataKuliah.setText("Nama Mata Kuliah");
        getContentPane().add(jLabelNamaMataKuliah, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, -1));

        jLabelSKS.setText("SKS");
        getContentPane().add(jLabelSKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, -1));

        jLabelSemester.setText("Semester");
        getContentPane().add(jLabelSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 136, 130, 20));

        jTextFieldKodeMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKodeMKActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldKodeMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 47, 110, -1));

        jTextFieldNamaMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaMKActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldNamaMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 75, 110, -1));

        jTextFieldSKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSKSActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldSKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 103, 28, -1));

        jTextFieldSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSemesterActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 131, 28, -1));

        jTableMataKuliah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "KODE", "NAMA", "SKS", "SEMESTER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMataKuliah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMataKuliahMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMataKuliah);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 47, 309, 307));

        jButtonTambah.setText("TAMBAH");
        jButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 296, -1, -1));

        jButtonUbah.setText("UBAH");
        jButtonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbahActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 296, 83, -1));

        jButtonHapus.setText("HAPUS");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 296, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("FORM MATA KULIAH");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        jButtonCetak.setText("CETAK");
        jButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCetakActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 325, 264, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Asus\\Downloads\\01GUI.jpg")); // NOI18N
        jLabel2.setText(" ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldKodeMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKodeMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKodeMKActionPerformed

    private void jTextFieldNamaMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaMKActionPerformed

    private void jTextFieldSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSKSActionPerformed

    private void jTextFieldSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSemesterActionPerformed

    private void jButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahActionPerformed
        // TODO add your handling code here:
        String kodeMK = jTextFieldKodeMK.getText().trim();
        String namaMK = jTextFieldNamaMK.getText();
        String sks = jTextFieldSKS.getText();
        String semester = jTextFieldSemester.getText();
        /*if (!kodeMK.isEmpty() && !namaMK.isEmpty() && !sks.isEmpty() && !semester.isEmpty()) {

            try {
                // TODO add your handling code here:
                Connection conn = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/mahasiswa", "postgres", "123");
                int k = masukkanData(conn, kodeMK, namaMK, sks, semester);
                if (k > 0) {
                    tampil(conn);
                    this.peringatan("Simpan Berhasil");
                } else {
                    this.peringatan("Simpan Gagal");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.peringatan("Input Data Mata Kuliah Gagal");
        }*/
        
        jTextFieldKodeMK.setText("");
        jTextFieldNamaMK.setText("");
        jTextFieldSKS.setText("");
        jTextFieldSemester.setText("");
    }//GEN-LAST:event_jButtonTambahActionPerformed

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbahActionPerformed
        String kodeMK = jTextFieldKodeMK.getText().trim();
        String namaMK = jTextFieldNamaMK.getText();
        String sks = jTextFieldSKS.getText();
        String semester = jTextFieldSemester.getText();
        if (!kodeMK.isEmpty() && !namaMK.isEmpty() && !sks.isEmpty() && !semester.isEmpty()) {
            try {
                Connection conn = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/mahasiswa", "postgres", "123");
                int k = ubahData(conn, kodeMK, namaMK, sks, semester);
                if (k > 0) {
                    tampil(conn);
                    this.peringatan("Ubah Berhasil");
                } else {
                    this.peringatan("Ubah Gagal");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.peringatan("Input Data Mata Kuliah Gagal");
        }
        jTextFieldKodeMK.setText("");
        jTextFieldNamaMK.setText("");
        jTextFieldSKS.setText("");
        jTextFieldSemester.setText("");
    }//GEN-LAST:event_jButtonUbahActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        String kodeMK = jTextFieldKodeMK.getText().trim();
        if (!kodeMK.isEmpty()) {
            try {
                Connection conn = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/mahasiswa", "postgres", "123");
                int k = hapusData(conn, kodeMK);
                if (k > 0) {
                    tampil(conn);
                    this.peringatan("Hapus Berhasil");
                } else {
                    this.peringatan("Hapus Gagal");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.peringatan("Masukkan Kode Mata Kuliah yang Akan Dihapus");
        }
        jTextFieldKodeMK.setText("");
        jTextFieldNamaMK.setText("");
        jTextFieldSKS.setText("");
        jTextFieldSemester.setText("");

    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jTableMataKuliahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMataKuliahMouseClicked
        // TODO add your handling code here:
        int row = jTableMataKuliah.getSelectedRow();
        if (row >= 0){
            String kodeMK = jTableMataKuliah.getValueAt(row, 0).toString();
            String namaMK = jTableMataKuliah.getValueAt(row, 1).toString();
            String sks = jTableMataKuliah.getValueAt(row, 2).toString();
            String semester = jTableMataKuliah.getValueAt(row, 3).toString();
            
            jTextFieldKodeMK.setText(kodeMK);
            jTextFieldNamaMK.setText(namaMK);
            jTextFieldSKS.setText(sks);
            jTextFieldSemester.setText(semester);
        }
    }//GEN-LAST:event_jTableMataKuliahMouseClicked

    private void jButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCetakActionPerformed
        try {
            // TODO add your handling code here:
            Connection conn = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/mahasiswa", "postgres", "123");
            var jrxmlFile = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\PBOPertemuan5\\src\\GUI\\reportMK.jrxml";
            JasperReport jr;
            jr = JasperCompileManager.compileReport(jrxmlFile);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TampilanGUI.class.getName()).log(Level.SEVERE, null, ex);
        
    }                
    }//GEN-LAST:event_jButtonCetakActionPerformed

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
            java.util.logging.Logger.getLogger(TampilanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TampilanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TampilanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TampilanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TampilanGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCetak;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonTambah;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelKodeMataKuliah;
    private javax.swing.JLabel jLabelNamaMataKuliah;
    private javax.swing.JLabel jLabelSKS;
    private javax.swing.JLabel jLabelSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMataKuliah;
    private javax.swing.JTextField jTextFieldKodeMK;
    private javax.swing.JTextField jTextFieldNamaMK;
    private javax.swing.JTextField jTextFieldSKS;
    private javax.swing.JTextField jTextFieldSemester;
    // End of variables declaration//GEN-END:variables

}
