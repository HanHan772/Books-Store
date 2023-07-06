/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.CTDH_DH;
import POJO.DoiSach;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author HANH
 */
public class CTDH_DH_DAO {

    public CTDH_DH_DAO() {
    }
    
    public Vector getDonHang(String maHD) throws SQLException, Exception {
        return JDBCHelper.getInstance().ExecuteGetVector("select dh.MADH,s.MASACH, s.TENSACH, kh.TENKH, ctdh.SOLUONG, nv.TENNV, CONVERT(varchar(255), dh.NGAYMUA,103) as 'NGAYMUA',  s.DONGIABAN from DONHANG dh,SACH s, KHACHHANG kh, NHANVIEN nv, CHITIETDH ctdh where dh.MAKH = kh.MAKH and dh.MANV = nv.MANV and dh.MADH = ctdh.MADH and ctdh.MASACH = s.MASACH and dh.MADH = ?", new Object[]{maHD}, CTDH_DH.class);
    }
    
     public Vector getAllDonHang() throws SQLException, Exception {
        return JDBCHelper.getInstance().ExecuteGetVector("select dh.MADH,s.MASACH, s.TENSACH, kh.TENKH, ctdh.SOLUONG, nv.TENNV, CONVERT(varchar(255), dh.NGAYMUA,103) as 'NGAYMUA',  s.DONGIABAN from DONHANG dh,SACH s, KHACHHANG kh, NHANVIEN nv, CHITIETDH ctdh where dh.MAKH = kh.MAKH and dh.MANV = nv.MANV and dh.MADH = ctdh.MADH and ctdh.MASACH = s.MASACH", null, CTDH_DH.class);
    }
}
