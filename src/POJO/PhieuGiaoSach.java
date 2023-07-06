/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Maitr
 */
public class PhieuGiaoSach {
     private int maPG;
    private int maPN;
    private String ngayGiao;
    private String thoiGianGiao;

    public PhieuGiaoSach(int maPG, int maPN, String ngayGiao, String thoiGianGiao) {
        this.maPG = maPG;
        this.maPN = maPN;
        this.ngayGiao = ngayGiao;
        this.thoiGianGiao = thoiGianGiao;
    }

    public int getMaPG() {
        return maPG;
    }

    public void setMaPG(int maPG) {
        this.maPG = maPG;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getThoiGianGiao() {
        return thoiGianGiao;
    }

    public void setThoiGianGiao(String thoiGianGiao) {
        this.thoiGianGiao = thoiGianGiao;
    }
    
}
