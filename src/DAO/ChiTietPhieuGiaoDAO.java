/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietPhieuGiao;
import java.sql.ResultSet;
import java.util.ArrayList;



/**
 *
 * @author PC
 */
public class ChiTietPhieuGiaoDAO {
    public static ArrayList<ChiTietPhieuGiao> getDSCTPG()
    {
        ArrayList<ChiTietPhieuGiao> dsPG = new ArrayList<>();
        try{
            String sql="SELECT * FROM CHITIETPHIEUGIAO";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPG = rs.getInt("MAPG");
               int maSach = rs.getInt("MASACH");
               int soLuong = rs.getInt("SOLUONG");
               ChiTietPhieuGiao ctpg = new ChiTietPhieuGiao(maPG,maSach,soLuong);
               dsPG.add(ctpg);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu giao sách");
        }
        return dsPG;
    }
    
    public static boolean themChiTietPhieuGiao(ChiTietPhieuGiao ctpg)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="INSERT INTO CHITIETPHIEUGIAO VALUES("+ctpg.getMaPG()+","+ctpg.getMaSach()+","+ctpg.getSoLuong()+")";
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Thêm chi tiết phiếu nhập mới thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
        
    }
    
    public static boolean suaChiTietPhieuGiao(ChiTietPhieuGiao ctpg)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="UPDATE CHITIETPHIEUGIAO SET SOLUONG = "+ ctpg.getSoLuong() +"WHERE MAPG ="+ctpg.getMaPG()+" AND MASACH ="+ctpg.getMaSach();
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Sửa chi tiết phiếu giao thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
    }
    
    public static boolean xoaChiTietPhieuGiao(int maPG, int maSach)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="DELETE CHITIETPHIEUGIAO WHERE MAPG = "+maPG+" AND MASACH = "+maSach;
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Xóa chi tiết phiếu giao thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
    }
    
    public static ArrayList<ChiTietPhieuGiao> getCTPGQuaMaPG(int ma)
    {
        ArrayList<ChiTietPhieuGiao> dsPG = new ArrayList<>();
        try{
            String sql="SELECT * FROM CHITIETPHIEUGIAO WHERE MAPG = "+ma;
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
           ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPG = rs.getInt("MAPG");
               int maSach = rs.getInt("MASACH");
               int soLuong = rs.getInt("SOLUONG");
               ChiTietPhieuGiao ctpg = new ChiTietPhieuGiao(maPG,maSach,soLuong);
               dsPG.add(ctpg);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu giao sách");
        }
        return dsPG;
    }
}
