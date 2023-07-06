/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.PhieuNhapSach;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PhieuNhapDAO {
    public static ArrayList<String> getMaPhieuNhap()
    {
        ArrayList<String> dsMaPN = new ArrayList<>();
        try{
            String sql="SELECT MAPN FROM PHIEUNHAPSACH";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               String ma= rs.getString("MAPN");
               dsMaPN.add(ma);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập !!");
        }
        return dsMaPN;
    }
    
    public static boolean themPhieuNhap(int maNCC, int maNV)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="INSERT INTO PHIEUNHAPSACH(MANCC,MANV) VALUES ("+maNCC+","+maNV+")";
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Thêm phiếu nhập mới thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
        
    }
    
    public static ArrayList<PhieuNhapSach> getDSPN()
    {
        ArrayList<PhieuNhapSach> dsPN = new ArrayList<PhieuNhapSach>();
        try{
            String sql="SELECT MAPN,CONVERT(VARCHAR(255),NGAYNHAP,103) AS 'NGAYNHAP',MANCC,MANV,TRANGTHAI,TONGTIENNHAP FROM PHIEUNHAPSACH";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPN = rs.getInt("MAPN");   
               String ngayNhap = rs.getString("NGAYNHAP");
               int maNCC = rs.getInt("MANCC");
               int maNV = rs.getInt("MANV");
               String trangThai = rs.getString("TRANGTHAI");
               BigDecimal tongTienNhap = rs.getBigDecimal("TONGTIENNHAP");
               PhieuNhapSach pn = new PhieuNhapSach(maPN,ngayNhap,maNCC,maNV,trangThai,tongTienNhap);
               dsPN.add(pn);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập sách");
        }
        return dsPN;
    }
    
    public static ArrayList<PhieuNhapSach> timKiemPhieuNhap(String txtSearch)
    {
        int ma = Integer.parseInt(txtSearch.trim());
        ArrayList<PhieuNhapSach> dsKQ = new ArrayList<>();
        try{
            String sql="SELECT * FROM PHIEUNHAPSACH WHERE MAPN = "+ma;
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPN = rs.getInt("MAPN");   
               String ngayNhap = rs.getString("NGAYNHAP");
               int maNCC = rs.getInt("MANCC");
               int maNV = rs.getInt("MANV");
               String trangThai = rs.getString("TRANGTHAI");
               BigDecimal tongTienNhap = rs.getBigDecimal("TONGTIENNHAP");
               PhieuNhapSach pn = new PhieuNhapSach(maPN,ngayNhap,maNCC,maNV,trangThai,tongTienNhap);
               dsKQ.add(pn);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập !!");
        }
        return dsKQ;
    }
    
}
