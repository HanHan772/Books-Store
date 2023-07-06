/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Maitr
 */
public class DonHang {
     private int maDH;
    private LocalDate ngayMua;
    private BigDecimal thanhTien;
    private Integer maKH;
    private Integer maNV;
    private int maLDH;
    private String trangThai;

    // Constructors, getters, setters
   @Override
    public String toString() {
        return "DonHang{" + "maDH=" + maDH + ", ngayMua=" + ngayMua + ", thanhTien=" + thanhTien + ", maKH=" + maKH + ", maNV=" + maNV + ", maLDH=" + maLDH + ", trangThai=" + trangThai + '}';
    }

    public DonHang(int maDH, LocalDate ngayMua, BigDecimal thanhTien, Integer maKH, Integer maNV, int maLDH, String trangThai) {
        this.maDH = maDH;
        this.ngayMua = ngayMua;
        this.thanhTien = thanhTien;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maLDH = maLDH;
        this.trangThai = trangThai;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public LocalDate getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDate ngayMua) {
        this.ngayMua = ngayMua;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public int getMaLDH() {
        return maLDH;
    }

    public void setMaLDH(int maLDH) {
        this.maLDH = maLDH;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
