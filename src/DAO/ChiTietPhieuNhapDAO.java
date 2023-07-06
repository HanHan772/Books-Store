/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietPhieuNhap;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ChiTietPhieuNhapDAO {
    public static ArrayList<ChiTietPhieuNhap> getDSCTPN()
    {
        ArrayList<ChiTietPhieuNhap> dsPN = new ArrayList<ChiTietPhieuNhap>();
        try{
            String sql="SELECT * FROM CHITIETPHIEUNHAP";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPN = rs.getInt("MAPN");
               int maSach = rs.getInt("MASACH");
               int soLuong = rs.getInt("SOLUONG");
               BigDecimal giaNhap = rs.getBigDecimal("GIANHAP");
               ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(maPN,maSach,soLuong,giaNhap);
               dsPN.add(ctpn);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập sách");
        }
        return dsPN;
    }
    
    public static boolean themChiTietPhieuNhap(ChiTietPhieuNhap ctpn)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="INSERT INTO CHITIETPHIEUNHAP VALUES("+ctpn.getMaPN()+","+ctpn.getMaSach()+","+ctpn.getSoLuong()+","+ctpn.getGiaNhap()+")";
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Thêm chi tiết phiếu nhập mới thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
        
    }
    
    public static boolean suaChiTietPhieuNhap(ChiTietPhieuNhap ctpn)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="UPDATE CHITIETPHIEUNHAP SET SOLUONG = "+ctpn.getSoLuong()+",GIANHAP ="+ctpn.getGiaNhap()+" WHERE MAPN = "+ctpn.getMaPN()+" AND MASACH = "+ctpn.getMaSach();
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Sửa chi tiết phiếu nhập thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
    }
    
    public static boolean xoaChiTietPhieuNhap(int maPN, int maSach)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="DELETE CHITIETPHIEUNHAP WHERE MAPN = "+maPN+" AND MASACH = "+maSach;
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Xóa chi tiết phiếu nhập thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
    }
    
    public static ArrayList<ChiTietPhieuNhap> getCTPNQuaMaPN(int ma)
    {
        ArrayList<ChiTietPhieuNhap> dsPN = new ArrayList<>();
        try{
            String sql="SELECT * FROM CHITIETPHIEUNHAP WHERE MAPN = "+ma;
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPN = rs.getInt("MAPN");
               int maSach = rs.getInt("MASACH");
               int soLuong = rs.getInt("SOLUONG");
               BigDecimal giaNhap = rs.getBigDecimal("GIANHAP");
               ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(maPN,maSach,soLuong,giaNhap);
               dsPN.add(ctpn);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập sách");
        }
        return dsPN;
    }
}
