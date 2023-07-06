/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Maitr
 */
public class HoanTien {
    private int maDH;
    private int maSach;
    private Date ngayHoanTien;
    private String lyDoHoanTien;
    private int soLuong;

    public HoanTien(int maDH, int maSach, Date ngayHoanTien, String lyDoHoanTien, int soLuong) {
        this.maDH = maDH;
        this.maSach = maSach;
        this.ngayHoanTien = ngayHoanTien;
        this.lyDoHoanTien = lyDoHoanTien;
        this.soLuong = soLuong;
    }
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgayHoanTien() {
        return ngayHoanTien;
    }

    public void setNgayHoanTien(Date ngayHoanTien) {
        this.ngayHoanTien = ngayHoanTien;
    }

    public String getLyDoHoanTien() {
        return lyDoHoanTien;
    }

    public void setLyDoHoanTien(String lyDoHoanTien) {
        this.lyDoHoanTien = lyDoHoanTien;
    }

}
