/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HANH
 */
public class ControlHelper {
    private static ControlHelper instance;
    public static ControlHelper getInstance() {
        if(instance == null)
            instance = new ControlHelper();
        return instance;
    }
    private ControlHelper() {}
    
    public static void questionEndWindow(Component component) {
        int choose = JOptionPane.showConfirmDialog(component, "Bạn có chắc chắn thoát?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choose != JOptionPane.YES_OPTION) {
            ((JFrame)component).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        else System.exit(0);
    }
    public static void showMessageError(Component component, String message, String title) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }
    public static void showMessageWarning(Component component, String message, String title) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.WARNING_MESSAGE);
    }
    public static void showMessageInfomation(Component component, String message, String title) {
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirmYN(Component component, String message, String title) {
        int choose = JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choose != JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }
}
