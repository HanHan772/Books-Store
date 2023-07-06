/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.math.BigDecimal;

/**
 *
 * @author Maitr
 */
public class HoaDonXuat {
       private int maDH;
    private String tenKH;
    private String tenNV;
    private BigDecimal thanhTien;
    private String trangThai;

    public HoaDonXuat(int maDH, String tenKH, String tenNV, BigDecimal thanhTien, String trangThai) {
        this.maDH = maDH;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
