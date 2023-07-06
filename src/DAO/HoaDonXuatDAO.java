/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoaDonXuat;
import POJO.NhanVien;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maitr
 */
public class HoaDonXuatDAO {

    public ArrayList<HoaDonXuat> xuatDSHoaDon() {
        String query = "EXEC dbo.GetDonHangInfo";
        ArrayList<HoaDonXuat> dsHD = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maDH = resultSet.getInt("MADH");
                String tenKH = resultSet.getString("TENKH");
                String tenNV = resultSet.getString("TENNV");
                BigDecimal thanhTien = resultSet.getBigDecimal("THANHTIEN");
                String trangThai = resultSet.getString("TRANGTHAI");

                HoaDonXuat hoaDon = new HoaDonXuat(maDH, tenKH, tenNV, thanhTien, trangThai);
                dsHD.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<HoaDonXuat> xuatDSHoaDonTim(String s) {
        String query = "SELECT d.MADH, kh.TENKH, nv.TENNV, d.THANHTIEN, d.TRANGTHAI "
                + "FROM DONHANG d "
                + "JOIN KHACHHANG kh ON d.MAKH = kh.MAKH "
                + "JOIN NHANVIEN nv ON d.MaNV = nv.MANV "
                + "JOIN LOAIDH l ON d.MALDH = l.MALDH "
                + "WHERE d.MADH like ? OR kh.MAKH like ? OR nv.MANV like ?";
        if(s.equals("")== true)
        {
            query = "EXEC dbo.GetDonHangInfo";
        }
        ArrayList<HoaDonXuat> dsHD = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if(s.equals("")== false)
        {
              int maDH = -1;
            int maKH = -1;
            int maNV = -1;

            try {
                maDH = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                // Không phải là số, bỏ qua mã đơn
            }

            try {
                maKH = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                // Không phải là số, bỏ qua mã khách hàng
            }

            try {
                maNV = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                // Không phải là số, bỏ qua mã nhân viên
            }

            preparedStatement.setInt(1, maDH);
            preparedStatement.setInt(2, maKH);
            preparedStatement.setInt(3, maNV);
        }
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maDHResult = resultSet.getInt("MADH");
                String tenKH = resultSet.getString("TENKH");
                String tenNV = resultSet.getString("TENNV");
                BigDecimal thanhTien = resultSet.getBigDecimal("THANHTIEN");
                String trangThai = resultSet.getString("TRANGTHAI");

                HoaDonXuat hoaDon = new HoaDonXuat(maDHResult, tenKH, tenNV, thanhTien, trangThai);
                dsHD.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHD;
    }
}
