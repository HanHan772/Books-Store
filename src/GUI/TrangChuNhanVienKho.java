/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.ChiTietPhieuGiaoDAO;
import DAO.ChiTietPhieuNhapDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.PhieuGiaoDAO;
import DAO.PhieuNhapDAO;
import DAO.SachDAO;
import POJO.ChiTietPhieuGiao;
import POJO.ChiTietPhieuNhap;
import POJO.NhanVien;
import POJO.PhieuGiaoSach;
import POJO.PhieuNhapSach;
import POJO.Sach;
import SP.Mes;
import java.awt.Color;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class TrangChuNhanVienKho extends javax.swing.JFrame {

    private static ArrayList<ChiTietPhieuGiao> ChiTietPhieuGiaoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Creates new form TrangChu
     */
    Vector tblDataPN = new Vector();
    Vector tblTitlePN= new Vector();
    Vector tblDataCTPN = new Vector();
    Vector tblTitleCTPN= new Vector();
    Vector tblDataS = new Vector();
    Vector tblTitleS = new Vector();
    Vector tblDataPG = new Vector();
    Vector tblTitlePG= new Vector();
    Vector tblDataCTPG = new Vector();
    Vector tblTitleCTPG= new Vector();
    DefaultTableModel tblModel;
    static ArrayList<PhieuNhapSach> dsPN = PhieuNhapDAO.getDSPN();
    static ArrayList<ChiTietPhieuNhap> dsCTPN = ChiTietPhieuNhapDAO.getDSCTPN();
    static ArrayList<ChiTietPhieuGiao> dsCTPG = ChiTietPhieuGiaoDAO.getDSCTPG();
    static ArrayList<PhieuGiaoSach> dsPG = PhieuGiaoDAO.getDSPG();
    static ArrayList<Sach> dsS = SachDAO.getDSS();;
    int check = -1;
    
    public TrangChuNhanVienKho() {
        initComponents();
        jpPhieuNhap.setVisible(true);
        jpPhieuGiao.setVisible(false);
        tab3.setBackground(new Color(255,153,153));
        tab4.setBackground(new Color(255,153,153));        
        setDefault();
    }

    public void setDefault()
    {
        loadComboboxNhaCungCap();
        loadComboboxMaPhieuNhap();
        setTitleSach();
        setTitleChiTietPhieuNhap();
        setTitlePhieuNhap();
        loadTablePhieuNhap();
        loadTableChiTietPhieuNhap();
        loadTableSach();
        setTitlePhieuGiao();
        setTitleChiTietPhieuGiao();
        loadTablePhieuGiao();
        loadTableChiTietPhieuGiao();
        txtNgayNhapSach.setText(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        txtTrangThai.setEnabled(false);
        txtMaPhieuNhap.setEnabled(false);
        txtTongTienNhap.setEnabled(false);
        txtTenNhanVien.setEnabled(false);
        txtNgayNhapSach.setEnabled(false);
        cboNhaCungCap.setEnabled(false);
        txtTenSachNhap.setEnabled(false);
        txtSoLuongNhap.setEnabled(false);
        txtGiaNhapSach.setEnabled(false);
        txtMaPhieuGiao.setEnabled(false);
        cboMaPhieuNhap.setEnabled(false);
        txtNgayGiaoSach.setEnabled(false);
        txtNgayGiaoSach.setText(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        txtThoiGianGiaoSach.setEnabled(false);
        txtTenSachGiao.setEnabled(false);
        txtSoLuongGiao.setEnabled(false);
    }
    
    public void setNhanVien(String text) 
    {
        txtTenNhanVien.setText(text);
    }
    void loadComboboxNhaCungCap()
    {
        cboNhaCungCap.removeAllItems();
        for(String t:NhaCungCapDAO.getDSNhaCungCap())
            cboNhaCungCap.addItem(t);
    }
    
    void loadComboboxMaPhieuNhap()
    {
        cboMaPhieuNhap.removeAllItems();
        for(String t:PhieuNhapDAO.getMaPhieuNhap())
            cboMaPhieuNhap.addItem(t);
    }
    
    public void setTitlePhieuNhap()
    {
        tblTitlePN.add("Mã Phiếu Nhập"); 
        tblTitlePN.add("Ngày Nhập"); 
        tblTitlePN.add("Mã Nhà Cung Cấp");
        tblTitlePN.add("Mã Nhân Viên");
        tblTitlePN.add("Trạng Thái");
        tblTitlePN.add("Tổng Tiền Nhập");
    }
        
    public void setTitleChiTietPhieuNhap()
    {
        tblTitleCTPN.add("Mã Phiếu Nhập"); 
        tblTitleCTPN.add("Mã Sách"); 
        tblTitleCTPN.add("Số Lượng");
        tblTitleCTPN.add("Giá Nhập");
    }
    
    public void setTitleSach()
    {
        tblTitleS.add("Mã Sách");
        tblTitleS.add("Tên Sách");
        tblTitleS.add("Đơn Giá Bán");
        tblTitleS.add("Số Lượng");
    }
    
    public void setTitlePhieuGiao()
    {
        tblTitlePG.add("Mã Phiếu Giao"); 
        tblTitlePG.add("Mã Phiếu Nhập"); 
        tblTitlePG.add("Ngày Giao");
        tblTitlePG.add("Thời Gian Giao");
    }
    
    public void loadTablePhieuGiao()
    {
        tblDataPG.removeAllElements();
        for(PhieuGiaoSach b : dsPG){
            Vector v = new Vector();
            v.add(b.getMaPG()); 
            v.add(b.getMaPN()); 
            v.add(b.getNgayGiao()); 
            v.add(b.getThoiGianGiao());
            
            tblDataPG.add(v);
        }
        tbPhieuGiaoSach.setModel(new DefaultTableModel(tblDataPG, tblTitlePG));
        
    }
    
        public void setTitleChiTietPhieuGiao()
    {
        tblTitleCTPG.add("Mã Phiếu Giao"); 
        tblTitleCTPG.add("Mã Sách");
        tblTitleCTPG.add("Số Lượng");
    }
    
    public void loadTableChiTietPhieuGiao()
    {
        tblDataCTPG.removeAllElements();
        for(ChiTietPhieuGiao b : dsCTPG){
            Vector v = new Vector();
            v.add(b.getMaPG()); 
            v.add(b.getMaSach()); 
            v.add(b.getSoLuong()); 
            
            tblDataCTPG.add(v);
        }
        tbChiTietPhieuGiao.setModel(new DefaultTableModel(tblDataCTPG, tblTitleCTPG));
        
    }
    
    public void loadTableSach()
    {
        tblDataS.removeAllElements();
        for(Sach b : dsS){
            Vector v = new Vector();
            v.add(b.getMaSach()); 
            v.add(b.getTenSach()); 
            v.add(String.format("%.0f", b.getDonGiaBan()));
            v.add(b.getSoLuongTon()); 
            tblDataS.add(v);
        }
        tbSachNhap.setModel(new DefaultTableModel(tblDataS, tblTitleS));
        tbSachGiao.setModel(new DefaultTableModel(tblDataS, tblTitleS));
        
    }
        
    public void loadTableChiTietPhieuNhap()
    {
        tblDataCTPN.removeAllElements();
        for(ChiTietPhieuNhap b : dsCTPN){
            Vector v = new Vector();
            v.add(b.getMaPN()); 
            v.add(b.getMaSach()); 
            v.add(b.getSoLuong());
            v.add(String.format("%.0f", b.getGiaNhap())); 
            tblDataCTPN.add(v);
        }
        tbChiTietPhieuNhap.setModel(new DefaultTableModel(tblDataCTPN, tblTitleCTPN));
    }
        
    public void loadTablePhieuNhap()
    {
        
        tblDataPN.removeAllElements();
        for(PhieuNhapSach b : dsPN){
            Vector v = new Vector();
            v.add(b.getMaPN()); 
            v.add(b.getNgayNhap()); 
            v.add(b.getMaNCC());
            v.add(b.getMaNV()); 
            v.add(b.getTrangThai());
            v.add(String.format("%.0f", b.getTongTienNhap()));
            tblDataPN.add(v);
        }
        tbPhieuNhapSach.setModel(new DefaultTableModel(tblDataPN, tblTitlePN));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField14 = new javax.swing.JTextField();
        btnTrangThai = new javax.swing.ButtonGroup();
        left = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dongke = new javax.swing.JSeparator();
        tab7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        right = new javax.swing.JPanel();
        jpPhieuNhap = new javax.swing.JPanel();
        txtTimKiemNhapSach = new javax.swing.JTextField();
        btnLamMoiNhapSach = new javax.swing.JButton();
        btnXoaNhapSach = new javax.swing.JButton();
        btnSuaNhapSach = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPhieuNhapSach = new javax.swing.JTable();
        jpTTPN = new javax.swing.JPanel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtNgayNhapSach = new javax.swing.JTextField();
        btnThemNhapSach = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtTongTienNhap = new javax.swing.JTextField();
        jpTTSCN = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTenSachNhap = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuongNhap = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaNhapSach = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbSachNhap = new javax.swing.JTable();
        btnTimKiemNhapSach = new javax.swing.JButton();
        btnThemChiTietSach = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbChiTietPhieuNhap = new javax.swing.JTable();
        btnLuuNhapSach = new javax.swing.JButton();
        btnLuuNhapSach1 = new javax.swing.JButton();
        btnTimKiemSach = new javax.swing.JButton();
        txtTimKiemSach = new javax.swing.JTextField();
        jpPhieuGiao = new javax.swing.JPanel();
        btnTimKiemGiaoSach = new javax.swing.JButton();
        txtTimKiemGiaoSach = new javax.swing.JTextField();
        btnLamMoiGiaoSach = new javax.swing.JButton();
        btnXoaGiaoSach = new javax.swing.JButton();
        btnSuaGiaoSach = new javax.swing.JButton();
        btnThemChiTietGiao = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPhieuGiaoSach = new javax.swing.JTable();
        btnLuuPhieuGiao = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSachGiao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuongGiao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSachGiao = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbChiTietPhieuGiao = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnThemPhieuGiao = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        cboMaPhieuNhap = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtNgayGiaoSach = new javax.swing.JTextField();
        txtMaPhieuGiao = new javax.swing.JTextField();
        txtThoiGianGiaoSach = new javax.swing.JTextField();

        jTextField14.setText("jTextField14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        left.setBackground(new java.awt.Color(51, 51, 51));

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/book-stack.png"))); // NOI18N

        tab3.setBackground(new java.awt.Color(255, 153, 153));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delivery-24.png"))); // NOI18N
        jLabel4.setText("Đặt Sách");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab4.setBackground(new java.awt.Color(255, 153, 153));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-flower-delivery-48.png"))); // NOI18N
        jLabel6.setText("Nhập Sách");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab7.setBackground(new java.awt.Color(255, 153, 153));
        tab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab7MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-log-out-24.png"))); // NOI18N
        jLabel11.setText("Đăng Xuất");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(icon)
                .addContainerGap(8, Short.MAX_VALUE))
            .addComponent(tab7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dongke, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(dongke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tab7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        right.setBackground(new java.awt.Color(102, 102, 102));
        right.setLayout(new javax.swing.OverlayLayout(right));

        jpPhieuNhap.setBackground(new java.awt.Color(102, 102, 102));

        txtTimKiemNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnLamMoiNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoiNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoiNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoiNhapSach.setText("Làm mới");
        btnLamMoiNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNhapSachActionPerformed(evt);
            }
        });

        btnXoaNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnXoaNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delete-trash-24.png"))); // NOI18N
        btnXoaNhapSach.setText("Xóa");
        btnXoaNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhapSachActionPerformed(evt);
            }
        });

        btnSuaNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSuaNhapSach.setText("Sửa");
        btnSuaNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhapSachActionPerformed(evt);
            }
        });

        tbPhieuNhapSach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPhieuNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbPhieuNhapSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Ngày Nhập", "Mã Nhà Cung Cấp", "Mã Nhân Viên", "Trạng Thái", "Tổng Tiền Nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPhieuNhapSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieuNhapSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPhieuNhapSach);

        jpTTPN.setBackground(new java.awt.Color(102, 102, 102));
        jpTTPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        txtMaPhieuNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Mã phiếu đặt:");

        txtTenNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Nhân viên:");

        cboNhaCungCap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Nhà cung cấp:");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Ngày đặt sách:");

        txtNgayNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnThemNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnThemNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemNhapSach.setText("Thêm một phiếu nhập mới");
        btnThemNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhapSachActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Trạng thái:");

        txtTrangThai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Tổng tiền:");

        txtTongTienNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jpTTPNLayout = new javax.swing.GroupLayout(jpTTPN);
        jpTTPN.setLayout(jpTTPNLayout);
        jpTTPNLayout.setHorizontalGroup(
            jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTTPNLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel40)
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTienNhap)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaPhieuNhap)
                    .addComponent(txtNgayNhapSach)
                    .addComponent(cboNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenNhanVien))
                .addGap(30, 30, 30))
            .addGroup(jpTTPNLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnThemNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        jpTTPNLayout.setVerticalGroup(
            jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTTPNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(txtNgayNhapSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jpTTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemNhapSach)
                .addContainerGap())
        );

        jpTTSCN.setBackground(new java.awt.Color(102, 102, 102));
        jpTTSCN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn sách cần nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên sách:");

        txtTenSachNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Số lượng:");

        txtSoLuongNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Giá nhập:");

        txtGiaNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        tbSachNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Đơn Giá Bán", "Số Lượng Tồn"
            }
        ));
        tbSachNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachNhapMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbSachNhap);

        javax.swing.GroupLayout jpTTSCNLayout = new javax.swing.GroupLayout(jpTTSCN);
        jpTTSCN.setLayout(jpTTSCNLayout);
        jpTTSCNLayout.setHorizontalGroup(
            jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTTSCNLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jpTTSCNLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpTTSCNLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpTTSCNLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpTTSCNLayout.setVerticalGroup(
            jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTTSCNLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtGiaNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpTTSCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnTimKiemNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemNhapSach.setText("Tìm kiếm");
        btnTimKiemNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNhapSachActionPerformed(evt);
            }
        });

        btnThemChiTietSach.setBackground(new java.awt.Color(0, 153, 153));
        btnThemChiTietSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemChiTietSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemChiTietSach.setText("Thêm");
        btnThemChiTietSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChiTietSachActionPerformed(evt);
            }
        });

        tbChiTietPhieuNhap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbChiTietPhieuNhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbChiTietPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Sách", "Số Lượng", "Giá Nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbChiTietPhieuNhap);
        if (tbChiTietPhieuNhap.getColumnModel().getColumnCount() > 0) {
            tbChiTietPhieuNhap.getColumnModel().getColumn(1).setResizable(false);
        }

        btnLuuNhapSach.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuNhapSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnLuuNhapSach.setText("Lưu");
        btnLuuNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhapSachActionPerformed(evt);
            }
        });

        btnLuuNhapSach1.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuNhapSach1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuNhapSach1.setText("Đóng");
        btnLuuNhapSach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhapSach1ActionPerformed(evt);
            }
        });

        btnTimKiemSach.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemSach.setText("Tìm kiếm sách");
        btnTimKiemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSachActionPerformed(evt);
            }
        });

        txtTimKiemSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jpPhieuNhapLayout = new javax.swing.GroupLayout(jpPhieuNhap);
        jpPhieuNhap.setLayout(jpPhieuNhapLayout);
        jpPhieuNhapLayout.setHorizontalGroup(
            jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                        .addComponent(jpTTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                        .addComponent(txtTimKiemNhapSach)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTimKiemNhapSach)
                                        .addGap(20, 20, 20))
                                    .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                        .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                                .addComponent(btnXoaNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnLuuNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                                .addComponent(btnThemChiTietSach, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSuaNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                                .addComponent(btnLamMoiNhapSach)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnLuuNhapSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(26, 26, 26)))
                                .addComponent(jpTTSCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPhieuNhapLayout.createSequentialGroup()
                                .addGap(508, 508, 508)
                                .addComponent(txtTimKiemSach)
                                .addGap(18, 18, 18)
                                .addComponent(btnTimKiemSach)
                                .addGap(85, 85, 85))))))
        );
        jpPhieuNhapLayout.setVerticalGroup(
            jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                        .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemChiTietSach)
                                    .addComponent(btnSuaNhapSach))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoaNhapSach)
                                    .addComponent(btnLuuNhapSach))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLamMoiNhapSach)
                                    .addComponent(btnLuuNhapSach1))
                                .addGap(30, 30, 30)
                                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimKiemNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTimKiemNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpPhieuNhapLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jpTTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPhieuNhapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiemSach)
                            .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpTTSCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        right.add(jpPhieuNhap);

        jpPhieuGiao.setBackground(new java.awt.Color(102, 102, 102));
        jpPhieuGiao.setPreferredSize(new java.awt.Dimension(774, 543));

        btnTimKiemGiaoSach.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemGiaoSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemGiaoSach.setText("Tìm kiếm");
        btnTimKiemGiaoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemGiaoSachActionPerformed(evt);
            }
        });

        txtTimKiemGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTimKiemGiaoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemGiaoSachActionPerformed(evt);
            }
        });

        btnLamMoiGiaoSach.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoiGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoiGiaoSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoiGiaoSach.setText("Làm mới");
        btnLamMoiGiaoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiGiaoSachActionPerformed(evt);
            }
        });

        btnXoaGiaoSach.setBackground(new java.awt.Color(0, 153, 153));
        btnXoaGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaGiaoSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delete-trash-24.png"))); // NOI18N
        btnXoaGiaoSach.setText("Xóa");
        btnXoaGiaoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGiaoSachActionPerformed(evt);
            }
        });

        btnSuaGiaoSach.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaGiaoSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSuaGiaoSach.setText("Sửa");
        btnSuaGiaoSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaGiaoSachActionPerformed(evt);
            }
        });

        btnThemChiTietGiao.setBackground(new java.awt.Color(0, 153, 153));
        btnThemChiTietGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemChiTietGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemChiTietGiao.setText("Thêm");
        btnThemChiTietGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChiTietGiaoActionPerformed(evt);
            }
        });

        tbPhieuGiaoSach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPhieuGiaoSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbPhieuGiaoSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Giao", "Mã Phiếu Nhập", "Ngày Giao", "Thời Gian Giao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPhieuGiaoSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieuGiaoSachMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPhieuGiaoSach);
        if (tbPhieuGiaoSach.getColumnModel().getColumnCount() > 0) {
            tbPhieuGiaoSach.getColumnModel().getColumn(0).setHeaderValue("Mã Phiếu Giao");
            tbPhieuGiaoSach.getColumnModel().getColumn(2).setResizable(false);
        }

        btnLuuPhieuGiao.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuPhieuGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuPhieuGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-save-24.png"))); // NOI18N
        btnLuuPhieuGiao.setText("Lưu");
        btnLuuPhieuGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPhieuGiaoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn sách được giao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên sách:");

        txtTenSachGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số lượng cần nhập:");

        txtSoLuongGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        tbSachGiao.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSachGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachGiaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSachGiao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSachGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuongGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSachGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuongGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbChiTietPhieuGiao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbChiTietPhieuGiao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Phiếu Giao", "Mã Sách", "Số Lượng"
            }
        ));
        tbChiTietPhieuGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietPhieuGiaoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbChiTietPhieuGiao);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu giao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btnThemPhieuGiao.setBackground(new java.awt.Color(0, 153, 153));
        btnThemPhieuGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemPhieuGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemPhieuGiao.setText("Thêm một phiếu giao mới");
        btnThemPhieuGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuGiaoActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Mã phiếu nhập:");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Thời gian giao:");

        cboMaPhieuNhap.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cboMaPhieuNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Ngày giao sách:");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Mã giao sách:");

        txtNgayGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtMaPhieuGiao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtThoiGianGiaoSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThemPhieuGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel49)
                            .addComponent(jLabel48)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThoiGianGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMaPhieuNhap, 0, 169, Short.MAX_VALUE)
                            .addComponent(txtMaPhieuGiao)
                            .addComponent(txtNgayGiaoSach))
                        .addGap(10, 10, 10))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtMaPhieuGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(cboMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtNgayGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtThoiGianGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemPhieuGiao)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPhieuGiaoLayout = new javax.swing.GroupLayout(jpPhieuGiao);
        jpPhieuGiao.setLayout(jpPhieuGiaoLayout);
        jpPhieuGiaoLayout.setHorizontalGroup(
            jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPhieuGiaoLayout.createSequentialGroup()
                        .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                                .addComponent(btnXoaGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLuuPhieuGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                                .addComponent(btnThemChiTietGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSuaGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(btnLamMoiGiaoSach))))
                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(txtTimKiemGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnTimKiemGiaoSach)))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPhieuGiaoLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPhieuGiaoLayout.setVerticalGroup(
            jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                        .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                        .addComponent(btnThemChiTietGiao)
                                        .addGap(20, 20, 20)
                                        .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnXoaGiaoSach)
                                            .addComponent(btnLuuPhieuGiao)))
                                    .addGroup(jpPhieuGiaoLayout.createSequentialGroup()
                                        .addComponent(btnSuaGiaoSach)
                                        .addGap(46, 46, 46)))
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoiGiaoSach)))
                        .addGap(35, 35, 35)
                        .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemGiaoSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemGiaoSach)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jpPhieuGiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        right.add(jpPhieuGiao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        // TODO add your handling code here:
        jpPhieuNhap.setVisible(true);
        jpPhieuGiao.setVisible(false);
        tab3.setBackground(Color.white);
        tab4.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:
        jpPhieuGiao.setVisible(true);
        jpPhieuNhap.setVisible(false);
        tab4.setBackground(Color.white);
        tab3.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_tab4MouseClicked

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        // TODO add your handling code here:
        tab7.setBackground(Color.white);
        tab3.setBackground(new Color(255,153,153));
        tab4.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_tab7MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Mes.Exit();
    }//GEN-LAST:event_formWindowClosing

    private void btnThemNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhapSachActionPerformed
        txtNgayNhapSach.setEnabled(true);
        cboNhaCungCap.setEnabled(true);
        check = 1;
        btnSuaNhapSach.setEnabled(false);
        btnXoaNhapSach.setEnabled(false);
        btnThemChiTietSach.setEnabled(false);
    }//GEN-LAST:event_btnThemNhapSachActionPerformed

    private void tbPhieuNhapSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieuNhapSachMouseClicked
        int i = tbPhieuNhapSach.getSelectedRow();
        String maPN = tbPhieuNhapSach.getValueAt(i, 0).toString().trim();
        String ngayNhap = tbPhieuNhapSach.getValueAt(i, 1).toString().trim();
        String maNCC = NhaCungCapDAO.getTenQuaMa(tbPhieuNhapSach.getValueAt(i, 2).toString().trim());
        String maNV = NhanVienDAO.getTenQuaMaNV(tbPhieuNhapSach.getValueAt(i, 3).toString().trim());
        String trangThai = tbPhieuNhapSach.getValueAt(i, 4).toString().trim();
        String tongTienNhap = tbPhieuNhapSach.getValueAt(i, 5).toString().trim();
        
        txtMaPhieuNhap.setText(maPN);
        txtNgayNhapSach.setText(ngayNhap);
        cboNhaCungCap.setSelectedItem(maNCC);
        txtTenNhanVien.setText(maNV);
        txtTrangThai.setText(trangThai);
        txtTongTienNhap.setText(tongTienNhap);
        dsCTPN = ChiTietPhieuNhapDAO.getCTPNQuaMaPN(Integer.parseInt(maPN));
        loadTableChiTietPhieuNhap();
        
    }//GEN-LAST:event_tbPhieuNhapSachMouseClicked

    private void btnThemChiTietSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChiTietSachActionPerformed
        txtSoLuongNhap.setEnabled(true);
        txtGiaNhapSach.setEnabled(true);
        check = 2;
        btnSuaNhapSach.setEnabled(false);
        btnXoaNhapSach.setEnabled(false);
        btnThemNhapSach.setEnabled(false);
        
    }//GEN-LAST:event_btnThemChiTietSachActionPerformed

    private void tbSachNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachNhapMouseClicked
        int i = tbSachNhap.getSelectedRow();
        String tenSach = tbSachNhap.getValueAt(i, 1).toString().trim();
        
        txtTenSachNhap.setText(tenSach);
    }//GEN-LAST:event_tbSachNhapMouseClicked

    private void btnSuaNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhapSachActionPerformed
       txtTenSachNhap.setEnabled(true);
        txtSoLuongNhap.setEnabled(true);
        check = 4;
        btnThemChiTietSach.setEnabled(false);
        btnXoaNhapSach.setEnabled(false);
        btnThemNhapSach.setEnabled(false);
    }//GEN-LAST:event_btnSuaNhapSachActionPerformed

    private void tbChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietPhieuNhapMouseClicked
        int i = tbChiTietPhieuNhap.getSelectedRow();
        String maPN = tbChiTietPhieuNhap.getValueAt(i, 0).toString().trim();        
        String tenSach = SachDAO.getTenQuaMaSach(Integer.parseInt(tbChiTietPhieuNhap.getValueAt(i, 1).toString()));
        String soLuong = tbChiTietPhieuNhap.getValueAt(i, 2).toString().trim();
        String giaNhap = tbChiTietPhieuNhap.getValueAt(i, 3).toString().trim();
        
        txtMaPhieuNhap.setText(maPN);
        txtSoLuongNhap.setText(soLuong);
        txtTenSachNhap.setText(tenSach);
        txtGiaNhapSach.setText(giaNhap);
        
    }//GEN-LAST:event_tbChiTietPhieuNhapMouseClicked

    private void btnXoaNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhapSachActionPerformed
        check = 3;
        btnSuaNhapSach.setEnabled(false);
        btnThemChiTietSach.setEnabled(false);
        btnThemNhapSach.setEnabled(false);
    }//GEN-LAST:event_btnXoaNhapSachActionPerformed

    private void btnLuuNhapSach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhapSach1ActionPerformed
        Mes.Exit();
    }//GEN-LAST:event_btnLuuNhapSach1ActionPerformed

    private void btnLamMoiNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNhapSachActionPerformed
        txtMaPhieuNhap.setText("");
        txtNgayNhapSach.setText(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        cboNhaCungCap.setSelectedItem("");
        txtTrangThai.setText("");
        txtTongTienNhap.setText("");
        txtTenSachNhap.setText("");
        txtSoLuongNhap.setText("");
        txtGiaNhapSach.setText("");
        btnThemNhapSach.setEnabled(true);
        btnSuaNhapSach.setEnabled(true);
        btnXoaNhapSach.setEnabled(true);
        btnThemChiTietSach.setEnabled(true);
        dsPN = PhieuNhapDAO.getDSPN();
        dsCTPN = ChiTietPhieuNhapDAO.getDSCTPN();
        loadTablePhieuNhap();
        loadTableChiTietPhieuNhap();
        dsS=SachDAO.getDSS();
        loadTableSach();
    }//GEN-LAST:event_btnLamMoiNhapSachActionPerformed

    boolean checkTXT()
    {
        if(txtSoLuongNhap.getText().equals("")||txtGiaNhapSach.getText().equals(""))
            return false;
        return true;
    }
    
    boolean checkTXTPG()
    {
        int i = tbSachGiao.getSelectedRow();
        int sl = Integer.parseInt(tbSachGiao.getValueAt(i, 3).toString());
        if(txtSoLuongGiao.getText().equals("")||Integer.parseInt(txtSoLuongGiao.getText())> sl)
            return false;
        return true;
    }
    
    
    private void btnLuuNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhapSachActionPerformed
        if(check ==1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm một phiếu nhập mới không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                int maNCC = NhaCungCapDAO.getMaQuaTen(cboNhaCungCap.getSelectedItem().toString());
                int maNV = NhanVienDAO.getMaNVQuaTen(txtTenNhanVien.getText());
                //PhieuNhapSach pn = new PhieuNhapSach(maNCC,maNV);
                
                if(PhieuNhapDAO.themPhieuNhap(maNCC,maNV)==true)
                {
                    dsPN = PhieuNhapDAO.getDSPN();
                    loadTablePhieuNhap();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadComboboxMaPhieuNhap();
                }
                else
                     JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể thêm");
                    
            }
            btnSuaNhapSach.setEnabled(true);
            btnXoaNhapSach.setEnabled(true);
            btnThemChiTietSach.setEnabled(true);
        }
        else
        {
            if(check == 2)
            {
                int c = tbSachNhap.getSelectedColumn();
                int d = tbPhieuNhapSach.getSelectedColumn();
                if(c != -1 && d != -1)
                {
                    if(checkTXT())
                    {
                        if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm một chi tiết phiếu nhập không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            int i = tbSachNhap.getSelectedRow();
                            int maPN = Integer.parseInt(txtMaPhieuNhap.getText());
                            int maSach = Integer.parseInt(tbSachNhap.getValueAt(i, 0).toString());
                            int soLuong = Integer.parseInt(txtSoLuongNhap.getText());
                            BigDecimal giaNhap = new BigDecimal(txtGiaNhapSach.getText());
                            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(maPN,maSach,soLuong,giaNhap);

                            if(ChiTietPhieuNhapDAO.themChiTietPhieuNhap(ctpn)==true)
                            {   
                                int j = tbPhieuNhapSach.getSelectedRow();

                                dsCTPN = ChiTietPhieuNhapDAO.getDSCTPN();
                                loadTableChiTietPhieuNhap();
                                dsPN = PhieuNhapDAO.getDSPN();
                                loadTablePhieuNhap();
                                JOptionPane.showMessageDialog(this, "Thêm thành công");
                            }
                            else
                                 JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể thêm");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ dữ liệu !!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong table để xác định dữ liệu cần thêm [Sách - Phiếu Nhập]");
                }
                btnSuaNhapSach.setEnabled(true);
                btnXoaNhapSach.setEnabled(true);
                btnThemNhapSach.setEnabled(true);
            }
            else
            {
                if(check == 3)
                {
                    int c = tbChiTietPhieuNhap.getSelectedColumn();
                    if(c != -1)
                    {
                        if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            int i = tbChiTietPhieuNhap.getSelectedRow();
                            int maPN = Integer.parseInt(tbChiTietPhieuNhap.getValueAt(i, 0).toString());
                            int maSach = Integer.parseInt(tbChiTietPhieuNhap.getValueAt(i, 1).toString());

                            if(ChiTietPhieuNhapDAO.xoaChiTietPhieuNhap(maPN,maSach)==true)
                            {
                                loadTablePhieuNhap();
                                dsCTPN = ChiTietPhieuNhapDAO.getDSCTPN();
                                loadTableChiTietPhieuNhap();
                                dsPN = PhieuNhapDAO.getDSPN();
                                loadTablePhieuNhap();
                                JOptionPane.showMessageDialog(this, "Xóa thành công");
                            }
                            else
                                 JOptionPane.showMessageDialog(this, "Không thể xóa !!");

                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong bảng Chi Tiết Phiếu Nhập để xác định dữ liệu cần xóa");
                    }
                    
                    btnThemChiTietSach.setEnabled(true);
                    btnSuaNhapSach.setEnabled(true);
                    btnThemNhapSach.setEnabled(true);
                }
                else
                {
                    if(check ==4)
                    {
                        int c = tbChiTietPhieuNhap.getSelectedColumn();
                        if(c != -1)
                        {
                            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            {
                                int i = tbChiTietPhieuNhap.getSelectedRow();
                                int maPN = Integer.parseInt(tbChiTietPhieuNhap.getValueAt(i, 0).toString());
                                int maSach = Integer.parseInt(tbChiTietPhieuNhap.getValueAt(i, 1).toString());
                                int soLuong = Integer.parseInt(txtSoLuongNhap.getText());
                                BigDecimal giaNhap = new BigDecimal(txtGiaNhapSach.getText());
                                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(maPN,maSach,soLuong,giaNhap);

                                if(ChiTietPhieuNhapDAO.suaChiTietPhieuNhap(ctpn)==true)
                                {
                                    loadTablePhieuNhap();
                                    dsCTPN = ChiTietPhieuNhapDAO.getDSCTPN();
                                    loadTableChiTietPhieuNhap();

                                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                                }
                                else
                                    JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể sửa");

                            }
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong bảng Chi Tiết Phiếu Nhập để xác định dữ liệu cần sửa");
                        }
                        
                        btnThemChiTietSach.setEnabled(true);
                        btnXoaNhapSach.setEnabled(true);
                        btnThemNhapSach.setEnabled(true);
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog(this, "Hãy chọn yêu cầu bạn muốn thực thi !!", "Thông báo", JOptionPane.OK_OPTION);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLuuNhapSachActionPerformed

    private void btnTimKiemNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNhapSachActionPerformed
        dsPN = PhieuNhapDAO.timKiemPhieuNhap(txtTimKiemNhapSach.getText());
        int i = tbPhieuNhapSach.getColumnCount();
        if(i == 0)
        {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy dữ liệu muốn tìm !!", "Thông báo", JOptionPane.OK_OPTION);
        }
        else
        {
            loadTablePhieuNhap();
            
        }
    }//GEN-LAST:event_btnTimKiemNhapSachActionPerformed

    private void btnThemPhieuGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuGiaoActionPerformed
        //txtNgayGiaoSach.setEnabled(false);
        cboMaPhieuNhap.setEnabled(true);
        //txtThoiGianGiaoSach.setEnabled(false);
        //txtMaPhieuGiao.setEnabled(false);
        check = 1;
        btnSuaGiaoSach.setEnabled(false);
        btnXoaGiaoSach.setEnabled(false);
        btnThemChiTietGiao.setEnabled(false);
    }//GEN-LAST:event_btnThemPhieuGiaoActionPerformed

    private void tbPhieuGiaoSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieuGiaoSachMouseClicked
        int i = tbPhieuGiaoSach.getSelectedRow();
        String maPG = tbPhieuGiaoSach.getValueAt(i, 0).toString().trim();
        String maPN = tbPhieuGiaoSach.getValueAt(i, 1).toString().trim();
        String ngayGiao = tbPhieuGiaoSach.getValueAt(i, 2).toString().trim();
        String thoiGianGiao = tbPhieuGiaoSach.getValueAt(i, 3).toString().trim();
        txtMaPhieuNhap.setEnabled(false);
        txtMaPhieuGiao.setText(maPG);
        cboMaPhieuNhap.setSelectedItem(maPN);
        txtNgayGiaoSach.setText(ngayGiao);
        txtThoiGianGiaoSach.setText(thoiGianGiao);
        dsCTPG = ChiTietPhieuGiaoDAO.getCTPGQuaMaPG(Integer.parseInt(maPG));
        loadTableChiTietPhieuGiao();
        dsS = SachDAO.getDSSachQuaMaPG(Integer.parseInt(maPG));
        loadTableSach();
    }//GEN-LAST:event_tbPhieuGiaoSachMouseClicked

    private void tbChiTietPhieuGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietPhieuGiaoMouseClicked
       int i = tbChiTietPhieuGiao.getSelectedRow();
        String maPG = tbChiTietPhieuGiao.getValueAt(i, 0).toString().trim();        
        String tenSach = SachDAO.getTenQuaMaSach(Integer.parseInt(tbChiTietPhieuGiao.getValueAt(i, 1).toString()));
        String soLuong = tbChiTietPhieuGiao.getValueAt(i, 2).toString().trim();
        
        txtMaPhieuGiao.setText(maPG);
        txtSoLuongGiao.setText(soLuong);
        txtTenSachGiao.setText(tenSach);
    }//GEN-LAST:event_tbChiTietPhieuGiaoMouseClicked

    private void txtTimKiemGiaoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemGiaoSachActionPerformed
 
    }//GEN-LAST:event_txtTimKiemGiaoSachActionPerformed

    private void btnTimKiemGiaoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemGiaoSachActionPerformed
             dsPG = PhieuGiaoDAO.timKiemPhieuGiao(txtTimKiemGiaoSach.getText());
        int i = tbPhieuGiaoSach.getColumnCount();
        if(i == 0)
        {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy dữ liệu muốn tìm !!", "Thông báo", JOptionPane.OK_OPTION);
        }
        else
        {
            loadTablePhieuGiao();
            
        }
    }//GEN-LAST:event_btnTimKiemGiaoSachActionPerformed

    private void btnThemChiTietGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChiTietGiaoActionPerformed
        txtSoLuongGiao.setEnabled(true);
        check = 2;
        btnSuaGiaoSach.setEnabled(false);
        btnXoaGiaoSach.setEnabled(false);
        btnThemPhieuGiao.setEnabled(false);
    }//GEN-LAST:event_btnThemChiTietGiaoActionPerformed

    private void btnSuaGiaoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaGiaoSachActionPerformed
        txtTenSachGiao.setEnabled(true);
        txtSoLuongGiao.setEnabled(true);
        check = 4;
        btnThemChiTietGiao.setEnabled(false);
        btnXoaGiaoSach.setEnabled(false);
        btnThemPhieuGiao.setEnabled(false);
    }//GEN-LAST:event_btnSuaGiaoSachActionPerformed

    private void btnXoaGiaoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGiaoSachActionPerformed
        check = 3;
        btnSuaGiaoSach.setEnabled(false);
        btnThemChiTietGiao.setEnabled(false);
        btnThemPhieuGiao.setEnabled(false);
    }//GEN-LAST:event_btnXoaGiaoSachActionPerformed

    private void btnLuuPhieuGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPhieuGiaoActionPerformed
        if(check ==1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm một phiếu giao mới không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                int maPN = Integer.parseInt(cboMaPhieuNhap.getSelectedItem().toString());
                String time = String.valueOf(LocalTime.now());
                //PhieuNhapSach pn = new PhieuNhapSach(maNCC,maNV);
                
                if(PhieuGiaoDAO.themPhieuGiao(maPN,time)==true)
                {
                    dsPG = PhieuGiaoDAO.getDSPG();
                    loadTablePhieuGiao();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                }
                else
                     JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể thêm");
                    
            }
            btnSuaGiaoSach.setEnabled(true);
            btnXoaGiaoSach.setEnabled(true);
            btnThemChiTietGiao.setEnabled(true);
        }
        else
        {
            if(check == 2)
            {
                int c = tbSachGiao.getSelectedColumn();
                int d = tbPhieuGiaoSach.getSelectedColumn();
                if(c != -1 && d != -1)
                {
                    if(checkTXTPG())
                    {
                        if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm một chi tiết phiếu giao không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            int i = tbSachGiao.getSelectedRow();
                            int maPG = Integer.parseInt(txtMaPhieuGiao.getText());
                            int maSach = Integer.parseInt(tbSachGiao.getValueAt(i, 0).toString());
                            int soLuong = Integer.parseInt(txtSoLuongGiao.getText());
                            ChiTietPhieuGiao ctpg = new ChiTietPhieuGiao(maPG,maSach,soLuong);

                            if(ChiTietPhieuGiaoDAO.themChiTietPhieuGiao(ctpg)==true)
                            {   
                                int j = tbPhieuGiaoSach.getSelectedRow();

                                dsCTPG = ChiTietPhieuGiaoDAO.getDSCTPG();
                                loadTableChiTietPhieuGiao();
                                JOptionPane.showMessageDialog(this, "Thêm thành công");
                            }
                            else
                                 JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể thêm");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ dữ liệu hoặc xem lại số lượng nhập!!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong table để xác định dữ liệu cần thêm [Sách - Phiếu Giao]");
                }
                btnSuaGiaoSach.setEnabled(true);
                btnXoaGiaoSach.setEnabled(true);
                btnThemPhieuGiao.setEnabled(true);
            }
            else
            {
                if(check == 3)
                {
                    int c = tbChiTietPhieuGiao.getSelectedColumn();
                    if(c != -1)
                    {
                        if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            int i = tbChiTietPhieuGiao.getSelectedRow();
                            int maPG = Integer.parseInt(tbChiTietPhieuGiao.getValueAt(i, 0).toString());
                            int maSach = Integer.parseInt(tbChiTietPhieuGiao.getValueAt(i, 1).toString());

                            if(ChiTietPhieuGiaoDAO.xoaChiTietPhieuGiao(maPG,maSach)==true)
                            {
                                loadTablePhieuGiao();
                                dsCTPG = ChiTietPhieuGiaoDAO.getDSCTPG();
                                loadTableChiTietPhieuGiao();

                                JOptionPane.showMessageDialog(this, "Xóa thành công");
                            }
                            else
                                 JOptionPane.showMessageDialog(this, "Không thể xóa !!");

                        }
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong bảng Chi Tiết Phiếu Giao để xác định dữ liệu cần xóa");
                    }
                    
                    btnThemChiTietGiao.setEnabled(true);
                    btnSuaGiaoSach.setEnabled(true);
                    btnThemPhieuGiao.setEnabled(true);
                }
                else
                {
                    if(check ==4)
                    {
                        int c = tbChiTietPhieuGiao.getSelectedColumn();
                        if(c != -1)
                        {
                            if(checkTXTPG())
                            {
                                if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                                {
                                    int i = tbChiTietPhieuGiao.getSelectedRow();
                                    int maPN = Integer.parseInt(tbChiTietPhieuGiao.getValueAt(i, 0).toString());
                                    int maSach = Integer.parseInt(tbChiTietPhieuGiao.getValueAt(i, 1).toString());
                                    int soLuong = Integer.parseInt(txtSoLuongGiao.getText());
                                    ChiTietPhieuGiao ctpg = new ChiTietPhieuGiao(maPN,maSach,soLuong);

                                    if(ChiTietPhieuGiaoDAO.suaChiTietPhieuGiao(ctpg)==true)
                                    {
                                        loadTablePhieuGiao();
                                        dsCTPG = ChiTietPhieuGiaoDAO.getDSCTPG();
                                        loadTableChiTietPhieuGiao();

                                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                                    }
                                    else
                                        JOptionPane.showMessageDialog(this, "Dòng dữ này đã tồn tại hoặc sai dữ liệu, không thể sửa");

                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại số lượng !!");
                            }
                            
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(this, "Hãy chọn vào 1 dòng trong bảng Chi Tiết Phiếu Giao để xác định dữ liệu cần sửa");
                        }
                        
                        btnThemChiTietGiao.setEnabled(true);
                            btnXoaGiaoSach.setEnabled(true);
                            btnThemPhieuGiao.setEnabled(true);
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog(this, "Hãy chọn yêu cầu bạn muốn thực thi !!", "Thông báo", JOptionPane.OK_OPTION);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLuuPhieuGiaoActionPerformed

    private void tbSachGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachGiaoMouseClicked
        int i = tbSachGiao.getSelectedRow();
        String tenSach = tbSachGiao.getValueAt(i, 1).toString().trim();
        
        txtTenSachGiao.setText(tenSach);
    }//GEN-LAST:event_tbSachGiaoMouseClicked

    private void btnLamMoiGiaoSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiGiaoSachActionPerformed
        txtMaPhieuGiao.setText("");
        txtNgayGiaoSach.setText(String.valueOf(LocalDate.now()));
        cboMaPhieuNhap.setSelectedItem("");
        txtThoiGianGiaoSach.setText("");
        txtTenSachGiao.setText("");
        txtSoLuongGiao.setText("");
        txtTimKiemGiaoSach.setText("");
        btnThemPhieuGiao.setEnabled(true);
        btnSuaGiaoSach.setEnabled(true);
        btnXoaGiaoSach.setEnabled(true);
        btnThemChiTietGiao.setEnabled(true);
        dsPG = PhieuGiaoDAO.getDSPG();
        dsCTPG = ChiTietPhieuGiaoDAO.getDSCTPG();
        loadTablePhieuGiao();
        loadTableChiTietPhieuGiao();
        dsS = SachDAO.getDSS();
        loadTableSach();
    }//GEN-LAST:event_btnLamMoiGiaoSachActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
         Mes.OpenFrom(new DangNhap());
        Mes.CloseFrom(this);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void btnTimKiemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSachActionPerformed
        dsS = SachDAO.getDSSachQuaSoLuong(Integer.parseInt(txtTimKiemSach.getText()));
        int i = tbSachNhap.getColumnCount();
        if(i == 0)
        {
            JOptionPane.showConfirmDialog(this, "Không tìm thấy dữ liệu muốn tìm !!", "Thông báo", JOptionPane.OK_OPTION);
        }
        else
        {
            loadTableSach();
        }        
    }//GEN-LAST:event_btnTimKiemSachActionPerformed

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
            java.util.logging.Logger.getLogger(TrangChuNhanVienKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVienKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVienKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVienKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuNhanVienKho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiGiaoSach;
    private javax.swing.JButton btnLamMoiNhapSach;
    private javax.swing.JButton btnLuuNhapSach;
    private javax.swing.JButton btnLuuNhapSach1;
    private javax.swing.JButton btnLuuPhieuGiao;
    private javax.swing.JButton btnSuaGiaoSach;
    private javax.swing.JButton btnSuaNhapSach;
    private javax.swing.JButton btnThemChiTietGiao;
    private javax.swing.JButton btnThemChiTietSach;
    private javax.swing.JButton btnThemNhapSach;
    private javax.swing.JButton btnThemPhieuGiao;
    private javax.swing.JButton btnTimKiemGiaoSach;
    private javax.swing.JButton btnTimKiemNhapSach;
    private javax.swing.JButton btnTimKiemSach;
    private javax.swing.ButtonGroup btnTrangThai;
    private javax.swing.JButton btnXoaGiaoSach;
    private javax.swing.JButton btnXoaNhapSach;
    private javax.swing.JComboBox<String> cboMaPhieuNhap;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JSeparator dongke;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JPanel jpPhieuGiao;
    private javax.swing.JPanel jpPhieuNhap;
    private javax.swing.JPanel jpTTPN;
    private javax.swing.JPanel jpTTSCN;
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab7;
    private javax.swing.JTable tbChiTietPhieuGiao;
    private javax.swing.JTable tbChiTietPhieuNhap;
    private javax.swing.JTable tbPhieuGiaoSach;
    private javax.swing.JTable tbPhieuNhapSach;
    private javax.swing.JTable tbSachGiao;
    private javax.swing.JTable tbSachNhap;
    private javax.swing.JTextField txtGiaNhapSach;
    private javax.swing.JTextField txtMaPhieuGiao;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtNgayGiaoSach;
    private javax.swing.JTextField txtNgayNhapSach;
    private javax.swing.JTextField txtSoLuongGiao;
    private javax.swing.JTextField txtSoLuongNhap;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenSachGiao;
    private javax.swing.JTextField txtTenSachNhap;
    private javax.swing.JTextField txtThoiGianGiaoSach;
    private javax.swing.JTextField txtTimKiemGiaoSach;
    private javax.swing.JTextField txtTimKiemNhapSach;
    private javax.swing.JTextField txtTimKiemSach;
    private javax.swing.JTextField txtTongTienNhap;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
