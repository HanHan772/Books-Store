/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.CTDH_DH_DAO;
import DAO.ControlHelper;
import DAO.DoiSachDAO;
import DAO.HoanTienDAO;
import DAO.JDBCHelper;
import DAO.SachDAO;
import FormatJTable.MoneyCellRenderer;
import FormatJTable.Utility;
import POJO.DonHang;
import POJO.*;
import POJO.Sach;
import SP.Mes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author HANH
 */
public class frmDoiSach extends javax.swing.JFrame {

    private static final int MADH = 0;
    private static final int MASACH = 1;
    private static final int TENSACH = 2;
    private static final int SOLUONG = 4;
    private static final int TENKH = 3;
    private static final int TENNV = 5;
    private static final int NGAYMUA = 6;
    private static final int DONGIABAN = 7;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Vector data = new Vector();
    Vector<String> header;

    Sach sach = null;
    DonHang dh = null;
    SachDAO sdao = new SachDAO();
    HoanTienDAO htdao = new HoanTienDAO();
    DoiSachDAO dsdao = new DoiSachDAO();
    
    /**
     * Creates new form fDoiSach
     */
    public frmDoiSach() throws Exception {
        initComponents();
        header = new Vector<>();
        header.add("Mã Đơn Hàng");
        header.add("Mã Sách");
        header.add("Tên Sách");
        header.add("Tên Khách Hàng");
        header.add("Số Lượng");
        header.add("Tên Nhân Viên");
        header.add("Ngày Mua");
        header.add("Giá");
        

        loadAll();

        checkDoiSach.setSelected(true);
        txtNgayDoiSach.setText(dateFormat.format(Calendar.getInstance().getTime()));
        txtNgayHoanTien.setText(dateFormat.format(Calendar.getInstance().getTime()));

        panelHoanTien.setEnabled(!checkDoiSach.isSelected());
        txtLyDoHoanTien.setEditable(!checkDoiSach.isSelected());
        txtNgayHoanTien.setEditable(!checkDoiSach.isSelected());

        tblBangDoiHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblBangDoiHang.getSelectedRow();
                int col = tblBangDoiHang.getSelectedColumn();
                if (row >= 0 && row < tblBangDoiHang.getRowCount()) {
                    txtMaHoaDon.setText(tblBangDoiHang.getValueAt(row, MADH).toString());
                    txtMaSach.setText(tblBangDoiHang.getValueAt(row, MASACH).toString());
                    txtTenSach.setText(tblBangDoiHang.getValueAt(row, TENSACH).toString());
                    txtTenKhachHang.setText(tblBangDoiHang.getValueAt(row, TENKH).toString());
                    txtSoLuong.setText(tblBangDoiHang.getValueAt(row, SOLUONG).toString());
                    txtTenNhanVien.setText(tblBangDoiHang.getValueAt(row, TENNV).toString());
                    txtNgayMua.setText(tblBangDoiHang.getValueAt(row, NGAYMUA).toString());
                    txtGia.setText(tblBangDoiHang.getValueAt(row, DONGIABAN).toString());
                } else {
                    resetText();
                }
            }
        });
        checkDoiSach.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panelDoiSach.setEnabled(checkDoiSach.isSelected());
                txtLyDoDoiSach.setEditable(checkDoiSach.isSelected());
                txtNgayDoiSach.setEditable(checkDoiSach.isSelected());

                panelHoanTien.setEnabled(!checkDoiSach.isSelected());
                txtLyDoHoanTien.setEditable(!checkDoiSach.isSelected());
                txtNgayHoanTien.setEditable(!checkDoiSach.isSelected());
            }
        });
        btnTimKiemDH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTimKiemDH.getText().isEmpty()) {
                    try {
                        loadAll();
                        //JOptionPane.showMessageDialog(DoiSach.this, "Điền thông tin hợp lệ trước khi tìm kiếm");
                    } catch (Exception ex) {
                        Logger.getLogger(frmDoiSach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        loadSearch();
                    } catch (SQLException ex) {
                        Logger.getLogger(frmDoiSach.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(frmDoiSach.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
    }

     private void clearTextField() {
        txtLyDoHoanTien.setText("");
        txtLyDoDoiSach.setText("");
    }

    private void loadSearch() throws Exception {
        if (data != null) {
            data.clear();
        }
        data = (new CTDH_DH_DAO()).getDonHang(txtTimKiemDH.getText());
        DefaultTableModel dtm = (DefaultTableModel) tblBangDoiHang.getModel();
        dtm.setDataVector(data, header);

        TableColumn moneyColumn = tblBangDoiHang.getColumnModel().getColumn(7);
        // Set the custom cell renderer for the column
        moneyColumn.setCellRenderer(new MoneyCellRenderer());
    }

    private void loadAll() throws Exception {
        if (data != null) {
            data.clear();
        }
        data = (new CTDH_DH_DAO()).getAllDonHang();
        DefaultTableModel dtm = (DefaultTableModel) tblBangDoiHang.getModel();
        dtm.setDataVector(data, header);

        TableColumn moneyColumn = tblBangDoiHang.getColumnModel().getColumn(7);
        // Set the custom cell renderer for the column
        moneyColumn.setCellRenderer(new MoneyCellRenderer());
    }

    private void resetText() {
        txtMaHoaDon.setText("");
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        txtTenKhachHang.setText("");
        txtTenNhanVien.setText("");
        txtNgayMua.setText("");
        txtGia.setText("");
        txtLyDoDoiSach.setText("");
        txtLyDoHoanTien.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTimKiemDH = new javax.swing.JTextField();
        btnTimKiemDH = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangDoiHang = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtNgayMua = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnXuatHoaDon = new javax.swing.JButton();
        panelDoiSach = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtLyDoDoiSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayDoiSach = new javax.swing.JTextField();
        panelHoanTien = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLyDoHoanTien = new javax.swing.JTextField();
        txtNgayHoanTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        checkDoiSach = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Mã đơn hàng:");

        txtTimKiemDH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnTimKiemDH.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemDH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemDH.setText("Tìm kiếm");

        tblBangDoiHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblBangDoiHang.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        tblBangDoiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn Hàng", "Mã Sách", "Tên Sách", "Ten Khách Hàng", "Số Lượng", "Tên Nhân Viên", "Ngày Mua", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangDoiHang.setRowHeight(30);
        jScrollPane3.setViewportView(tblBangDoiHang);

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel40.setText("Mã đơn hàng:");

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel39.setText("Tên sách:");

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel38.setText("Số lượng:");

        jLabel44.setBackground(new java.awt.Color(0, 0, 0));
        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel44.setText("Tên khách hàng:");

        txtMaHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMaHoaDon.setEnabled(false);
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });

        txtTenSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTenSach.setEnabled(false);

        txtSoLuong.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtSoLuong.setEnabled(false);

        txtTenKhachHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTenKhachHang.setEnabled(false);

        jLabel41.setBackground(new java.awt.Color(0, 0, 0));
        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel41.setText("Tên nhân viên:");

        txtTenNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtTenNhanVien.setEnabled(false);

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setText("Ngày mua:");

        txtNgayMua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtNgayMua.setEnabled(false);

        jLabel42.setBackground(new java.awt.Color(0, 0, 0));
        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setText("Giá:");

        txtGia.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtGia.setEnabled(false);
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(0, 153, 153));
        btnLuu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-exchange-16.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXuatHoaDon.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXuatHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-invoice-24.png"))); // NOI18N
        btnXuatHoaDon.setText("Xuất Hóa Đơn");

        panelDoiSach.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đổi sách:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Lí do đổi sách:");

        txtLyDoDoiSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Ngày đổi sách:");

        txtNgayDoiSach.setEditable(false);
        txtNgayDoiSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNgayDoiSach.setEnabled(false);

        javax.swing.GroupLayout panelDoiSachLayout = new javax.swing.GroupLayout(panelDoiSach);
        panelDoiSach.setLayout(panelDoiSachLayout);
        panelDoiSachLayout.setHorizontalGroup(
            panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoiSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLyDoDoiSach, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(txtNgayDoiSach))
                .addContainerGap())
        );
        panelDoiSachLayout.setVerticalGroup(
            panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoiSachLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLyDoDoiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDoiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayDoiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelHoanTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hoàn tiền lại:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Lí do hoàn tiền:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Ngày hoàn tiền:");

        txtLyDoHoanTien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtNgayHoanTien.setEditable(false);
        txtNgayHoanTien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNgayHoanTien.setEnabled(false);

        javax.swing.GroupLayout panelHoanTienLayout = new javax.swing.GroupLayout(panelHoanTien);
        panelHoanTien.setLayout(panelHoanTienLayout);
        panelHoanTienLayout.setHorizontalGroup(
            panelHoanTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoanTienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHoanTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoanTienLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLyDoHoanTien))
                    .addGroup(panelHoanTienLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayHoanTien)))
                .addContainerGap())
        );
        panelHoanTienLayout.setVerticalGroup(
            panelHoanTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoanTienLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelHoanTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLyDoHoanTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelHoanTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoanTienLayout.createSequentialGroup()
                        .addComponent(txtNgayHoanTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Mã sách:");

        txtMaSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMaSach.setEnabled(false);

        checkDoiSach.setLabel("Đổi sách");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemDH, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(btnTimKiemDH))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel39)
                                        .addComponent(jLabel38))
                                    .addComponent(jLabel6))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSach))
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkDoiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelDoiSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11))
                            .addComponent(panelHoanTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLuu)
                .addGap(48, 48, 48)
                .addComponent(btnXuatHoaDon)
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiemDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemDH))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDoiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelHoanTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkDoiSach))
                    .addComponent(jLabel42))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatHoaDon)
                    .addComponent(btnLuu))
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed
    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:       
        try {
            int row = tblBangDoiHang.getSelectedRow();
            if (row < 0 || row >= tblBangDoiHang.getRowCount()) {
                ControlHelper.getInstance().showMessageError(this, "Vui lòng chọn sách trước khi lưu", "Thông báo");
                return;
            }
            int MaDH = Integer.parseInt(txtMaHoaDon.getText());
            int MaSach = Integer.parseInt(txtMaSach.getText());
            String NgayDoiSach = txtNgayDoiSach.getText();
            String LyDoDoiSach = txtLyDoDoiSach.getText();
            String NgayHoanTien = txtNgayHoanTien.getText();
            String LyDoHoanTien = txtLyDoHoanTien.getText();
            int SoLuong = Integer.parseInt(txtSoLuong.getText());
            if (!checkDoiSach.isSelected()) { // Trường hợp hoàn tiền
                if (LyDoHoanTien.isEmpty()) {
                    ControlHelper.getInstance().showMessageError(this, "Không được bỏ trống thông tin", "Thông báo");
                    return;
                }
                HoanTien hoanTien = new HoanTien(MaDH, MaSach, Calendar.getInstance().getTime(), LyDoHoanTien, SoLuong);
                HoanTienDAO hoanTienDAO = new HoanTienDAO();
                boolean kq = hoanTienDAO.insertHoanTien(hoanTien);
                if (kq) {
                    clearTextField();
                    data.remove(row);
                    tblBangDoiHang.updateUI();
                    ControlHelper.getInstance().showMessageInfomation(this, "Lưu thông tin thành công", "Thông báo");
                } else {
                    ControlHelper.getInstance().showMessageError(this, "Lưu thông tin không thành công", "Thông báo");
                }
                
            }
            
            else
            {
                if (LyDoDoiSach.isEmpty()) {
                    ControlHelper.getInstance().showMessageError(this, "Không được bỏ trống thông tin", "Thông báo");
                    return;
                }

                DoiSach doiSach = new DoiSach(MaDH, MaSach, dateFormat.parse(NgayDoiSach), LyDoDoiSach, SoLuong);
                DoiSachDAO doiSachDAO = new DoiSachDAO();
                boolean kq = doiSachDAO.insertDoiSach(doiSach);
                if (kq) {
                    clearTextField();
                    data.remove(row);
                    tblBangDoiHang.updateUI();
                    ControlHelper.getInstance().showMessageInfomation(this, "Lưu thông tin thành công", "Thông báo");
                  
                } 
                else {
                    ControlHelper.getInstance().showMessageError(this, "Lưu thông tin không thành công", "Thông báo");
                }
            }
              clearTextField();    
        } catch (Exception ex) {
            Logger.getLogger(frmDoiSach.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }//GEN-LAST:event_btnLuuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Mes.Exit();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(frmDoiSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDoiSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDoiSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDoiSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                try {
                    new frmDoiSach().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(frmDoiSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnTimKiemDH;
    private javax.swing.JButton btnXuatHoaDon;
    private javax.swing.JCheckBox checkDoiSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelDoiSach;
    private javax.swing.JPanel panelHoanTien;
    private javax.swing.JTable tblBangDoiHang;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLyDoDoiSach;
    private javax.swing.JTextField txtLyDoHoanTien;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayDoiSach;
    private javax.swing.JTextField txtNgayHoanTien;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTimKiemDH;
    // End of variables declaration//GEN-END:variables

   
}
