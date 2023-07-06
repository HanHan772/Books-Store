/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class NhaCungCapDAO {
    public static ArrayList<String> getDSNhaCungCap()
    {
        ArrayList<String> dsTen = new ArrayList<String>();
        try{
            String sql="SELECT TENNCC FROM NHACUNGCAP";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               String ten= rs.getString("TENNCC");
               dsTen.add(ten);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu nhà cung cấp !!");
        }
        return  dsTen;
    }
    
    public static int getMaQuaTen(String ten)
    {
       int ma = 0;
        try{
            String sql="SELECT MANCC FROM NHACUNGCAP WHERE TENNCC = N'"+ten+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               ma = rs.getInt("MANCC");
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu nhà cung cấp !!");
        }
        return ma;
    }
    
    public static String getTenQuaMa(String ma)
    {
       String ten = "";
        try{
            String sql="SELECT TENNCC FROM NHACUNGCAP WHERE MANCC = '"+ma+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               ten = rs.getString("TENNCC");
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu nhà cung cấp !!");
        }
        return ten;
    }
}
