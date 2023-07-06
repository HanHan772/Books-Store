/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SP;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maitr
 */
public class Mes {

    public static void ThongBao(String tileBar, String info) {
        JOptionPane.showMessageDialog(null, info, tileBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void BaoLoi(String tileBar, String info) {
        JOptionPane.showMessageDialog(null, info, tileBar, JOptionPane.WARNING_MESSAGE);
    }

    public static void Exit() {
        JFrame frame = new JFrame();
        if (JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    public static void CloseFrom(JFrame j) {
        j.setVisible(false);
    }

    public static void OpenFrom(JFrame j) {
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }

    public static void XetChiNhapSoDouble(KeyEvent keyEvent) {
        JTextField textField = (JTextField) keyEvent.getComponent();
        String text = textField.getText();
        String newText = text + keyEvent.getKeyChar();
        if (!newText.matches("\\d*\\.?\\d*")) {
            keyEvent.consume();
        }
    }

    public static void XetChiNhapSoDoubleCoAm(KeyEvent keyEvent) {
        JTextField textField = (JTextField) keyEvent.getComponent();
        String text = textField.getText();
        String newText = text + keyEvent.getKeyChar();
        if (!newText.matches("-?\\d*\\.?\\d*")) {
            keyEvent.consume();
        }
    }

    public static void XetChiNhapSoInt(KeyEvent keyEvent) {
        // Chỉ sử dụng trong hàm keyTyped
        JTextField textField = (JTextField) keyEvent.getComponent();
        String text = textField.getText();
        String newText = text + keyEvent.getKeyChar();
        if (!newText.matches("\\d*")) {
            keyEvent.consume();
        }
    }

    // Chỉ sử dụng trong hàm keyTyped
    public static void XetChiNhapSoIntCoAm(KeyEvent keyEvent) {
        //   -? đại diện cho ký tự - (nếu có) để cho phép nhập số âm.
        //   \\d* đại diện cho một chuỗi bao gồm các ký tự số.
        JTextField textField = (JTextField) keyEvent.getComponent();
        String text = textField.getText();
        String newText = text + keyEvent.getKeyChar();
        if (!newText.matches("-?\\d*")) {
            keyEvent.consume();
        }
    }

    public static boolean boolJTextField(JTextField jTF) {
        return jTF.getText().isEmpty(); // null -> true  
    }

    public static boolean boolBangO(JTextField jTF) {
        double d = Double.parseDouble(jTF.getText());
        return d == 0;
    }

    // Jtable
    public static void SetDuLieuTable(Vector title, Vector dltable, JTable table) {
        table.setModel(new DefaultTableModel(dltable, title));
    }

    public static void FormatTable(Vector dlTable) {
        dlTable.removeAllElements();

    }

    public static void SetEnibleJtextPanel(boolean KQ, JPanel jp) {
        // true để mở    false để đóng
        Component[] components = jp.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setEnabled(KQ);
            }
        }

    }

    public static void SetEnibleComBoBoxPanel(boolean KQ, JPanel jp) {
        // true để mở    false để đóng
        Component[] components = jp.getComponents();
        for (Component component : components) {
            if (component instanceof JComboBox) {
                JComboBox cbo = (JComboBox) component;
                cbo.setEnabled(KQ);
            }
        }

    }

    public static void SetEditableJtextJPanel(boolean enable, JPanel panel) {
        // false vô hiệu hoá, true có thể chỉnh suả
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setEditable(enable);
            }
        }
    }

    public static void SetDLJtextPanel(String DuLieu, JPanel jp) {
        // true để mở    false để đóng
        Component[] components = jp.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText(DuLieu);
            }
        }
    }

    public static boolean boolInputDate(String inputDate, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(inputDate);
            System.out.println("Dữ liệu hợp lệ");
            return true;
        } catch (ParseException e) {
            System.out.println("Dữ liệu không hợp lệ");
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex); // True -> Hợp lệ
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        // Kiểm tra số điện thoại có 10-11 chữ số và chỉ chứa các ký tự số
        String phoneNumberRegex = "^[0-9]{10,11}$";
        return phoneNumber.matches(phoneNumberRegex);// True -> Hợp lệ
    }

    public static boolean checkCCCDNumber(String cccd) {
        // Kiểm tra số điện thoại có 10-11 chữ số và chỉ chứa các ký tự số
        String ccNumberRegex = "^[0-9]{12}$";
        return cccd.matches(ccNumberRegex);// True -> Hợp lệ
    }

    public static void addComboBox(ArrayList<String> dsCo,JComboBox cbo) {
        // cbo bắt đầu từ 0 
        cbo.removeAllItems(); // Xóa tất cả các phần tử hiện có trong JComboBox
        for (String item : dsCo) {
            cbo.addItem(item); // Thêm từng phần tử từ ArrayList vào JComboBox
        }
    }
    public static ImageIcon ResizeImage(String path, JLabel lable)
    {
        ImageIcon myImage = new ImageIcon(path);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(lable.getWidth(), lable.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
