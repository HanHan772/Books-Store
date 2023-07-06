/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.PhieuGiaoSach;
import POJO.PhieuNhapSach;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PhieuGiaoDAO {
    public static boolean themPhieuGiao(int maPN, String time)
    {
        ConnectionDB cn = new ConnectionDB();
        cn.getConnection();
        String sql="INSERT INTO PHIEUGIAOSACH(MAPN, THOIGIANGIAO) VALUES("+maPN+",'"+time+"')";
        try{
            
            cn.executeUpdate(sql);
            System.out.println("Thêm phiếu giao mới thành công !!");
            return true;
        }catch(Exception e)
        { 
            
            return false;
        }
        
    }
    
    public static ArrayList<PhieuGiaoSach> getDSPG()
    {
        ArrayList<PhieuGiaoSach> dsPG = new ArrayList<>();
        try{
            String sql="SELECT * FROM PHIEUGIAOSACH";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maPG = rs.getInt("MAPG");  
               int maPN = rs.getInt("MAPN"); 
               String ngayGiao = rs.getString("NGAYGIAO");
               String thoiGianGiao = rs.getString("THOIGIANGIAO");
               PhieuGiaoSach pg = new PhieuGiaoSach(maPG,maPN,ngayGiao,thoiGianGiao);
               dsPG.add(pg);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu giao sách");
        }
        return dsPG;
    }
    
    public static ArrayList<PhieuGiaoSach> timKiemPhieuGiao(String txtSearch)
    {
        int ma = Integer.parseInt(txtSearch.trim());
        ArrayList<PhieuGiaoSach> dsKQ = new ArrayList<>();
        try{
            String sql="SELECT * FROM PHIEUGIAOSACH WHERE MAPG = "+ma;
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
              int maPG = rs.getInt("MAPG");  
               int maPN = rs.getInt("MAPN"); 
               String ngayGiao = rs.getString("NGAYGIAO");
               String thoiGianGiao = rs.getString("THOIGIANGIAO");
               PhieuGiaoSach pg = new PhieuGiaoSach(maPG,maPN,ngayGiao,thoiGianGiao);
               dsKQ.add(pg);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu giao !!");
        }
        return dsKQ;
    }
}
