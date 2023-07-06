/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.TacGia;
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
public class TacGiaDAO {

    public ArrayList<TacGia> getDSTacGia() {
        ArrayList<TacGia> tacGia = new ArrayList<>();
        ConnectionDB conet = new ConnectionDB();
        String query = "SELECT *FROM TACGIA;";

        try (Connection connection = conet.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int maTG = resultSet.getInt("MATG");
                String tenTG = resultSet.getString("TENTG");
                TacGia tg = new TacGia(maTG, tenTG);
                tacGia.add(tg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tacGia;
    }
}
