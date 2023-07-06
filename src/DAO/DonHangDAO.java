/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.DonHang;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DonHangDAO {
    public static ArrayList<DonHang> getDonHang()
        {
        ArrayList<DonHang> dsSach = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();
        String query = "SELECT * FROM DONHANG";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maDH = resultSet.getInt("MADH");
                LocalDate ngayMua = resultSet.getDate("NGAYMUA").toLocalDate();
                BigDecimal thanhTien = resultSet.getBigDecimal("THANHTIEN");
                int maKH = resultSet.getInt("MAKH");
                int maNV = resultSet.getInt("MANV");
                int maLDH = resultSet.getInt("MALDH");
                String trangThai = resultSet.getString("TRANGTHAI").trim();
                DonHang employee = new DonHang(maDH,ngayMua,thanhTien,maKH,maNV,maLDH,trangThai);
                dsSach.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSach;
    }
    public static DonHang getDH(int MaDH) {
        ArrayList<DonHang> dhs = getDonHang();
        for(DonHang donHang : dhs) {
            if(donHang.getMaDH() == MaDH) {
                return donHang;
            }
        }
        return null;
    }
    public static int insertDonHang(int maKH, int maNV, String trangThai) {
        ConnectionDB conet = new ConnectionDB();
        int result = 0;
        String query = "insert into DONHANG(MAKH,MANV,TRANGTHAI) values(?, ?, ?)";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, maKH);
            preparedStatement.setInt(2, maNV);
            preparedStatement.setString(3, trangThai);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "select MAX(MADH) as 'MADH' from DONHANG";
        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("MADH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void insertCTDH(int maDH, int maSach, int sl) {
        ConnectionDB conet = new ConnectionDB();
        int result = 0;
        String query = "insert into CHITIETDH(MADH, MASACH, SOLUONG) values (?, ?, ?)";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, maDH);
            preparedStatement.setInt(2, maSach);
            preparedStatement.setInt(3, sl);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
