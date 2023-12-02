package UI;

import javax.swing.*;
import java.awt.*;

public class LoginJFrame extends JFrame {
    //登录界面

    public LoginJFrame(){   //构造方法

        //在创建登录界面的时候,同时给界面设置信息.
        initJFrame2();

        account_password();

        //initial Image of login background
        initLogin();

        this.setVisible(true);

    }

    private void initJFrame2(){
        //宽-高-show
        this.setSize(488,430);
        //设置界面标题
        this.setTitle("拼图游戏-Login");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式: 当关闭 游戏界面时 虚拟机 也停止
        this.setDefaultCloseOperation(3);




    }

    private void initLogin(){

        //add background image
        JLabel bg = new JLabel(new ImageIcon("puzzlegame\\image\\background\\background.png"));
        bg.setBounds(30,30,460,400);
        this.getContentPane().add(bg);

        //refrash the pane
        this.getContentPane().repaint();
    }

    private void account_password(){
        // set account text and password text
        JLabel Jlabel1 = new JLabel("  Account:");
        Jlabel1.setBounds(80,130,120,20);
        Jlabel1.setFont(new Font(null,Font.BOLD,20));
        JLabel Jlabel2 = new JLabel("Password:");
        Jlabel2.setBounds(80,190,120,20);
        Jlabel2.setFont(new Font(null,Font.BOLD,20));
        this.getContentPane().add(Jlabel1);
        this.getContentPane().add(Jlabel2);



        //create a Account box
        JTextField Jaccount = new JTextField();
        //position and size
        Jaccount.setBounds(200,130,200,30);
        Jaccount.setFont(new Font(null,Font.PLAIN,20));
        this.getContentPane().add(Jaccount);

        //create a password box
        JPasswordField Jpass = new JPasswordField();
        //position and size
        Jpass.setBounds(200,190,200,30);
        Jpass.setFont(new Font(null,Font.BOLD,18));
        this.getContentPane().add(Jpass);

        //put image below the login text
        JLabel loginIcon = new JLabel(new ImageIcon("puzzlegame\\image\\background\\enter.png"));
        loginIcon.setBounds(280,250,60,60);
        this.getContentPane().add(loginIcon);

        JLabel forgot = new JLabel(new ImageIcon("puzzlegame\\image\\background\\forgot-password.png"));
        forgot.setBounds(100,250,60,60);
        this.getContentPane().add(forgot);

        JLabel signup = new JLabel(new ImageIcon("puzzlegame\\image\\background\\signup.png"));
        signup.setBounds(170,250,60,60);
        this.getContentPane().add(signup);
    }
}
