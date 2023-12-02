package UI;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //注册界面

    public RegisterJFrame(){                     //构造方法
        this.setSize(488,500);
        //设置界面标题
        this.setTitle("拼图游戏-注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式: 当关闭 游戏界面时 虚拟机 也停止
        this.setDefaultCloseOperation(3);





        this.setVisible(true);
    }
}
