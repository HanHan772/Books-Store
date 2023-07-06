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
public class ChiTietPhieuNhap {
     int maPN,maSach,soLuong;
    BigDecimal giaNhap;

    public ChiTietPhieuNhap(int maPN, int maSach, int soLuong, BigDecimal giaNhap) {
        this.maPN = maPN;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    public ChiTietPhieuNhap() {
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhap{" + "maPN=" + maPN + ", maSach=" + maSach + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + '}';
    }
    
}
