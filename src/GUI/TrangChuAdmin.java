/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.HoaDonXuatDAO;
import DAO.NXBDAO;
import DAO.NhanVienDAO;
import DAO.SachDAO;
import DAO.TacGiaDAO;
import DAO.TheLoaiDAO;
import POJO.HoaDonXuat;
import POJO.NhaXuatBan;
import POJO.NhanVien;
import POJO.Sach;
import POJO.TacGia;
import POJO.TheLoai;
import SP.Mes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class TrangChuAdmin extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    // vector nhân viên
    NhanVien nhanvien = null;
    // DAO
    NhanVienDAO nvdao = new NhanVienDAO();
    SachDAO sachDao = new SachDAO();
    TacGiaDAO tgDAO = new TacGiaDAO();
    TheLoaiDAO theloaiDAO = new TheLoaiDAO();
    NXBDAO nxbDAO = new NXBDAO();
    //ge
    String srcFile = "\\DO_AN_HK6\\Java\\DAJAVA_QuanLyBanSach\\DA_JAVA_QuanLyBanSach\\src\\Image\\";
    String tk = "";
    String btnNhanVien = "Không";
    String btnSanPham = "Không";
    boolean chooseImg = false;
    private String tenAnh;
    private File fileAnh;
    private String anhTableUpdate;

    private TrangChuAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void MoDau() {
        jpTaiKHoan.setVisible(true);
        jpSanPham.setVisible(false);
        jpKhachHang.setVisible(false);
        jpHoaDon.setVisible(false);
        jpNhanVIen.setVisible(false);
        jpDoiMK.setVisible(false);
        jp8.setVisible(false);
        tabTaiKhoan.setBackground(Color.white);
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        tabDoiMatKhau.setBackground(new Color(255, 153, 153));
        PanelTaiKhoan();
    }

    public TrangChuAdmin(NhanVien nv) {
        initComponents();
        Mes.SetEnibleJtextPanel(false, jpTaiKHoan);
        cboGioiTinh.setEnabled(false);
        nhanvien = nv;
        XetDLTaiKHoan(nv);
        txtTenTaiKhoan.setText(nv.getUsername());
        btnLuuTTCN.setEnabled(false);
        tabTaiKhoan.setBackground(Color.white);
        MoDau();
    }

    // Tiến
    // Trang chủ
    // Làm tài khoản
    public void PanelTaiKhoan() {
        //Mes.SetEditablePanel(false, jp1);
        Mes.SetEnibleJtextPanel(false, jpTaiKHoan);
        cboGioiTinh.setEnabled(false);
        btnLuuTTCN.setEnabled(false);

    }

    // Tiến
    public void PanelNhanVien() {
        Mes.SetEnibleJtextPanel(false, jpNhanVIen);
        ArrayList<NhanVien> ds = nvdao.getDanhSachNV();
        SetDataTableNV(ds);
        txtTimKiemNhanVien.setEnabled(true);
        btnLuuNhanVIen.setEnabled(false);
    }

    public void PanelSanPham() {
        Mes.SetEnibleJtextPanel(false, jpSanPham);
        Mes.SetEnibleComBoBoxPanel(false, jpSanPham);
        btnChonAnh.setEnabled(false);
        btnLuuSach.setEnabled(false);

        ArrayList<TheLoai> dsTL = theloaiDAO.getDSTheLoai();
        ArrayList<String> dsT = new ArrayList<>();
        for (TheLoai tl : dsTL) {
            dsT.add(tl.getTenTL());
        }
        Mes.addComboBox(dsT, cboTheLoaiSach);
        // Tac gia
        ArrayList<TacGia> dsTG = tgDAO.getDSTacGia();
        ArrayList<String> dsG = new ArrayList<>();
        for (TacGia tl : dsTG) {
            dsG.add(tl.getTenTG());
        }
        Mes.addComboBox(dsG, cboTacGiaSach);
        // Nha xb
        ArrayList<NhaXuatBan> dsNXB = nxbDAO.getDSNXB();
        ArrayList<String> dsNX = new ArrayList<>();
        for (NhaXuatBan tl : dsNXB) {
            dsNX.add(tl.getTenNXB());
        }
        Mes.addComboBox(dsNX, cboNhaXuatBanSach);
        ArrayList<Sach> dsSach = sachDao.getDanhSachBook();
        SetDataTableSach(dsSach);
        txtTimKiem.setEnabled(true);
    }

    // Tiến
    public void XetDLTaiKHoan(NhanVien nv) {
        txtUsename.setText(nv.getUsername());
        txtHoTen.setText(nv.getTenNV());
        txtSoDienThoai.setText(nv.getSoDienThoai());
        txtEmail.setText(nv.getEmail());
        String gt = nv.getGioiTinh().trim();
        cboGioiTinh.setSelectedItem(gt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        txtNgaySinh.setText(nv.getNgaySinh().format(formatter));

        LocalDate ngaySinh = nv.getNgaySinh();
        txtSoCCCD.setText(nv.getSoCCCD());
        txtDiaChi.setText(nv.getDiaChi());
    }

    public void SetDataTableNV(ArrayList<NhanVien> dsHV) {
        Vector titleTable = new Vector();
        titleTable.add("Mã");
        titleTable.add("Tên NV");
        titleTable.add("Địa Chỉ");
        titleTable.add("SDT");
        titleTable.add("Email");
        titleTable.add("Giới T");
        titleTable.add("Ngày Sinh");
        titleTable.add("SCMND");
        titleTable.add("Mã CV");
        titleTable.add("Tài Khoản");

        Vector dataTable = new Vector();
        Mes.FormatTable(dataTable);
        for (NhanVien hv : dsHV) {
            Vector h = new Vector();
            h.add(hv.getMaNV());
            h.add(hv.getTenNV());
            h.add(hv.getDiaChi());
            h.add(hv.getSoDienThoai());
            h.add(hv.getEmail());
            h.add(hv.getGioiTinh());
            h.add(hv.getNgaySinh());
            h.add(hv.getSoCCCD());
            h.add(hv.getMaCV());
            h.add(hv.getUsername());
            dataTable.add(h);
        }
        Mes.SetDuLieuTable(titleTable, dataTable, tblBangNhanVien);
    }

    public void SetDataTableSach(ArrayList<Sach> dsHV) {
        Vector titleTable = new Vector();
        titleTable.add("Mã");
        titleTable.add("Tên Sách");
        titleTable.add("Dơn Giá ");
        titleTable.add("SL Tồn");
        titleTable.add("Ảnh");
        titleTable.add("Mô Tả");
        titleTable.add("Mã NXB");
        titleTable.add("Mã TL");
        titleTable.add("Mã TG");
        titleTable.add("Trạng Thái");

        Vector dataTable = new Vector();
        Mes.FormatTable(dataTable);
        for (Sach hv : dsHV) {
            Vector h = new Vector();
            h.add(hv.getMaSach());
            h.add(hv.getTenSach());
            h.add(hv.getDonGiaBan());
            h.add(hv.getSoLuongTon());
            h.add(hv.getHinhAnh());
            h.add(hv.getMoTa());
            h.add(hv.getMaNXB());
            h.add(hv.getMaTL());
            h.add(hv.getMaTG());
            h.add(hv.getTrangThai());
            dataTable.add(h);
        }
        Mes.SetDuLieuTable(titleTable, dataTable, tblBangSach);
    }

    public void SetDataTableHoaDon(ArrayList<HoaDonXuat> dsHV) {
        Vector titleTable = new Vector();
        titleTable.add("Mã HD");
        titleTable.add("Tên Khách Hàng");
        titleTable.add("Tên Nhân Viên");
        titleTable.add("Thành Tiền");
        titleTable.add("Trạng Thái");

        Vector dataTable = new Vector();
        Mes.FormatTable(dataTable);
        for (HoaDonXuat hv : dsHV) {
            Vector h = new Vector();
            h.add(hv.getMaDH());
            h.add(hv.getTenKH());
            h.add(hv.getTenNV());
            h.add(hv.getThanhTien());
            h.add(hv.getTrangThai());

            dataTable.add(h);
        }
        Mes.SetDuLieuTable(titleTable, dataTable, tblBangDH);
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
        btnChucVu = new javax.swing.ButtonGroup();
        btnTinhTrang = new javax.swing.ButtonGroup();
        left = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        tabTaiKhoan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabSanPham = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tabHoaDon = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tabNhanVien = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        dongke = new javax.swing.JSeparator();
        tabDoiMatKhau = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        tab7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        right = new javax.swing.JPanel();
        jpTaiKHoan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        btnSuaTTCN = new javax.swing.JButton();
        btnLamMoiTTCN = new javax.swing.JButton();
        btnLuuTTCN = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSoCCCD = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtUsename = new javax.swing.JTextField();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jpSanPham = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        txtMaSach = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDonGiaBan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblAnhSach = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuuSach = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangSach = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        cboTheLoaiSach = new javax.swing.JComboBox<>();
        cboTacGiaSach = new javax.swing.JComboBox<>();
        cboNhaXuatBanSach = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        cboTrangThaiSach = new javax.swing.JComboBox<>();
        btnLamMoi1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jpKhachHang = new javax.swing.JPanel();
        btnTimKiemKH = new javax.swing.JButton();
        txtTimKiemKH = new javax.swing.JTextField();
        btnLamMoiKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        txtNgaySinhKH = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtDiaChiKhachHang = new javax.swing.JTextField();
        txtEmailKH = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtSoDienThoaiKH = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtGioiTinhKH = new javax.swing.JTextField();
        txtSoCCCDKH = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jpHoaDon = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangDH = new javax.swing.JTable();
        btnTimKiemDH = new javax.swing.JButton();
        txtTimKiemDH = new javax.swing.JTextField();
        jpNhanVIen = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSoDienThoaiNhanVien = new javax.swing.JTextField();
        txtNgaySinhNhanVien = new javax.swing.JTextField();
        txtDiaChiNhanVien = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtHoTenNhanVien = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtSoCCCDNhanVien = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtEmailNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        btnXoaNhanVien = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBangNhanVien = new javax.swing.JTable();
        btnTimKiemNhanVien = new javax.swing.JButton();
        txtTimKiemNhanVien = new javax.swing.JTextField();
        btnLamMoiNhanVien = new javax.swing.JButton();
        btnLuuNhanVIen = new javax.swing.JButton();
        cboChucVuNhanVien = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        txtTaiKhoanNV = new javax.swing.JTextField();
        cboGioiTinhNhanVien = new javax.swing.JComboBox<>();
        jp8 = new javax.swing.JPanel();
        jpDoiMK = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtMatKhauCu = new javax.swing.JTextField();
        txtMatKhauMoi = new javax.swing.JTextField();
        txtNhapLaiMatKhau = new javax.swing.JTextField();
        btnDoiMatKhau = new javax.swing.JButton();
        txtTenTaiKhoan = new javax.swing.JTextField();

        jTextField14.setText("jTextField14");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        left.setBackground(new java.awt.Color(51, 51, 51));

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/book-stack.png"))); // NOI18N

        tabTaiKhoan.setBackground(new java.awt.Color(255, 153, 153));
        tabTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTaiKhoanMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/add-user.png"))); // NOI18N
        jLabel1.setText("Tài Khoản");

        javax.swing.GroupLayout tabTaiKhoanLayout = new javax.swing.GroupLayout(tabTaiKhoan);
        tabTaiKhoan.setLayout(tabTaiKhoanLayout);
        tabTaiKhoanLayout.setHorizontalGroup(
            tabTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabTaiKhoanLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabTaiKhoanLayout.setVerticalGroup(
            tabTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSanPham.setBackground(new java.awt.Color(255, 153, 153));
        tabSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabSanPhamMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-extra-features-32.png"))); // NOI18N
        jLabel3.setText("Sản Phẩm");

        javax.swing.GroupLayout tabSanPhamLayout = new javax.swing.GroupLayout(tabSanPham);
        tabSanPham.setLayout(tabSanPhamLayout);
        tabSanPhamLayout.setHorizontalGroup(
            tabSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(33, 33, 33))
        );
        tabSanPhamLayout.setVerticalGroup(
            tabSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabHoaDon.setBackground(new java.awt.Color(255, 153, 153));
        tabHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabHoaDonMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-bill-24.png"))); // NOI18N
        jLabel6.setText("Hóa Đơn");

        javax.swing.GroupLayout tabHoaDonLayout = new javax.swing.GroupLayout(tabHoaDon);
        tabHoaDon.setLayout(tabHoaDonLayout);
        tabHoaDonLayout.setHorizontalGroup(
            tabHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHoaDonLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabHoaDonLayout.setVerticalGroup(
            tabHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabNhanVien.setBackground(new java.awt.Color(255, 153, 153));
        tabNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabNhanVienMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-salesman-50.png"))); // NOI18N
        jLabel8.setText("Nhân Viên");

        javax.swing.GroupLayout tabNhanVienLayout = new javax.swing.GroupLayout(tabNhanVien);
        tabNhanVien.setLayout(tabNhanVienLayout);
        tabNhanVienLayout.setHorizontalGroup(
            tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabNhanVienLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabNhanVienLayout.setVerticalGroup(
            tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabDoiMatKhau.setBackground(new java.awt.Color(255, 153, 153));
        tabDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDoiMatKhauMouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-no-key-48.png"))); // NOI18N
        jLabel26.setText("Đổi mật khẩu");

        javax.swing.GroupLayout tabDoiMatKhauLayout = new javax.swing.GroupLayout(tabDoiMatKhau);
        tabDoiMatKhau.setLayout(tabDoiMatKhauLayout);
        tabDoiMatKhauLayout.setHorizontalGroup(
            tabDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDoiMatKhauLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabDoiMatKhauLayout.setVerticalGroup(
            tabDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
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
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(tabTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dongke)
            .addComponent(tabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabDoiMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(dongke, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        right.setBackground(new java.awt.Color(102, 102, 102));
        right.setLayout(new javax.swing.OverlayLayout(right));

        jpTaiKHoan.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Thông tin cá nhân");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Họ tên:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Địa chỉ:");

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnSuaTTCN.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaTTCN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaTTCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSuaTTCN.setText("Sửa");
        btnSuaTTCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTCNActionPerformed(evt);
            }
        });

        btnLamMoiTTCN.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoiTTCN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoiTTCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoiTTCN.setText("Làm Mới");
        btnLamMoiTTCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTCNActionPerformed(evt);
            }
        });

        btnLuuTTCN.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuTTCN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuTTCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-save-24.png"))); // NOI18N
        btnLuuTTCN.setText("Lưu");
        btnLuuTTCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTTCNActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Email:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Số điện thoại:");

        txtSoDienThoai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtNgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ngày sinh:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Giới tính:");

        txtSoCCCD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Số CCCD:");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Tài Khoản:");

        txtUsename.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nữ", "Nam" }));

        javax.swing.GroupLayout jpTaiKHoanLayout = new javax.swing.GroupLayout(jpTaiKHoan);
        jpTaiKHoan.setLayout(jpTaiKHoanLayout);
        jpTaiKHoanLayout.setHorizontalGroup(
            jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(376, 376, 376))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSuaTTCN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnLamMoiTTCN)
                .addGap(32, 32, 32)
                .addComponent(btnLuuTTCN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(18, 18, 18)
                                    .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel12)
                                .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                                    .addGap(128, 128, 128)
                                    .addComponent(txtUsename, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel23))
                            .addComponent(jLabel17))
                        .addGap(45, 45, 45)
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(77, 77, 77)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(43, 43, 43)
                                    .addComponent(txtSoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(43, 43, 43)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(57, 57, 57)
                        .addComponent(txtDiaChi)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jpTaiKHoanLayout.setVerticalGroup(
            jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtSoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(25, 25, 25)
                        .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(18, 18, 18)
                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTaiKHoanLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaiKHoanLayout.createSequentialGroup()
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(41, 41, 41)
                .addGroup(jpTaiKHoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaTTCN)
                    .addComponent(btnLamMoiTTCN)
                    .addComponent(btnLuuTTCN))
                .addContainerGap(262, Short.MAX_VALUE))
        );

        right.add(jpTaiKHoan);

        jpSanPham.setBackground(new java.awt.Color(102, 102, 102));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mã sách:");

        btnChonAnh.setBackground(new java.awt.Color(0, 153, 153));
        btnChonAnh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnChonAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-choose-50.png"))); // NOI18N
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        txtMaSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tên sách:");

        txtTenSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Đơn giá bán:");

        txtDonGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDonGiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDonGiaBanKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Mô tả:");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Nhà xuất bản:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Số lượng tồn:");

        txtSoLuongTon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSoLuongTon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongTonKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Ảnh:");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Thể loại:");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Tác giả:");

        lblAnhSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAnhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/1.jpg"))); // NOI18N

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 153));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delete-trash-24.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuuSach.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-save-24.png"))); // NOI18N
        btnLuuSach.setText("Lưu");
        btnLuuSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSachActionPerformed(evt);
            }
        });

        tblBangSach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblBangSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblBangSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Đơn giá bán", "Số lượng tồn", "Mô tả", "Nhà sản xuất", "Địa chỉ", "Thể loại", "Tác giả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangSach);

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnTimKiem.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboTheLoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboTacGiaSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNhaXuatBanSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Trạng Thái:");

        cboTrangThaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dừng Bán", "Hết Hàng", "Còn Hàng" }));

        btnLamMoi1.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoi1.setText("Làm mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(5);
        txtMoTa.setWrapStyleWord(true);
        jScrollPane6.setViewportView(txtMoTa);

        javax.swing.GroupLayout jpSanPhamLayout = new javax.swing.GroupLayout(jpSanPham);
        jpSanPham.setLayout(jpSanPhamLayout);
        jpSanPhamLayout.setHorizontalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel25)
                                    .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel19))))
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTheLoaiSach, 0, 253, Short.MAX_VALUE)
                            .addComponent(cboNhaXuatBanSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoLuongTon)
                            .addComponent(txtDonGiaBan)
                            .addComponent(txtMaSach)
                            .addComponent(cboTacGiaSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTrangThaiSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnChonAnh)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSanPhamLayout.createSequentialGroup()
                                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnLuuSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLamMoi1))
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSanPhamLayout.createSequentialGroup()
                                        .addComponent(btnTimKiem)
                                        .addGap(37, 37, 37))))
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jpSanPhamLayout.setVerticalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSanPhamLayout.createSequentialGroup()
                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(cboNhaXuatBanSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTheLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)))
                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel33))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChonAnh))
                            .addGroup(jpSanPhamLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addComponent(btnLuuSach))
                                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                                        .addComponent(btnThem)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSua)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXoa)))
                                .addGap(18, 18, 18)
                                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLamMoi1)
                                    .addComponent(jLabel21))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTacGiaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(cboTrangThaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE))
                    .addGroup(jpSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        right.add(jpSanPham);

        jpKhachHang.setBackground(new java.awt.Color(102, 102, 102));

        btnTimKiemKH.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemKH.setText("Tìm kiếm");

        txtTimKiemKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnLamMoiKH.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoiKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoiKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoiKH.setText("Làm mới");
        btnLamMoiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKHActionPerformed(evt);
            }
        });

        btnXoaKH.setBackground(new java.awt.Color(0, 153, 153));
        btnXoaKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delete-trash-24.png"))); // NOI18N
        btnXoaKH.setText("Xóa");

        btnSuaKH.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSuaKH.setText("Sửa");

        btnThemKH.setBackground(new java.awt.Color(0, 153, 153));
        btnThemKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemKH.setText("Thêm");

        txtNgaySinhKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Ngày sinh:");

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "Email", "Địa chỉ", "Số điện thoại", "Số CCCD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Tên khách hàng:");

        txtMaKhachHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtMaKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Mã khách hàng:");

        txtDiaChiKhachHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtEmailKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Email:");

        txtSoDienThoaiKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Số điện thoại:");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Địa chỉ:");

        txtGioiTinhKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtSoCCCDKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Giới tính:");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Số CCCD:");

        javax.swing.GroupLayout jpKhachHangLayout = new javax.swing.GroupLayout(jpKhachHang);
        jpKhachHang.setLayout(jpKhachHangLayout);
        jpKhachHangLayout.setHorizontalGroup(
            jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jpKhachHangLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39)
                    .addComponent(jLabel38)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpKhachHangLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel41))
                    .addComponent(jLabel43)
                    .addComponent(jLabel42)
                    .addComponent(jLabel45))
                .addGap(28, 28, 28)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoCCCDKH, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(jpKhachHangLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnLamMoiKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpKhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnTimKiemKH)
                .addGap(289, 289, 289))
        );
        jpKhachHangLayout.setVerticalGroup(
            jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpKhachHangLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpKhachHangLayout.createSequentialGroup()
                        .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpKhachHangLayout.createSequentialGroup()
                        .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGioiTinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(txtSoCCCDKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnSuaKH)
                    .addComponent(btnXoaKH)
                    .addComponent(btnLamMoiKH))
                .addGap(28, 28, 28)
                .addGroup(jpKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKH))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
        );

        right.add(jpKhachHang);

        jpHoaDon.setBackground(new java.awt.Color(102, 102, 102));
        jpHoaDon.setPreferredSize(new java.awt.Dimension(774, 543));

        tblBangDH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblBangDH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblBangDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã đơn hàng", "Tên khách hàng", "Tên nhân viên", "Ngày mua", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblBangDH);

        btnTimKiemDH.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemDH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemDH.setText("Tìm kiếm");
        btnTimKiemDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemDHActionPerformed(evt);
            }
        });

        txtTimKiemDH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        javax.swing.GroupLayout jpHoaDonLayout = new javax.swing.GroupLayout(jpHoaDon);
        jpHoaDon.setLayout(jpHoaDonLayout);
        jpHoaDonLayout.setHorizontalGroup(
            jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addGroup(jpHoaDonLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(txtTimKiemDH, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnTimKiemDH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpHoaDonLayout.setVerticalGroup(
            jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHoaDonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemDH))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );

        right.add(jpHoaDon);

        jpNhanVIen.setBackground(new java.awt.Color(120, 120, 120));
        jpNhanVIen.setToolTipText("");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Họ tên:");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Số điện thoại:");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Địa chỉ:");

        txtSoDienThoaiNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtNgaySinhNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtDiaChiNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Ngày sinh:");

        txtHoTenNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Giới tính:");

        txtSoCCCDNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Số CCCD:");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Email:");

        txtEmailNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtMaNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Mã nhân viên:");

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Chức vụ:");

        btnXoaNhanVien.setBackground(new java.awt.Color(0, 153, 153));
        btnXoaNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-delete-trash-24.png"))); // NOI18N
        btnXoaNhanVien.setText("Xóa");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-tools-24.png"))); // NOI18N
        btnSuaNhanVien.setText("Sửa");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnThemNhanVien.setBackground(new java.awt.Color(0, 153, 153));
        btnThemNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-add-24.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        tblBangNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblBangNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblBangNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblBangNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangNhanVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBangNhanVien);

        btnTimKiemNhanVien.setBackground(new java.awt.Color(0, 153, 153));
        btnTimKiemNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTimKiemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-find-24.png"))); // NOI18N
        btnTimKiemNhanVien.setText("Tìm kiếm");
        btnTimKiemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNhanVienActionPerformed(evt);
            }
        });

        txtTimKiemNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnLamMoiNhanVien.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoiNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLamMoiNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-refresh-32.png"))); // NOI18N
        btnLamMoiNhanVien.setText("Làm mới");
        btnLamMoiNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNhanVienActionPerformed(evt);
            }
        });

        btnLuuNhanVIen.setBackground(new java.awt.Color(0, 153, 153));
        btnLuuNhanVIen.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLuuNhanVIen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-save-24.png"))); // NOI18N
        btnLuuNhanVIen.setText("Lưu");
        btnLuuNhanVIen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhanVIenActionPerformed(evt);
            }
        });

        cboChucVuNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bán Hàng", "Kho", "Quan Lý" }));

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Tài Khoản");

        txtTaiKhoanNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        cboGioiTinhNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        javax.swing.GroupLayout jpNhanVIenLayout = new javax.swing.GroupLayout(jpNhanVIen);
        jpNhanVIen.setLayout(jpNhanVIenLayout);
        jpNhanVIenLayout.setHorizontalGroup(
            jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanVIenLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(txtTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanVIenLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnTimKiemNhanVien)
                        .addGap(289, 289, 289))
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnLuuNhanVIen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnLamMoiNhanVien)
                        .addContainerGap(169, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanVIenLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46)
                            .addComponent(jLabel48)
                            .addComponent(jLabel54))
                        .addGap(18, 18, 18)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoDienThoaiNhanVien)
                            .addComponent(txtDiaChiNhanVien)
                            .addComponent(txtHoTenNhanVien)
                            .addGroup(jpNhanVIenLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboChucVuNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNhanVien)))
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel51)
                            .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel50)
                                .addComponent(jLabel49)
                                .addComponent(jLabel52))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanVIenLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)))
                .addGap(18, 18, 18)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTaiKhoanNV)
                    .addComponent(txtSoCCCDNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(txtEmailNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(txtNgaySinhNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(cboGioiTinhNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 117, 117))
        );
        jpNhanVIenLayout.setVerticalGroup(
            jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhanVIenLayout.createSequentialGroup()
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpNhanVIenLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanVIenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtEmailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(cboGioiTinhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgaySinhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(18, 18, 18)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoCCCDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)))
                    .addGroup(jpNhanVIenLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(21, 21, 21)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txtDiaChiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(txtSoDienThoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTaiKhoanNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55))
                    .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(cboChucVuNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhanVien)
                    .addComponent(btnSuaNhanVien)
                    .addComponent(btnLamMoiNhanVien)
                    .addComponent(btnLuuNhanVIen)
                    .addComponent(btnXoaNhanVien))
                .addGap(22, 22, 22)
                .addGroup(jpNhanVIenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNhanVien))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        );

        right.add(jpNhanVIen);

        jp8.setBackground(new java.awt.Color(120, 120, 120));

        javax.swing.GroupLayout jp8Layout = new javax.swing.GroupLayout(jp8);
        jp8.setLayout(jp8Layout);
        jp8Layout.setHorizontalGroup(
            jp8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jp8Layout.setVerticalGroup(
            jp8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        right.add(jp8);

        jpDoiMK.setBackground(new java.awt.Color(102, 102, 102));
        jpDoiMK.setToolTipText("");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Mật khẩu cũ:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tên tài khoản:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Đổi mật khẩu");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Nhập lại mật khẩu:");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Mật khẩu mới:");

        txtMatKhauCu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtMatKhauMoi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtNhapLaiMatKhau.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnDoiMatKhau.setBackground(new java.awt.Color(0, 153, 153));
        btnDoiMatKhau.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icons8-no-key-48.png"))); // NOI18N
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        txtTenTaiKhoan.setEditable(false);
        txtTenTaiKhoan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        javax.swing.GroupLayout jpDoiMKLayout = new javax.swing.GroupLayout(jpDoiMK);
        jpDoiMK.setLayout(jpDoiMKLayout);
        jpDoiMKLayout.setHorizontalGroup(
            jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDoiMKLayout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDoiMKLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDoiMKLayout.createSequentialGroup()
                        .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpDoiMKLayout.createSequentialGroup()
                                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28))
                                .addGap(37, 37, 37)))
                        .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDoiMKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpDoiMKLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(349, 349, 349))
            .addGroup(jpDoiMKLayout.createSequentialGroup()
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDoiMKLayout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel29))
                    .addGroup(jpDoiMKLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(btnDoiMatKhau)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDoiMKLayout.setVerticalGroup(
            jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDoiMKLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jpDoiMKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(37, 37, 37)
                .addComponent(btnDoiMatKhau)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        right.add(jpDoiMK);

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

    private void tabTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTaiKhoanMouseClicked
        // TODO add your handling code here:
        jpTaiKHoan.setVisible(true);
        jpSanPham.setVisible(false);
        jpKhachHang.setVisible(false);
        jpHoaDon.setVisible(false);
        jpNhanVIen.setVisible(false);
        jpDoiMK.setVisible(false);
        jp8.setVisible(false);
        tabTaiKhoan.setBackground(Color.white);
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        tabDoiMatKhau.setBackground(new Color(255, 153, 153));
        PanelTaiKhoan();
    }//GEN-LAST:event_tabTaiKhoanMouseClicked

    private void tabSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabSanPhamMouseClicked
        // TODO add your handling code here:
        jpSanPham.setVisible(true);
        jpTaiKHoan.setVisible(false);
        jpKhachHang.setVisible(false);
        jpHoaDon.setVisible(false);
        jpNhanVIen.setVisible(false);
        jpDoiMK.setVisible(false);
        jp8.setVisible(false);
        tabSanPham.setBackground(Color.white);
        tabTaiKhoan.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        tabDoiMatKhau.setBackground(new Color(255, 153, 153));
        PanelSanPham();
    }//GEN-LAST:event_tabSanPhamMouseClicked

    private void tabHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabHoaDonMouseClicked
        // TODO add your handling code here:
        jpHoaDon.setVisible(true);
        jpSanPham.setVisible(false);
        jpKhachHang.setVisible(false);
        jpTaiKHoan.setVisible(false);
        jpNhanVIen.setVisible(false);
        jpDoiMK.setVisible(false);
        jp8.setVisible(false);
        tabHoaDon.setBackground(Color.white);
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabTaiKhoan.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        tabDoiMatKhau.setBackground(new Color(255, 153, 153));
        HoaDonXuatDAO hddao = new HoaDonXuatDAO();
        SetDataTableHoaDon(hddao.xuatDSHoaDon());
    }//GEN-LAST:event_tabHoaDonMouseClicked

    private void tabNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabNhanVienMouseClicked
        // TODO add your handling code here:
        jpNhanVIen.setVisible(true);
        jpSanPham.setVisible(false);
        jpKhachHang.setVisible(false);
        jpHoaDon.setVisible(false);
        jpTaiKHoan.setVisible(false);
        jpDoiMK.setVisible(false);
        jp8.setVisible(false);
        tabNhanVien.setBackground(Color.white);
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabTaiKhoan.setBackground(new Color(255, 153, 153));
        tabDoiMatKhau.setBackground(new Color(255, 153, 153));

        PanelNhanVien();
    }//GEN-LAST:event_tabNhanVienMouseClicked

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        // TODO add your handling code here:
        tab7.setBackground(Color.white);
        tabTaiKhoan.setBackground(new Color(255, 153, 153));
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        Mes.OpenFrom(new DangNhap());
        Mes.CloseFrom(this);
    }//GEN-LAST:event_tab7MouseClicked

    private void tabDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDoiMatKhauMouseClicked
        // TODO add your handling code here:
        jp8.setVisible(false);
        jpDoiMK.setVisible(true);
        jpSanPham.setVisible(false);
        jpKhachHang.setVisible(false);
        jpHoaDon.setVisible(false);
        jpNhanVIen.setVisible(false);
        jpTaiKHoan.setVisible(false);
        tabDoiMatKhau.setBackground(Color.white);
        tabSanPham.setBackground(new Color(255, 153, 153));
        tabHoaDon.setBackground(new Color(255, 153, 153));
        tabNhanVien.setBackground(new Color(255, 153, 153));
        tabTaiKhoan.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_tabDoiMatKhauMouseClicked

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here: 
        System.out.println("Chương trình đã kết thúc.");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Mes.Exit();
    }//GEN-LAST:event_formWindowClosing
    // Tiến
    private void btnSuaTTCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTCNActionPerformed

        // TODO add your handling code here:
        Mes.SetEnibleJtextPanel(true, jpTaiKHoan);
        txtUsename.setEnabled(false);
        cboGioiTinh.setEnabled(true);
        btnLuuTTCN.setEnabled(true);
    }//GEN-LAST:event_btnSuaTTCNActionPerformed
    // Tiến
    private void btnLamMoiTTCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTCNActionPerformed
        // TODO add your handling code here:
        NhanVien nv = nvdao.getNhanVienQuaUsename(nhanvien.getUsername());
        XetDLTaiKHoan(nv);
        Mes.SetEnibleJtextPanel(false, jpTaiKHoan);
        cboGioiTinh.setEnabled(false);

    }//GEN-LAST:event_btnLamMoiTTCNActionPerformed
    // Tiến
    public boolean KiemTraND() {
        String tileBar = "Thông Báo";
        if (Mes.boolJTextField(txtDiaChi) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }
        if (Mes.boolJTextField(txtHoTen) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }
        if (Mes.boolJTextField(txtSoDienThoai) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }
        if (Mes.boolJTextField(txtEmail) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }
        if (Mes.boolJTextField(txtSoDienThoai) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }
        if (Mes.boolInputDate(txtNgaySinh.getText(), "dd-mm-yyyy") == false) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đúng định dạng ngày: dd-mm-yyyy!");
            return false;
        }
        if (Mes.boolJTextField(txtSoCCCD) == true) {
            Mes.BaoLoi(tileBar, "Vui lòng nhập đày đủ dữ liệu!");
            return false;
        }

        return true;
    }

    //Tiến
    private void btnLuuTTCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTTCNActionPerformed
        // TODO add your handling code here:
        if (KiemTraND() == false) {
            return;
        }
        int maNV = nhanvien.getMaNV();
        String tenNV = txtHoTen.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String email = txtEmail.getText().trim();
        String gioiTinh = (String) cboGioiTinh.getSelectedItem();

        String ngaySinhString = txtNgaySinh.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ngaySinh = LocalDate.parse(ngaySinhString, formatter);

        int maCV = nhanvien.getMaCV();
        String soCCCD = txtSoCCCD.getText();
        String tentk = txtUsename.getText();
        if (nvdao.checkDuplicateEmail(maNV, email) == false) {
            Mes.BaoLoi("Thông báo!", "Email đã trong hệ thống");

        }
        if (nvdao.checkDuplicateCCCD(maNV, email) == false) {
            Mes.BaoLoi("Thông báo!", "CCCD đã có trong hệ thống");

        }
        if (nvdao.checkDuplicateSDT(maNV, soDienThoai) == false) {
            Mes.BaoLoi("Thông báo!", "Số Điện Thoại đã có trong hệ thống");

        }
        NhanVien employee = new NhanVien(maNV, tenNV, diaChi, soDienThoai, email, gioiTinh, ngaySinh, maCV, soCCCD, tentk);
        if (nvdao.updateNhanVien(employee) == true) {
            Mes.ThongBao("Cập Nhật", "Thành Công");
        }

    }//GEN-LAST:event_btnLuuTTCNActionPerformed

    private void tblBangNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangNhanVienMouseClicked
        // TODO add your handling code here:
        int i = tblBangNhanVien.getSelectedRow();
        int maNV = (int) tblBangNhanVien.getValueAt(i, 0);
        String tenNV = tblBangNhanVien.getValueAt(i, 1).toString().trim();
        String diaChi = tblBangNhanVien.getValueAt(i, 2).toString().trim();
        String soDienThoai = tblBangNhanVien.getValueAt(i, 3).toString().trim();
        String email = tblBangNhanVien.getValueAt(i, 4).toString().trim();
        String gioiTinh = tblBangNhanVien.getValueAt(i, 5).toString().trim();
        String ngaySinhString = tblBangNhanVien.getValueAt(i, 6).toString().trim();
        String soCCCD = tblBangNhanVien.getValueAt(i, 7).toString().trim();
        String macv = tblBangNhanVien.getValueAt(i, 8).toString();
        String taikhoan = (String) tblBangNhanVien.getValueAt(i, 9);
        tk = taikhoan;
        //-----------
        txtMaNhanVien.setText(String.format("%d", maNV));
        txtHoTenNhanVien.setText(tenNV);
        txtDiaChiNhanVien.setText(diaChi);
        txtSoDienThoaiNhanVien.setText(soDienThoai);
        txtEmailNhanVien.setText(email);
        cboGioiTinhNhanVien.setSelectedItem(gioiTinh);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngaySinh = LocalDate.parse(ngaySinhString, formatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String ngaySinhFormatted = ngaySinh.format(outputFormatter);
        txtNgaySinhNhanVien.setText(ngaySinhFormatted);
        txtSoCCCDNhanVien.setText(soCCCD);
        int combo = Integer.parseInt(macv) - 1;
        cboChucVuNhanVien.setSelectedIndex(combo);
        txtTaiKhoanNV.setText(tk);
        Mes.SetEnibleJtextPanel(false, jpNhanVIen);
        txtTimKiemNhanVien.setEnabled(true);
        btnLuuNhanVIen.setEnabled(false);
        cboChucVuNhanVien.setEnabled(false);
        cboGioiTinhNhanVien.setEnabled(false);

    }//GEN-LAST:event_tblBangNhanVienMouseClicked

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        // TODO add your handling code here:
        int ma = Integer.parseInt(txtMaNhanVien.getText());
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (nvdao.deleteNhanVien(ma, tk) == true) {
                Mes.ThongBao("Thông báo Xoá", "Xoá Thành Công!");
            } else {
                Mes.ThongBao("Thông báo Xoá", "Xoá Không Thành Công!");
            }
        }
        ArrayList<NhanVien> ds = nvdao.getDanhSachNV();
        SetDataTableNV(ds);
        btnNhanVien = "Không";
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed
    public boolean KiemTraNhapNhanVien() {
        String tb = " Thông Báo Nhâp!";
        if (Mes.boolJTextField(txtHoTenNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Tên NV");
            return false;
        }
        if (Mes.boolJTextField(txtDiaChiNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Địa Chỉ");
            return false;
        }
        if (Mes.boolJTextField(txtSoDienThoaiNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp SDT");
            return false;
        }
        if (Mes.boolJTextField(txtEmailNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Email");
            return false;
        }
        if (Mes.boolJTextField(txtNgaySinhNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Ngày Sinh");
            return false;
        }
        if (Mes.boolInputDate(txtNgaySinhNhanVien.getText(), "dd-MM-yyyy") == false) {
            Mes.ThongBao(tb, "Vui lòng nhập đúng định dạng ngày: dd-MM-yyyy!");
            return false;
        }
        if (Mes.boolJTextField(txtSoCCCDNhanVien) == true) {
            Mes.ThongBao(tb, "Chưa nhâp CCCD");
            return false;
        }
        if (Mes.checkCCCDNumber(txtSoCCCDNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "Định dạng CCCD không đúng, 12 số");
            return false;
        }
        if (btnNhanVien.equals("Thêm") == true && Mes.boolJTextField(txtTaiKhoanNV) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Tài Khoản Nhân Viên!");
            return false;
        }

        if (Mes.checkEmail(txtEmailNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "Định dạng email không đúng");
            return false;
        }
        if (Mes.checkPhoneNumber(txtSoDienThoaiNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "Định dạng SDT không đúng");
            return false;
        }
        return true;
    }

    private boolean kiemTraDLNhap(int ma) {
        String tb = " Thông Báo Định Dạng Nhâp!";
        if (nvdao.checkDuplicateCCCD(ma, txtSoCCCDNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "CCCD có trong hệ thống!");
            return false;
        }
        if (nvdao.checkDuplicateSDT(ma, txtSoDienThoaiNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "Số Điện THoại có trong hệ thống!");
            return false;
        }
        if (nvdao.checkDuplicateUsername(ma, txtTaiKhoanNV.getText()) == false) {
            Mes.ThongBao(tb, "Tài Khoản có trong hệ thống!");
            return false;
        }
        if (nvdao.checkDuplicateEmail(ma, txtEmailNhanVien.getText()) == false) {
            Mes.ThongBao(tb, "Email có trong hệ thống!");
            return false;
        }
        return true;
    }

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
        // TODO add your handling code here:
        if (Mes.boolJTextField(txtMaNhanVien) == true) {
            Mes.BaoLoi("Thông báo sửa!", "Vui lòng Chọn Nhân Viên!");
            btnLuuNhanVIen.setEnabled(false);
            Mes.SetEnibleJtextPanel(false, jpNhanVIen);
            return;
        }
        Mes.SetEnibleJtextPanel(true, jpNhanVIen);
        txtMaNhanVien.setEditable(false);
        btnNhanVien = "Sửa";
        btnLuuNhanVIen.setEnabled(true);
        txtTimKiemNhanVien.setEnabled(true);
        cboChucVuNhanVien.setEnabled(true);
        cboGioiTinhNhanVien.setEnabled(true);
        txtTaiKhoanNV.setEnabled(false);
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnLamMoiNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNhanVienActionPerformed
        // TODO add your handling code here:
        ArrayList<NhanVien> ds = nvdao.getDanhSachNV();
        SetDataTableNV(ds);
        btnLuuNhanVIen.setEnabled(false);
    }//GEN-LAST:event_btnLamMoiNhanVienActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        Mes.SetEnibleJtextPanel(true, jpNhanVIen);
        txtMaNhanVien.setEditable(false);
        Mes.SetDLJtextPanel("", jpNhanVIen);
        btnNhanVien = "Thêm";
        btnLuuNhanVIen.setEnabled(true);
        cboChucVuNhanVien.setEnabled(true);
        cboGioiTinhNhanVien.setEnabled(true);
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnLuuNhanVIenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhanVIenActionPerformed
        // TODO add your handling code here:

        if (KiemTraNhapNhanVien() == false) {
            return;
        }
        String tenNV = txtHoTenNhanVien.getText().trim();
        String diaChi = txtDiaChiNhanVien.getText().trim();
        String soDienThoai = txtSoDienThoaiNhanVien.getText().trim();
        String email = txtEmailNhanVien.getText().trim();
        String gioiTinh = (String) cboGioiTinhNhanVien.getSelectedItem();
        String ngaySinhString = txtNgaySinhNhanVien.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ngaySinh = LocalDate.parse(ngaySinhString, formatter);
        String soCCCD = txtSoCCCDNhanVien.getText().trim();
        String tentk = txtTaiKhoanNV.getText().trim();
        int maCV = (int) cboChucVuNhanVien.getSelectedIndex() + 1;
        System.out.println(maCV);
        System.err.println(btnNhanVien);
        if (btnNhanVien.equals("Thêm") == true) {
            System.err.println(btnNhanVien);
            if (kiemTraDLNhap(0) == false) {
                System.err.println(btnNhanVien);
                return;
            }
            NhanVien employee = new NhanVien(0, tenNV, diaChi, soDienThoai, email, gioiTinh, ngaySinh, maCV, soCCCD, tentk);
            System.out.println(employee);
            if (nvdao.insertNhanVienWithAccount(employee) == true) {
                Mes.ThongBao("Thêm nhân viên", "Thành Công");
            } else {
                Mes.ThongBao("Thêm nhân viên", "Không Thành Công");
                return;
            }

        } else if (btnNhanVien.equals("Sửa") == true) {
            int manv = Integer.parseInt(txtMaNhanVien.getText());
            System.err.println(manv);
            if (kiemTraDLNhap(manv) == false) {
                System.err.println("Sua");
                return;
            }
            NhanVien employee = new NhanVien(manv, tenNV, diaChi, soDienThoai, email, gioiTinh, ngaySinh, maCV, soCCCD, tentk);
            if (nvdao.updateNhanVien(employee) == true) {
                Mes.ThongBao("Cập Nhật", "Thành Công");
            } else {
                Mes.ThongBao("Cập Nhật", "Không Thành Công");
            }
        }
        ArrayList<NhanVien> ds = nvdao.getDanhSachNV();
        SetDataTableNV(ds);
        Mes.SetEnibleJtextPanel(false, jpNhanVIen);
        btnLuuNhanVIen.setEnabled(false);
        cboChucVuNhanVien.setEnabled(false);
        cboGioiTinhNhanVien.setEnabled(false);
    }//GEN-LAST:event_btnLuuNhanVIenActionPerformed

    private void btnTimKiemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNhanVienActionPerformed
        // TODO add your handling code here:
        ArrayList<NhanVien> ds = nvdao.getDanhSachNVSearch(txtTimKiemNhanVien.getText());
        SetDataTableNV(ds);
        btnLuuNhanVIen.setEnabled(false);
    }//GEN-LAST:event_btnTimKiemNhanVienActionPerformed

    private void btnLamMoiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiKHActionPerformed

    private void tblBangSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangSachMouseClicked
        // TODO add your handling code here:
        int i = tblBangSach.getSelectedRow();
        int maS = (int) tblBangSach.getValueAt(i, 0);
        String tenS = tblBangSach.getValueAt(i, 1).toString().trim();
        String dongGia = tblBangSach.getValueAt(i, 2).toString().trim();
        String slTon = tblBangSach.getValueAt(i, 3).toString().trim();
        String anh = tblBangSach.getValueAt(i, 4).toString().trim();
        anhTableUpdate = tblBangSach.getValueAt(i, 4).toString().trim();
        String mota = tblBangSach.getValueAt(i, 5).toString().trim();
        String manxb = tblBangSach.getValueAt(i, 6).toString().trim();
        String matl = tblBangSach.getValueAt(i, 7).toString().trim();
        String matg = tblBangSach.getValueAt(i, 8).toString();
        String trangThai = (String) tblBangSach.getValueAt(i, 9);
        /// Set Text
        txtMaSach.setText(String.format("%d", maS));
        txtTenSach.setText(tenS);
        txtDonGiaBan.setText(dongGia);
        txtSoLuongTon.setText(slTon);

        String sAnh = srcFile + anh;

        System.out.println(sAnh);
        //lblAnhSach.setIcon(imageIcon);
        lblAnhSach.setIcon(Mes.ResizeImage(sAnh, lblAnhSach));
        txtMoTa.setText(mota);
        // cbo
        int tg = Integer.parseInt(matg) - 1;
        int tl = Integer.parseInt(matl) - 1;
        int nxb = Integer.parseInt(manxb) - 1;
        //cboChucVuNhanVien.setSelectedIndex(combo);
        cboNhaXuatBanSach.setSelectedIndex(nxb);
        cboTheLoaiSach.setSelectedIndex(tl);
        cboTacGiaSach.setSelectedIndex(tg);
        cboTrangThaiSach.setSelectedItem(trangThai);
        Mes.SetEnibleJtextPanel(false, jpSanPham);
        Mes.SetEnibleComBoBoxPanel(false, jpSanPham);
        btnChonAnh.setEnabled(false);
        btnLuuSach.setEnabled(false);
    }//GEN-LAST:event_tblBangSachMouseClicked

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser("Image");
            f.setDialogTitle("Chọn Ảnh");
            f.showOpenDialog(null);

            fileAnh = f.getSelectedFile();
            tenAnh = fileAnh.getName();

            if (srcFile != null) {
                lblAnhSach.setIcon(Mes.ResizeImage(fileAnh.getAbsolutePath(), lblAnhSach));
            }
            chooseImg = true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Đã xảy ra sự cố!", "Oops...", JOptionPane.DEFAULT_OPTION);
            return;
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    public boolean kiemTraNhapSach() {
        String tb = " Thông Báo Nhâp!";
        if (Mes.boolJTextField(txtTenSach) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Tên Sách");
            return false;
        }
        if (Mes.boolJTextField(txtDonGiaBan) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Giá Bán!");
            return false;
        }
        if (Mes.boolJTextField(txtSoLuongTon) == true) {
            Mes.ThongBao(tb, "Chưa nhâp Số lượng tồn");
            return false;
        }
        if (txtMoTa.equals("") == true) {
            Mes.ThongBao(tb, "Chưa nhâp Mô Tả!");
            return false;
        }
        return true;
    }
    private void btnLuuSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSachActionPerformed
        // TODO add your handling code here:
        if (kiemTraNhapSach() == false) {
            return;
        }

        String tens = txtTenSach.getText();

        BigDecimal don = new BigDecimal(txtDonGiaBan.getText());
        int slt = Integer.parseInt(txtSoLuongTon.getText());
        String mota = txtMoTa.getText();
// Hoặc bạn có thể chỉ định chỉ số hàng cần lấy thông tin tên ảnh

        int manxb = cboNhaXuatBanSach.getSelectedIndex() + 1;
        int matl = cboTheLoaiSach.getSelectedIndex() + 1;
        int matg = cboTacGiaSach.getSelectedIndex() + 1;
        String trangthai = (String) cboTrangThaiSach.getSelectedItem();

        if (btnSanPham.equals("Thêm")) {
            if (chooseImg == false) {
                JOptionPane.showConfirmDialog(null, "Bạn chưa chọn ảnh của sinh viên này", "Oops...", JOptionPane.DEFAULT_OPTION);
                return;
            }
            Sach sach = new Sach(0, tens, don, slt, tenAnh, mota, manxb, matl, matg, trangthai);
            if (sachDao.ThemSach(sach) == false) {
                Mes.BaoLoi("Không thể thêm", "Lỗi thêm sách");
                return;
            }
            try {
                File fileDich = new File(srcFile + tenAnh);
                Files.copy(fileAnh.toPath(), fileDich.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Đã sao chép file ảnh thành công!");
                chooseImg = false;
            } catch (IOException ex) {
                Logger.getLogger(TrangChuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (btnSanPham.equals("Sửa")) {
            int mas = Integer.parseInt(txtMaSach.getText());
            Sach sach;
            if (chooseImg == true) {

                sach = new Sach(mas, tens, don, slt, tenAnh, mota, manxb, matl, matg, trangthai);
                try {
                    File fileDich = new File(srcFile + tenAnh);
                    Files.copy(fileAnh.toPath(), fileDich.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Đã sao chép file ảnh thành công!");
                    chooseImg = false;
                } catch (IOException ex) {
                    Logger.getLogger(TrangChuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                sach = new Sach(mas, tens, don, slt, anhTableUpdate, mota, manxb, matl, matg, trangthai);
            }

            if (sachDao.CapNhatSach(sach) == false) {
                Mes.BaoLoi("Không thể thêm", "Lỗi sửa sách");
                return;
            }

            Mes.ThongBao("Thêm sách!", "Thành công");
        }
        PanelSanPham();
    }//GEN-LAST:event_btnLuuSachActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int ma = Integer.parseInt(txtMaSach.getText());
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn khoá khoá?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            boolean i = sachDao.XoaSach(ma);
            Mes.ThongBao("Thông báo khoá", "Khoá Thành Công!");
        }
        ArrayList<Sach> ds = sachDao.getDanhSachBook();
        SetDataTableSach(ds);
        btnSanPham = "Không";
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        Mes.SetEnibleJtextPanel(true, jpSanPham);
        Mes.SetEnibleComBoBoxPanel(true, jpSanPham);
        btnChonAnh.setEnabled(true);
        btnLuuSach.setEnabled(true);
        btnSanPham = "Thêm";
        txtMaSach.setEnabled(false);
        Mes.SetDLJtextPanel("", jpSanPham);
        txtTimKiem.setEnabled(true);

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (Mes.boolJTextField(txtMaSach) == true) {
            Mes.ThongBao("Thông báo chọn!", "Vui lòng chọn sách để sửa!");
            Mes.SetEnibleJtextPanel(false, jpSanPham);
            Mes.SetEnibleComBoBoxPanel(false, jpSanPham);
            return;
        }
        Mes.SetEnibleJtextPanel(true, jpSanPham);
        Mes.SetEnibleComBoBoxPanel(true, jpSanPham);
        btnChonAnh.setEnabled(true);
        btnLuuSach.setEnabled(true);
        btnSanPham = "Sửa";
        txtMaSach.setEnabled(false);
        txtTimKiem.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSoLuongTonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongTonKeyTyped
        // TODO add your handling code here:
        Mes.XetChiNhapSoInt(evt);
    }//GEN-LAST:event_txtSoLuongTonKeyTyped

    private void txtDonGiaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDonGiaBanKeyTyped
        // TODO add your handling code here:
        Mes.XetChiNhapSoInt(evt);
    }//GEN-LAST:event_txtDonGiaBanKeyTyped

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        // TODO add your handling code here:
        PanelSanPham();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String tukhoa = txtTimKiem.getText().trim();

        ArrayList<Sach> dsSach = sachDao.timKiemSach(tukhoa);
        SetDataTableSach(dsSach);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiemDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemDHActionPerformed
        // TODO add your handling code here:
        String s = txtTimKiemDH.getText();
        HoaDonXuatDAO hddao = new HoaDonXuatDAO();
        SetDataTableHoaDon(hddao.xuatDSHoaDonTim(s));
    }//GEN-LAST:event_btnTimKiemDHActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO add your handling code here:
        if(Mes.boolJTextField(txtMatKhauCu)== true)
        {  
            Mes.BaoLoi("Thông báo", "Nhập đầy đủ thông tin!");
            return;
        }
         if(Mes.boolJTextField(txtMatKhauMoi)== true)
        {  
            Mes.BaoLoi("Thông báo", "Nhập đầy đủ thông tin!");
             return;
        }
          if(Mes.boolJTextField(txtNhapLaiMatKhau)== true)
        {  
            Mes.BaoLoi("Thông báo", "Nhập đầy đủ thông tin!");
             return;
        }
       NhanVien nv = nhanvien;
        if(nvdao.KiemTRaNVMatKhau(nv.getUsername(), txtMatKhauCu.getText())== false){
            Mes.BaoLoi("Thông báo !", "Mk Cũ Sai");
            return ;
        }
        if(txtMatKhauMoi.getText().equals(txtNhapLaiMatKhau.getText())== true){
            nvdao.updateMatKhau(txtTenTaiKhoan.getText(), txtMatKhauMoi.getText().trim());
             Mes.ThongBao("Thông báo !", "Thành công đổi MK");
             Mes.SetDLJtextPanel("", jpDoiMK);
             txtTenTaiKhoan.setText(nv.getUsername());
        }
        else{
              Mes.BaoLoi("Thông báo !", "2 mat khau khong trung nhau");
        }
        
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.ButtonGroup btnChucVu;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoiKH;
    private javax.swing.JButton btnLamMoiNhanVien;
    private javax.swing.JButton btnLamMoiTTCN;
    private javax.swing.JButton btnLuuNhanVIen;
    private javax.swing.JButton btnLuuSach;
    private javax.swing.JButton btnLuuTTCN;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnSuaTTCN;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemDH;
    private javax.swing.JButton btnTimKiemKH;
    private javax.swing.JButton btnTimKiemNhanVien;
    private javax.swing.ButtonGroup btnTinhTrang;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JComboBox<String> cboChucVuNhanVien;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboGioiTinhNhanVien;
    private javax.swing.JComboBox<String> cboNhaXuatBanSach;
    private javax.swing.JComboBox<String> cboTacGiaSach;
    private javax.swing.JComboBox<String> cboTheLoaiSach;
    private javax.swing.JComboBox<String> cboTrangThaiSach;
    private javax.swing.JSeparator dongke;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JPanel jp8;
    private javax.swing.JPanel jpDoiMK;
    private javax.swing.JPanel jpHoaDon;
    private javax.swing.JPanel jpKhachHang;
    private javax.swing.JPanel jpNhanVIen;
    private javax.swing.JPanel jpSanPham;
    private javax.swing.JPanel jpTaiKHoan;
    private javax.swing.JLabel lblAnhSach;
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    private javax.swing.JPanel tab7;
    private javax.swing.JPanel tabDoiMatKhau;
    private javax.swing.JPanel tabHoaDon;
    private javax.swing.JPanel tabNhanVien;
    private javax.swing.JPanel tabSanPham;
    private javax.swing.JPanel tabTaiKhoan;
    private javax.swing.JTable tblBangDH;
    private javax.swing.JTable tblBangNhanVien;
    private javax.swing.JTable tblBangSach;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiKhachHang;
    private javax.swing.JTextField txtDiaChiNhanVien;
    private javax.swing.JTextField txtDonGiaBan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailKH;
    private javax.swing.JTextField txtEmailNhanVien;
    private javax.swing.JTextField txtGioiTinhKH;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTenNhanVien;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMatKhauCu;
    private javax.swing.JTextField txtMatKhauMoi;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgaySinhKH;
    private javax.swing.JTextField txtNgaySinhNhanVien;
    private javax.swing.JTextField txtNhapLaiMatKhau;
    private javax.swing.JTextField txtSoCCCD;
    private javax.swing.JTextField txtSoCCCDKH;
    private javax.swing.JTextField txtSoCCCDNhanVien;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtSoDienThoaiNhanVien;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTaiKhoanNV;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemDH;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemNhanVien;
    private javax.swing.JTextField txtUsename;
    // End of variables declaration//GEN-END:variables
}
