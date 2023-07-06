/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Sach;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Maitr
 */
public class SachDAO {

    public ArrayList<Sach> getDanhSachBook() {
        ArrayList<Sach> books = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();
        String query = "SELECT *FROM Sach;";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int masach = resultSet.getInt("MASACH");
                String tensach = resultSet.getString("TENSACH");
                BigDecimal dongiaban = resultSet.getBigDecimal("DONGIABAN");
                int slton = resultSet.getInt("SLTON");
                String hinhanh = resultSet.getString("HINHANH");
                String mota = resultSet.getString("MOTA");
                int manxb = resultSet.getInt("MANXB");
                int matl = resultSet.getInt("MATL");
                int matg = resultSet.getInt("MATG");
                String trangthai = resultSet.getString("TRANGTHAI");

                Sach book = new Sach(masach, tensach, dongiaban, slton, hinhanh, mota, manxb, matl, matg, trangthai);
                books.add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean XoaSach(int ma) {
        ConnectionDB conet = new ConnectionDB();
        String query = "UPDATE SACH SET TRANGTHAI = N'Dừng Bán' WHERE MASACH = ?";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ma);
            ResultSet resultSet = preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ThemSach(Sach sach) {
        ConnectionDB connect = new ConnectionDB();
        String query = "INSERT INTO SACH (TENSACH, DONGIABAN, SLTON, HINHANH, MOTA, MANXB, MATL, MATG, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sach.getTenSach());
            preparedStatement.setBigDecimal(2, sach.getDonGiaBan());
            preparedStatement.setInt(3, sach.getSoLuongTon());
            preparedStatement.setString(4, sach.getHinhAnh());
            preparedStatement.setString(5, sach.getMoTa());
            preparedStatement.setInt(6, sach.getMaNXB());
            preparedStatement.setInt(7, sach.getMaTL());
            preparedStatement.setInt(8, sach.getMaTG());
            preparedStatement.setString(9, sach.getTrangThai());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean CapNhatSach(Sach sach) {
        ConnectionDB connect = new ConnectionDB();
        String query = "UPDATE SACH SET TENSACH = ?, DONGIABAN = ?, SLTON = ?, HINHANH = ?, MOTA = ?, MANXB = ?, MATL = ?, MATG = ?, TRANGTHAI = ? WHERE MASACH = ?";

        try (Connection connection = connect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sach.getTenSach());
            preparedStatement.setBigDecimal(2, sach.getDonGiaBan());
            preparedStatement.setInt(3, sach.getSoLuongTon());
            preparedStatement.setString(4, sach.getHinhAnh());
            preparedStatement.setString(5, sach.getMoTa());
            preparedStatement.setInt(6, sach.getMaNXB());
            preparedStatement.setInt(7, sach.getMaTL());
            preparedStatement.setInt(8, sach.getMaTG());
            preparedStatement.setString(9, sach.getTrangThai());
            preparedStatement.setInt(10, sach.getMaSach());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Update sach thanh cong");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Sach> timKiemSach(String tuKhoa) {
        ArrayList<Sach> books = new ArrayList<>();
        ConnectionDB connect = new ConnectionDB();
        String query = "SELECT * FROM SACH WHERE TENSACH LIKE ?";

        try (Connection connection = connect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + tuKhoa + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maSach = resultSet.getInt("MASACH");
                String tenSach = resultSet.getString("TENSACH");
                BigDecimal donGiaBan = resultSet.getBigDecimal("DONGIABAN");
                int soLuongTon = resultSet.getInt("SLTON");
                String hinhAnh = resultSet.getString("HINHANH");
                String moTa = resultSet.getString("MOTA");
                int maNXB = resultSet.getInt("MANXB");
                int maTL = resultSet.getInt("MATL");
                int maTG = resultSet.getInt("MATG");
                String trangThai = resultSet.getString("TRANGTHAI");

                Sach book = new Sach(maSach, tenSach, donGiaBan, soLuongTon, hinhAnh, moTa, maNXB, maTL, maTG, trangThai);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    // han
     public static ArrayList<Sach> getDSS()
    {
        ArrayList<Sach> dsS = new ArrayList<>();
        try{
            String sql="SELECT MASACH, TENSACH, DONGIABAN, SLTON FROM SACH";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maSach = rs.getInt("MASACH");   
               String tenSach = rs.getString("TENSACH").trim();
               BigDecimal donGiaBan = rs.getBigDecimal("DONGIABAN");
               int soLuongTon = rs.getInt("SLTON");
               Sach s = new Sach(maSach,tenSach,donGiaBan,soLuongTon);
               dsS.add(s);
            }
            System.out.println("Lấy dữ liệu sách thành công !!");
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu sách");
        }
        return dsS;
    }

    public static int getMaSachQuaTen(String ten)
    {
       int ma = 0;
        try{
            String sql="SELECT MASACH FROM NHACUNGCAP WHERE TENNCC = N'"+ten+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               ma = rs.getInt("MASACH");
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu sách !!");
        }
        return ma;
    }
    public static String getTenQuaMaSach(int ma)
    {
        String ten = "";
        try{
            String sql="SELECT TENSACH FROM SACH WHERE MASACH = '"+ma+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               ten = rs.getString("TENSACH");
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu sách !!");
        }
        return ten;
    }
    
    public static ArrayList<Sach> getDSSachQuaMaPG(int maPG)
    {
        ArrayList<Sach> dsS = new ArrayList<Sach>();
        try{
            String sql="SELECT SACH.MASACH AS 'MASACH',TENSACH,DONGIABAN,CT.SOLUONG AS 'SOLUONG' FROM SACH, CHITIETPHIEUNHAP CT,PHIEUGIAOSACH PG WHERE MAPG = "+maPG+" AND CT.MAPN = PG.MAPN AND SACH.MASACH = CT.MASACH";
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maSach = rs.getInt("MASACH");   
               String tenSach = rs.getString("TENSACH").trim();
               BigDecimal donGiaBan = rs.getBigDecimal("DONGIABAN");
               int soLuong = rs.getInt("SOLUONG");
               Sach s = new Sach(maSach,tenSach,donGiaBan,soLuong);
               dsS.add(s);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu phiếu nhập sách");
        }
        return dsS;
    }
    
    public static ArrayList<Sach> getDSSachQuaSoLuong(int sl)
    {
        ArrayList<Sach> dsS = new ArrayList<Sach>();
        try{
            String sql="SELECT MASACH, TENSACH, DONGIABAN, SLTON FROM SACH WHERE SLTON <= "+sl;
            ConnectionDB cn = new ConnectionDB();
            cn.getConnection();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next())
            {
               int maSach = rs.getInt("MASACH");   
               String tenSach = rs.getString("TENSACH").trim();
               BigDecimal donGiaBan = rs.getBigDecimal("DONGIABAN");
               int soLuong = rs.getInt("SLTON");
               Sach s = new Sach(maSach,tenSach,donGiaBan,soLuong);
               dsS.add(s);
            }
            
        }catch(Exception e)
        {
            System.err.println("Không thể lấy dữ liệu sách");
        }
        return dsS;
    }
}
