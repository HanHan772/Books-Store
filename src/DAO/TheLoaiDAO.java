/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Sach;
import POJO.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maitr
 */
public class TheLoaiDAO {

    public ArrayList<TheLoai> getDSTheLoai() {
        ArrayList<TheLoai> theLoai = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();
        String query = "SELECT *FROM TheLoai;";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int maTL = resultSet.getInt("MATL");
                String tenTL = resultSet.getString("TENTL");

                TheLoai tl = new TheLoai(maTL, tenTL);
                theLoai.add(tl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theLoai;
    }
}
