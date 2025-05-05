
package backend;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MenuPenyewaan extends javax.swing.JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public MenuPenyewaan() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        
    }
    
    private String generateID(String tableName, String idColumn, String prefix) {
    String newID = prefix + "001";
    try {
        String sql = "SELECT " + idColumn + " FROM " + tableName + " ORDER BY " + idColumn + " DESC LIMIT 1";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            String lastID = rs.getString(1);
            int num = Integer.parseInt(lastID.substring(prefix.length())) + 1;
            newID = prefix + String.format("%03d", num);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e.getMessage());
    }
    return newID;
}

    private void load_table() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Sewa");
        model.addColumn("ID Pelanggan");
        model.addColumn("ID Pengguna");
        model.addColumn("Tanggal Sewa");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Total Harga");
        model.addColumn("Jaminan");

        try {
            String sql = "select * from penyewaan";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }  
            
            table_sewa.setModel(model);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }  
    
    private void load_tableBrg() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Barang");       // <-- Tambahkan ini
        model.addColumn("Nama Barang");
        model.addColumn("Qty");
        model.addColumn("Harga");
        model.addColumn("Subtotal");

        table_barang.setModel(model);       // <-- Jangan lupa set ke tabel
    }


    private void hitungTotalHarga() {
    double total = 0.0;

    DefaultTableModel model = (DefaultTableModel) table_barang.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        double subtotal = Double.parseDouble(model.getValueAt(i, 4).toString()); // kolom subtotal (index ke-4)
        total += subtotal;
    }

    txt_total.setText("Rp " + String.format("%,.0f", total)); // Contoh label tampilan total
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        page_main = new javax.swing.JPanel();
        page_penyewaan = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_detail = new javax.swing.JButton();
        btn_nota = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_sewa = new custom.JTable_custom();
        page_tambah = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        form_tambah = new javax.swing.JPanel();
        btn_calender = new javax.swing.JButton();
        btn_calender2 = new javax.swing.JButton();
        no_hp = new javax.swing.JTextField();
        tgl_kembali = new javax.swing.JTextField();
        tgl_pinjam = new javax.swing.JTextField();
        nama_penyewa = new javax.swing.JTextField();
        cek_ktm = new javax.swing.JRadioButton();
        cek_ktp = new javax.swing.JRadioButton();
        cek_sim = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_next = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        page_barang = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        form_table_tambah = new javax.swing.JPanel();
        txt_searchBrg = new javax.swing.JTextField();
        btn_tambah_barang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_barang = new custom.JTable_custom();
        btn_searchBrg = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();

        dateChooser.setForeground(new java.awt.Color(195, 45, 45));
        dateChooser.setDateFormat("yyyy-MM-dd");
        dateChooser.setTextRefernce(tgl_pinjam);

        dateChooser2.setForeground(new java.awt.Color(195, 45, 45));
        dateChooser2.setDateFormat("yyyy-MM-dd");
        dateChooser2.setTextRefernce(tgl_kembali);

        setPreferredSize(new java.awt.Dimension(836, 666));
        setLayout(new java.awt.CardLayout());

        page_main.setBackground(new java.awt.Color(255, 244, 232));
        page_main.setPreferredSize(new java.awt.Dimension(836, 666));
        page_main.setLayout(new java.awt.CardLayout());

        page_penyewaan.setBackground(new java.awt.Color(255, 244, 232));
        page_penyewaan.setForeground(new java.awt.Color(230, 230, 230));
        page_penyewaan.setPreferredSize(new java.awt.Dimension(836, 666));
        page_penyewaan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Penyewaan.png"))); // NOI18N
        page_penyewaan.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 27, 250, 37));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 74.png"))); // NOI18N
        page_penyewaan.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, 41, 37));

        btn_search.setContentAreaFilled(false);

        btn_search.setBorderPainted(false);
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search.png"))); // NOI18N
        btn_search.setBorder(null);
        btn_search.setContentAreaFilled(false);
        btn_search.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search Select.png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 50, 40));

        txt_search.setBackground(new java.awt.Color(238, 236, 227));
        txt_search.setBorder(null);
        page_penyewaan.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 181, 290, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Search.png"))); // NOI18N
        page_penyewaan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 410, -1));

        btn_tambah.setContentAreaFilled(false);

        btn_tambah.setBorderPainted(false);
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Tambah.png"))); // NOI18N
        btn_tambah.setBorder(null);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Tambah Select.png"))); // NOI18N
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 210, 50));

        btn_hapus.setContentAreaFilled(false);
        btn_hapus.setBorderPainted(false);
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Hapus.png"))); // NOI18N
        btn_hapus.setBorder(null);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Hapus Select.png"))); // NOI18N
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, 40));

        btn_detail.setContentAreaFilled(false);

        btn_detail.setBorderPainted(false);
        btn_detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Detail.png"))); // NOI18N
        btn_detail.setBorder(null);
        btn_detail.setContentAreaFilled(false);
        btn_detail.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Detail Select.png"))); // NOI18N
        btn_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detailActionPerformed(evt);
            }
        });
        page_penyewaan.add(btn_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, 40));

        btn_nota.setContentAreaFilled(false);

        btn_nota.setBorderPainted(false);
        btn_nota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Nota.png"))); // NOI18N
        btn_nota.setBorder(null);
        btn_nota.setContentAreaFilled(false);
        btn_nota.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Nota Select.png"))); // NOI18N
        page_penyewaan.add(btn_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/BG Button.png"))); // NOI18N
        page_penyewaan.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 720, 65));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 28.png"))); // NOI18N
        page_penyewaan.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 69));

        jLabel27.setText("Username");
        page_penyewaan.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, 20));

        table_sewa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_sewa);

        page_penyewaan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 730, 370));

        page_main.add(page_penyewaan, "card2");

        page_tambah.setBackground(new java.awt.Color(255, 244, 232));
        page_tambah.setPreferredSize(new java.awt.Dimension(836, 666));
        page_tambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Data Penyewa.png"))); // NOI18N
        page_tambah.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 27, 312, 37));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 74.png"))); // NOI18N
        page_tambah.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, 41, 37));

        form_tambah.setBackground(new java.awt.Color(255, 244, 232));
        form_tambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_calender.setContentAreaFilled(false);

        btn_calender.setBorderPainted(false);
        btn_calender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Calender.png"))); // NOI18N
        btn_calender.setBorder(null);
        btn_calender.setContentAreaFilled(false);
        btn_calender.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Calender Select.png"))); // NOI18N
        btn_calender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calenderActionPerformed(evt);
            }
        });
        btn_calender.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btn_calenderPropertyChange(evt);
            }
        });
        form_tambah.add(btn_calender, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 258, 40, 40));

        btn_calender2.setContentAreaFilled(false);

        btn_calender2.setBorderPainted(false);
        btn_calender2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Calender.png"))); // NOI18N
        btn_calender2.setBorder(null);
        btn_calender2.setContentAreaFilled(false);
        btn_calender2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Calender Select.png"))); // NOI18N
        btn_calender2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calender2ActionPerformed(evt);
            }
        });
        form_tambah.add(btn_calender2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 304, 40, 40));

        no_hp.setText("No. HP/WA");
        no_hp.setForeground(Color.GRAY);
        no_hp.setBackground(new java.awt.Color(255, 244, 232));
        no_hp.setForeground(new java.awt.Color(153, 153, 153));
        no_hp.setToolTipText("");
        no_hp.setBorder(null);
        no_hp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                no_hpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                no_hpFocusLost(evt);
            }
        });
        no_hp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_hpActionPerformed(evt);
            }
        });
        form_tambah.add(no_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 355, 440, 30));

        tgl_kembali.setText("Tanggal Kembali");
        tgl_kembali.setForeground(Color.GRAY);
        tgl_kembali.setBackground(new java.awt.Color(255, 244, 232));
        tgl_kembali.setForeground(new java.awt.Color(153, 153, 153));
        tgl_kembali.setToolTipText("");
        tgl_kembali.setBorder(null);
        tgl_kembali.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tgl_kembaliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tgl_kembaliFocusLost(evt);
            }
        });
        tgl_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_kembaliActionPerformed(evt);
            }
        });
        form_tambah.add(tgl_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 390, 30));

        tgl_pinjam.setText("Tanggal Pinjam");
        tgl_pinjam.setForeground(Color.GRAY);
        tgl_pinjam.setBackground(new java.awt.Color(255, 244, 232));
        tgl_pinjam.setForeground(new java.awt.Color(153, 153, 153));
        tgl_pinjam.setToolTipText("");
        tgl_pinjam.setBorder(null);
        tgl_pinjam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tgl_pinjamFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tgl_pinjamFocusLost(evt);
            }
        });
        tgl_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_pinjamActionPerformed(evt);
            }
        });
        form_tambah.add(tgl_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 264, 390, 30));

        nama_penyewa.setText("Nama Penyewa");
        nama_penyewa.setForeground(Color.GRAY);
        nama_penyewa.setBackground(new java.awt.Color(255, 244, 232));
        nama_penyewa.setForeground(new java.awt.Color(153, 153, 153));
        nama_penyewa.setToolTipText("");
        nama_penyewa.setBorder(null);
        nama_penyewa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nama_penyewaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nama_penyewaFocusLost(evt);
            }
        });
        form_tambah.add(nama_penyewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 219, 440, 30));

        cek_ktm.setBorder(null);
        form_tambah.add(cek_ktm, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 419, 20, 20));

        cek_ktp.setBorder(null);
        form_tambah.add(cek_ktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 419, 20, 20));

        cek_sim.setBorder(null);
        form_tambah.add(cek_sim, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 419, 20, 20));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Form Tambah Penyewa.png"))); // NOI18N
        form_tambah.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 560, 490));

        page_tambah.add(form_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 580, 490));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/_Syarat dan Ketentuan berlaku..png"))); // NOI18N
        page_tambah.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 230, 20));

        btn_next.setContentAreaFilled(false);

        btn_next.setBorderPainted(false);
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Next.png"))); // NOI18N
        btn_next.setBorder(null);
        btn_next.setContentAreaFilled(false);
        btn_next.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Next Select.png"))); // NOI18N
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        page_tambah.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 580, 100, 40));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 28.png"))); // NOI18N
        page_tambah.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 69));

        jLabel31.setText("Username");
        page_tambah.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, 20));

        page_main.add(page_tambah, "card3");

        page_barang.setBackground(new java.awt.Color(255, 244, 232));
        page_barang.setPreferredSize(new java.awt.Dimension(836, 666));
        page_barang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Tambah Barang Sewa.png"))); // NOI18N
        page_barang.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 27, 440, 37));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 74.png"))); // NOI18N
        page_barang.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 27, 41, 37));

        form_table_tambah.setBackground(new java.awt.Color(255, 244, 232));
        form_table_tambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_searchBrg.setBackground(new java.awt.Color(238, 236, 227));
        txt_searchBrg.setBorder(null);
        txt_searchBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchBrgActionPerformed(evt);
            }
        });
        form_table_tambah.add(txt_searchBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 49, 120, 20));

        btn_tambah_barang.setContentAreaFilled(false);

        btn_tambah_barang.setBorderPainted(false);
        btn_tambah_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Tambah Barang.png"))); // NOI18N
        btn_tambah_barang.setBorder(null);
        btn_tambah_barang.setContentAreaFilled(false);
        btn_tambah_barang.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Tambah Barang Select.png"))); // NOI18N
        btn_tambah_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_barangActionPerformed(evt);
            }
        });
        form_table_tambah.add(btn_tambah_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 39, 80, 40));

        table_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_barang);

        form_table_tambah.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 490, 300));

        btn_search.setContentAreaFilled(false);

        btn_search.setBorderPainted(false);
        btn_searchBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search.png"))); // NOI18N
        btn_searchBrg.setBorder(null);
        btn_searchBrg.setContentAreaFilled(false);
        btn_searchBrg.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Search Select.png"))); // NOI18N
        btn_searchBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchBrgActionPerformed(evt);
            }
        });
        form_table_tambah.add(btn_searchBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 43, 40, 30));

        txt_nama.setText("Nama Barang");
        txt_nama.setForeground(Color.GRAY);
        txt_nama.setBackground(new java.awt.Color(238, 236, 227));
        txt_nama.setBorder(null);
        txt_nama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_namaFocusLost(evt);
            }
        });
        form_table_tambah.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 50, 80, 20));

        txt_qty.setText("Qty");
        txt_qty.setForeground(Color.GRAY);
        txt_qty.setBackground(new java.awt.Color(238, 236, 227));
        txt_qty.setBorder(null);
        txt_qty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_qtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_qtyFocusLost(evt);
            }
        });
        form_table_tambah.add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 50, 53, 20));

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(238, 236, 227));
        txt_total.setBorder(null);
        form_table_tambah.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 438, 90, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Form Tambah Barang Sewa (1).png"))); // NOI18N
        form_table_tambah.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 610, 510));

        page_barang.add(form_table_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 630, -1));

        btn_simpan.setContentAreaFilled(false);

        btn_simpan.setBorderPainted(false);
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Simpan.png"))); // NOI18N
        btn_simpan.setBorder(null);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/penyewaan/Button Simpan Select.png"))); // NOI18N
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        page_barang.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 600, 110, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashpeg/Group 28.png"))); // NOI18N
        page_barang.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 69));

        jLabel35.setText("Username");
        page_barang.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, 20));

        page_main.add(page_barang, "card3");

        add(page_main, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        page_main.removeAll();
        page_main.add(page_tambah);
        page_main.repaint();
        page_main.revalidate();
        
        
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        page_main.removeAll();
        page_main.add(page_barang);
        page_main.repaint();
        page_main.revalidate();
        load_tableBrg();
    }//GEN-LAST:event_btn_nextActionPerformed

    private void tgl_pinjamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tgl_pinjamFocusGained
        if (tgl_pinjam.getText().equals("Tanggal Pinjam")) {
            tgl_pinjam.setText("");
            tgl_pinjam.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tgl_pinjamFocusGained

    private void tgl_pinjamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tgl_pinjamFocusLost
        if (tgl_pinjam.getText().isEmpty()) {
            tgl_pinjam.setText("Tanggal Pinjam");
            tgl_pinjam.setForeground(Color.GRAY);
        }else {
        tgl_pinjam.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tgl_pinjamFocusLost

    private void nama_penyewaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nama_penyewaFocusGained
        if (nama_penyewa.getText().equals("Nama Penyewa")) {
            nama_penyewa.setText("");
            nama_penyewa.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_nama_penyewaFocusGained

    private void nama_penyewaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nama_penyewaFocusLost
        if (nama_penyewa.getText().isEmpty()) {
            nama_penyewa.setText("Nama Penyewa");
            nama_penyewa.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_nama_penyewaFocusLost

    private void tgl_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_pinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_pinjamActionPerformed

    private void tgl_kembaliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tgl_kembaliFocusGained
        if (tgl_kembali.getText().equals("Tanggal Kembali")) {
            tgl_kembali.setText("");
            tgl_kembali.setForeground(Color.BLACK);
        }           
    }//GEN-LAST:event_tgl_kembaliFocusGained

    private void tgl_kembaliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tgl_kembaliFocusLost
         if (tgl_kembali.getText().isEmpty()) {
            tgl_kembali.setText("Tanggal Kembali");
            tgl_kembali.setForeground(Color.GRAY);
         }else {
            tgl_kembali.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tgl_kembaliFocusLost

    private void tgl_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_kembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_kembaliActionPerformed

    private void no_hpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_no_hpFocusGained
        if (no_hp.getText().equals("No. HP/WA")) {
            no_hp.setText("");
            no_hp.setForeground(Color.BLACK);
        }  
    }//GEN-LAST:event_no_hpFocusGained

    private void no_hpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_no_hpFocusLost
        if (no_hp.getText().isEmpty()) {
            no_hp.setText("No. HP/WA");
            no_hp.setForeground(Color.GRAY);
         }
    }//GEN-LAST:event_no_hpFocusLost

    private void no_hpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_hpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_hpActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_detailActionPerformed

    private void btn_calenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calenderActionPerformed
        // TODO add your handling code here:
      dateChooser.showPopup();
    }//GEN-LAST:event_btn_calenderActionPerformed

    private void btn_calender2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calender2ActionPerformed
        // TODO add your handling code here:
        dateChooser2.showPopup();
    }//GEN-LAST:event_btn_calender2ActionPerformed

    private void btn_calenderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btn_calenderPropertyChange
     
    }//GEN-LAST:event_btn_calenderPropertyChange

    private void txt_namaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaFocusGained
        // TODO add your handling code here:
        if (txt_nama.getText().equals("Nama Barang")) {
            txt_nama.setText("");
            txt_nama.setForeground(Color.BLACK);
        }   
    }//GEN-LAST:event_txt_namaFocusGained

    private void txt_namaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaFocusLost
         if (txt_nama.getText().isEmpty()) {
            txt_nama.setText("");
            txt_nama.setForeground(Color.GRAY);
        }   
    }//GEN-LAST:event_txt_namaFocusLost

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed

    try {
        String idPelanggan = generateID("pelanggan", "id_pelanggan", "PL");
        String idSewa = generateID("penyewaan", "id_sewa", "PN");
         String idKembali = generateID("pengembalian", "id_kembali", "PM");

        String namaPenyewa = nama_penyewa.getText();
        String tglPinjam = tgl_pinjam.getText();
        String tglKembali = tgl_kembali.getText();
        String noHp = no_hp.getText();

        String jaminan = "";
        if (cek_sim.isSelected()) jaminan = "SIM";
        else if (cek_ktp.isSelected()) jaminan = "KTP";
        else if (cek_ktm.isSelected()) jaminan = "KTM";

        // Hitung total harga
        double totalHarga = 0.0;
        for (int i = 0; i < table_barang.getRowCount(); i++) {
            int qty = Integer.parseInt(table_barang.getValueAt(i, 2).toString());
            double harga = Double.parseDouble(table_barang.getValueAt(i, 3).toString());
            totalHarga += qty * harga;
        }

        // 1. Insert pelanggan
        String sqlPelanggan = "INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, no_hp, poin) VALUES (?, ?, ?, ?)";
        PreparedStatement psPelanggan = con.prepareStatement(sqlPelanggan);
        psPelanggan.setString(1, idPelanggan);
        psPelanggan.setString(2, namaPenyewa);
        psPelanggan.setString(3, noHp);
        psPelanggan.setInt(4, 10); // Default poin
        psPelanggan.executeUpdate();

        // 2. Insert penyewaan
        String sqlSewa = "INSERT INTO penyewaan (id_sewa, id_pelanggan, id_pengguna, tgl_sewa, tgl_kembali, total_harga, jaminan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement psSewa = con.prepareStatement(sqlSewa);
        psSewa.setString(1, idSewa);
        psSewa.setString(2, idPelanggan);
        psSewa.setString(3, "UR001"); // Dummy id_pengguna
        psSewa.setString(4, tglPinjam);
        psSewa.setString(5, tglKembali);
        psSewa.setDouble(6, totalHarga);
        psSewa.setString(7, jaminan);
        psSewa.executeUpdate();

        // 3. Insert pengembalian
       
        String sqlKembali = "INSERT INTO pengembalian (id_kembali, id_sewa, tgl_kembali) VALUES (?, ?, ?)";
        PreparedStatement psKembali = con.prepareStatement(sqlKembali);
        psKembali.setString(1, idKembali);
        psKembali.setString(2, idSewa);
        psKembali.setString(3, tglKembali);
        psKembali.executeUpdate();

        // 4. Insert ke detail_sewa
        for (int i = 0; i < table_barang.getRowCount(); i++) {
            String idBarang = table_barang.getValueAt(i, 0).toString();
            int qty = Integer.parseInt(table_barang.getValueAt(i, 2).toString());
            double harga = Double.parseDouble(table_barang.getValueAt(i, 3).toString());
            double subTotal = qty * harga;

            String idDetail = generateID("detail_sewa", "id_detail", "DT");
            String sqlDetail = "INSERT INTO detail_sewa (id_detail, id_sewa, id_barang, qty, sub_total) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psDetail = con.prepareStatement(sqlDetail);
            psDetail.setString(1, idDetail);
            psDetail.setString(2, idSewa);
            psDetail.setString(3, idBarang);
            psDetail.setInt(4, qty);
            psDetail.setDouble(5, subTotal);
            psDetail.executeUpdate();
        }
        

        JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
        
        page_main.removeAll();
        page_main.add(page_penyewaan);
        page_main.repaint();
        page_main.revalidate();
        load_table();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        e.printStackTrace();
    }

    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_qtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_qtyFocusGained
        // TODO add your handling code here:
        if (txt_qty.getText().equals("Qty")) {
            txt_qty.setText("");
            txt_qty.setForeground(Color.BLACK);
        } 
    }//GEN-LAST:event_txt_qtyFocusGained

    private void txt_qtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_qtyFocusLost
        // TODO add your handling code here:
        if (txt_qty.getText().isEmpty()) {
            txt_qty.setText("");
            txt_qty.setForeground(Color.GRAY);
        } 
    }//GEN-LAST:event_txt_qtyFocusLost

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_searchActionPerformed

    
    private int hargaBarangTerpilih = 0; // Variabel global untuk menyimpan harga sementara
    private String idBarangTerpilih = ""; // Kalau kamu perlu simpan ID barangnya
    
    private void btn_searchBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchBrgActionPerformed
        String keyword = txt_searchBrg.getText(); // TextField pencarian

    try {
        String sql = "SELECT * FROM barang WHERE nama_barang LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Tampilkan nama barang ke TextField
            txt_nama.setText(rs.getString("nama_barang"));
            hargaBarangTerpilih = rs.getInt("harga_sewa");
            idBarangTerpilih = rs.getString("id_barang");
        } else {
            JOptionPane.showMessageDialog(null, "Barang tidak ditemukan!");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_searchBrgActionPerformed

    private void txt_searchBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchBrgActionPerformed
        String nama = txt_nama.getText();
    String qtyStr = txt_qty.getText();

    if (nama.isEmpty() || qtyStr.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi nama barang dan qty terlebih dahulu.");
        return;
    }

    int qty = Integer.parseInt(qtyStr);
    int subtotal = qty * hargaBarangTerpilih;

    DefaultTableModel model = (DefaultTableModel) table_barang.getModel();
    model.addRow(new Object[]{nama, qty, hargaBarangTerpilih, subtotal});

    // Reset field
    txt_nama.setText("");
    txt_qty.setText("");

    // Hitung total semua item
    int total = 0;
    for (int i = 0; i < model.getRowCount(); i++) {
        total += (int) model.getValueAt(i, 3); // Kolom subtotal
    }

    txt_total.setText("Rp " + total);
    }//GEN-LAST:event_txt_searchBrgActionPerformed

    private void btn_tambah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_barangActionPerformed
        // TODO add your handling code here:
        try {
    String namaBarang = txt_nama.getText().trim();
    String qtyStr = txt_qty.getText().trim();

    if (namaBarang.isEmpty() || qtyStr.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isi nama barang dan qty!");
        return;
    }

    int qtyInt = Integer.parseInt(qtyStr);
    if (qtyInt <= 0) {
        JOptionPane.showMessageDialog(null, "Qty harus lebih dari nol.");
        return;
    }

    // Ambil id_barang dan harga dari DB
    String sql = "SELECT id_barang, harga_sewa, stok FROM barang WHERE nama_barang = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, namaBarang);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        String idBarang = rs.getString("id_barang");
        double harga = rs.getDouble("harga_sewa");
        int stok = rs.getInt("stok");

        if (qtyInt > stok) {
            JOptionPane.showMessageDialog(null, "Stok tidak mencukupi! Stok tersedia: " + stok);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table_barang.getModel();
        boolean barangSudahAda = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            String idTabel = model.getValueAt(i, 0).toString();
            if (idTabel.equals(idBarang)) {
                int qtyLama = Integer.parseInt(model.getValueAt(i, 2).toString());
                int qtyBaru = qtyLama + qtyInt;
                double subTotalBaru = harga * qtyBaru;

                model.setValueAt(qtyBaru, i, 2);
                model.setValueAt(subTotalBaru, i, 4);
                barangSudahAda = true;
                break;
            }
        }

        if (!barangSudahAda) {
            double subTotal = qtyInt * harga;
            model.addRow(new Object[]{idBarang, namaBarang, qtyInt, harga, subTotal});
        }

        hitungTotalHarga(); // Jika ada
        txt_nama.setText("");
        txt_qty.setText("");
        JOptionPane.showMessageDialog(null, "Barang berhasil ditambahkan!");
    } else {
        JOptionPane.showMessageDialog(null, "Barang tidak ditemukan!");
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    e.printStackTrace();
}

    }//GEN-LAST:event_btn_tambah_barangActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_calender;
    private javax.swing.JButton btn_calender2;
    private javax.swing.JButton btn_detail;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_nota;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_searchBrg;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tambah_barang;
    private javax.swing.JRadioButton cek_ktm;
    private javax.swing.JRadioButton cek_ktp;
    private javax.swing.JRadioButton cek_sim;
    private com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JPanel form_table_tambah;
    private javax.swing.JPanel form_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nama_penyewa;
    private javax.swing.JTextField no_hp;
    private javax.swing.JPanel page_barang;
    private javax.swing.JPanel page_main;
    private javax.swing.JPanel page_penyewaan;
    private javax.swing.JPanel page_tambah;
    private custom.JTable_custom table_barang;
    private custom.JTable_custom table_sewa;
    private javax.swing.JTextField tgl_kembali;
    private javax.swing.JTextField tgl_pinjam;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_searchBrg;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
