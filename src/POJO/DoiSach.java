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
public class DoiSach {
    private int MaDH;
    private int MaSach;
    private Date NgayDoi;
    private String LyDoDoiSach;
    private int SoLuong;

    public DoiSach() {
    }

    public DoiSach(int MaDH, int MaSach, Date NgayDoi, String LyDoDoiSach, int SoLuong) {
        this.MaDH = MaDH;
        this.MaSach = MaSach;
        this.NgayDoi = NgayDoi;
        this.LyDoDoiSach = LyDoDoiSach;
        this.SoLuong = SoLuong;
    }
    
    

    public int getMaDH() {
        return MaDH;
    }

    public void setMaDH(int MaDH) {
        this.MaDH = MaDH;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public Date getNgayDoi() {
        return NgayDoi;
    }

    public void setNgayDoi(Date NgayDoi) {
        this.NgayDoi = NgayDoi;
    }

    public String getLyDoDoiSach() {
        return LyDoDoiSach;
    }

    public void setLyDoDoiSach(String LyDoDoiSach) {
        this.LyDoDoiSach = LyDoDoiSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    
}
