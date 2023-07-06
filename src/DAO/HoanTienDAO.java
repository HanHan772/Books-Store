/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoanTien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author HANH
 */
public class HoanTienDAO {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public boolean insertHoanTien(HoanTien hoanTien) throws SQLException, Exception {
        int ketQua = JDBCHelper.getInstance().ExecuteNonQuery("delete CHITIETDH where MADH = ? and MASACH = ?", new Object[] {hoanTien.getMaDH(), hoanTien.getMaSach()});
        if(ketQua > 0) {
            ketQua = JDBCHelper.getInstance().ExecuteNonQuery("update SACH set SLTON = SLTON + ? where MASACH = ?",
                    new Object[] {hoanTien.getSoLuong(), hoanTien.getMaSach()});
        }
        if(ketQua > 0) {
            ketQua = JDBCHelper.getInstance().ExecuteNonQuery("SET DATEFORMAT DMY insert into HOANTIEN(MADH, MASACH, NGAYHOANTIEN, LYDOHOANTIEN, SOLUONG) values(?, ?, ?, ?, ?)", new Object[]{hoanTien.getMaDH(), hoanTien.getMaSach(), dateFormat.format(hoanTien.getNgayHoanTien()), hoanTien.getLyDoHoanTien(), hoanTien.getSoLuong()});
        }
        if (ketQua > 0) {
            return true;
        }
        return false;
    }
    
    
    
    

}
