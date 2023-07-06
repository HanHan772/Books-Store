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
public class PhieuNhapSach {
     private int maPN;
    private String ngayNhap;
    private int maNCC;
    private int maNV;
    private String trangThai;
    private BigDecimal tongTienNhap;

    public PhieuNhapSach() {
    }

    public PhieuNhapSach(int maNCC, int maNV) {
        this.maNCC = maNCC;
        this.maNV = maNV;
    }

    public PhieuNhapSach(int maPN, String ngayNhap, int maNCC, int maNV, String trangThai, BigDecimal tongTienNhap) {
        this.maPN = maPN;
        this.ngayNhap = ngayNhap;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.trangThai = trangThai;
        this.tongTienNhap = tongTienNhap;
    }

    public PhieuNhapSach(int maPN, String trangThai, BigDecimal tongTienNhap) {
        this.maPN = maPN;
        this.trangThai = trangThai;
        this.tongTienNhap = tongTienNhap;
    }

    
    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(BigDecimal tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }

    @Override
    public String toString() {
        return "PhieuNhapSach{" + "maPN=" + maPN + ", ngayNhap=" + ngayNhap + ", maNCC=" + maNCC + ", maNV=" + maNV + ", trangThai=" + trangThai + ", tongTienNhap=" + tongTienNhap + '}';
    }
    
    
    

}
