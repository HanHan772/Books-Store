/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.DoiSach;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author HANH
 */
public class DoiSachDAO {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public DoiSachDAO(){}
    
    public boolean insertDoiSach(DoiSach doiSach) throws Exception {
        int ketQua = JDBCHelper.getInstance().ExecuteNonQuery("delete CHITIETDH where MADH = ? and MASACH = ?", new Object[] {doiSach.getMaDH(), doiSach.getMaSach()});
        if(ketQua > 0) {
            ketQua = JDBCHelper.getInstance().ExecuteNonQuery("update SACH set SLTON = SLTON + ? where MASACH = ?",
                    new Object[] {doiSach.getSoLuong(), doiSach.getMaSach()});
        }
        if(ketQua > 0) {
            ketQua = JDBCHelper.getInstance().ExecuteNonQuery("SET DATEFORMAT DMY INSERT INTO DOISACH(MADH ,MASACH, NGAYDOI,LYDODOI, SOLUONG) values(?, ?, ?, ?, ?)",
                    new Object[]{doiSach.getMaDH(), doiSach.getMaSach(), dateFormat.format(doiSach.getNgayDoi()), doiSach.getLyDoDoiSach(), doiSach.getSoLuong()});
        }
        if (ketQua > 0) {
            return true;
        }
        return false;
    }
}

