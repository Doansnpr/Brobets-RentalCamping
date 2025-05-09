/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package backend;

import backend.Koneksi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author Toshiba
 */
public class MenuPelanggan extends javax.swing.JPanel {

 JTable table;
    DefaultTableModel model;   
    
    public MenuPelanggan() {
        initComponents();
         loadDataTabel();
    }

    private void loadDataTabel() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Pelanggan");
    model.addColumn("Nama");
    model.addColumn("No HP");
    model.addColumn("Poin");

    jTable_custom1.setModel(model); // Set model sebelum isi data

    // Panggil koneksi
    Koneksi koneksi = new Koneksi();
    koneksi.config();

    try {
        Connection conn = koneksi.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pelanggan");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_pelanggan"),
                rs.getString("nama_pelanggan"),
                rs.getString("no_hp"),
                rs.getInt("poin")
            });
        }

        rs.close();
        st.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + e.getMessage());
    }
}

    private void hapusData() {
    int selectedRow = jTable_custom1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
        return;
    }

    String idPelanggan = jTable_custom1.getValueAt(selectedRow, 0).toString(); // Ambil id dari kolom pertama

    int konfirmasi = JOptionPane.showConfirmDialog(this,
            "Yakin ingin menghapus data dengan ID: " + idPelanggan + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

    if (konfirmasi == JOptionPane.YES_OPTION) {
        try {
            Koneksi koneksi = new Koneksi();
            koneksi.config();
            Connection conn = koneksi.getConnection();

            String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPelanggan);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            loadDataTabel(); // reload tabel setelah hapus
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }
}

    private void cariData() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Pelanggan");
    model.addColumn("Nama");
    model.addColumn("No HP");
    model.addColumn("Poin");

    String keyword = txt_search.getText(); // ambil teks dari field pencarian

    try {
        Koneksi koneksi = new Koneksi();
        koneksi.config();
        Connection conn = koneksi.getConnection();

        String query = "SELECT * FROM pelanggan WHERE id_pelanggan LIKE ? OR nama_pelanggan LIKE ? OR no_hp LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ps.setString(3, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id_pelanggan"),
                rs.getString("nama_pelanggan"),
                rs.getString("no_hp"),
                rs.getInt("poin")
            });
        }

        jTable_custom1.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari data: " + e.getMessage());
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

        page_main = new javax.swing.JPanel();
        page_penyewaan = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_custom1 = new custom.JTable_custom();

        setLayout(new java.awt.CardLayout());

        page_main.setBackground(new java.awt.Color(255, 244, 232));
        page_main.setLayout(new java.awt.CardLayout());

        page_penyewaan.setBackground(new java.awt.Color(255, 244, 232));
        page_penyewaan.setForeground(new java.awt.Color(230, 230, 230));
        page_penyewaan.setPreferredSize(new java.awt.Dimension(836, 666));
        page_penyewaan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pelanggan/Data Pelanggan.png"))); // NOI18N
        page_penyewaan.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 27, 320, 37));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 74.png"))); // NOI18N
        page_penyewaan.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, 41, 37));

        btn_search.setContentAreaFilled(false);

        btn_search.setBorderPainted(false);
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search.png"))); // NOI18N
        btn_search.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search Select.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 50, 40));

        txt_search.setBackground(new java.awt.Color(238, 236, 227));
        txt_search.setBorder(null);
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        page_penyewaan.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 181, 290, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Search.png"))); // NOI18N
        page_penyewaan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 410, -1));

        btn_hapus.setContentAreaFilled(false);
        btn_hapus.setBorderPainted(false);
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Hapus.png"))); // NOI18N
        btn_hapus.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Hapus Select.png"))); // NOI18N
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/BG Button.png"))); // NOI18N
        page_penyewaan.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 720, 65));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 28.png"))); // NOI18N
        page_penyewaan.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 69));

        jLabel27.setText("Username");
        page_penyewaan.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, 20));

        jTable_custom1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_custom1);

        page_penyewaan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 730, 370));

        page_main.add(page_penyewaan, "card2");

        add(page_main, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed




    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private custom.JTable_custom jTable_custom1;
    private javax.swing.JPanel page_main;
    private javax.swing.JPanel page_penyewaan;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
