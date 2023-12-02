package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame -> 界面/窗体
    //子类GameJFrame -> 界面/窗体
    //这个就是游戏的主角面

    //创建选项条下的条目对象
    JMenuItem replayItem = new JMenuItem("刷新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("帅哥微信号");

    int [][] data = new int[4][4]; //为打乱图片而创建的 二维数组.
    //记录路径
    String path = "puzzlegame\\image\\animal\\animal2\\";
    //定义一个二维数组,存储正确的数据
    int[][] win ={
        {1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}
    };
    //定义变量,表示步数
    int step = 0;
    public GameJFrame() {                          //构造方法
        //初始化界面 (ctrl+鼠左:查看对应方法)
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱图片的数据)
        initDate();

        //初始化图片
        initImage();

        this.setVisible(true);                        //界面本身是隐藏的需要用 visible调出来
    }
    //记录 xy 表示 图片0 在 索引中的位置.
    int x = 0;
    int y = 0;
    private void initDate() {
        //1.定义一个一维数组,里面有0~15的数字
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //挨个遍历,遍历的时候 和随机 位子的数交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取随机索引,遍历并交换
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //接着,把这个一维数组 添加到二维数组里面
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                //找出 图片编号0 在 打乱后的 位置
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4]  = tempArr[i];
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if(victory()){
            //显示胜利图片
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(402,480,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("steps:"+step);
        stepCount.setBounds(250,130,100,20);
        stepCount.setFont(new Font("Serif", Font.BOLD, 20));
        this.getContentPane().add(stepCount);

        //循环的时候改变图片的名字
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取加载图片的序号(从initDate 传过来)
                int num = data[i][j];

                //创建一个图片 ImageIcon 的对象,然后丢到 新的 JLabel 对象(容器) 里面
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //指定图片位子
                jLabel.setBounds(150 * j + 200, 150 * i +200, 150, 150);
                //给图片(JLabel)添加边框
                jLabel.setBorder(new BevelBorder(1));// 这里的参数可以改成其他数字,也可以写"RAISED"/"LOWERED"
                //把JLabel对象 添加到 界面上
                this.getContentPane().add(jLabel);
            }

        }

        //添加背景图片
        JLabel bg = new JLabel(new ImageIcon("puzzlegame\\image\\background\\5c2327524194a.jpg"));
        bg.setBounds(50,20,900,900);
        this.getContentPane().add(bg);

        //刷新一下界面
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上2个选项的对象(功能/关于我们)
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("认识帅哥");

        //将[条目对象]添加到对应的[选项]中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将2个[选项]添加到[菜单]栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给整个界面设置[菜单]
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(1000, 1080);  //教学视频里的 宽高:603 680
        //设置界面标题
        this.setTitle("拼图游戏-单机版");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式: 当关闭 游戏界面时 虚拟机 也停止
        this.setDefaultCloseOperation(3);

        //Menubar(菜单栏)下方的空白处为"Contentpane" 一般默认(0,0)位子为中心
        //所以这里要让图片从左上角开始的话,需要取消 初始的默认设置
        this.setLayout(null);

        //给整个界面添加 键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65 ){
            //把界面中的所有图片删除
            this.getContentPane().removeAll();
            //加载对应的完整图片
            JLabel all = new JLabel(new ImageIcon(path +"all.jpg"));
            all.setBounds(200,200,600,600);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel bg = new JLabel(new ImageIcon("puzzlegame\\image\\background\\5c2327524194a.jpg"));
            bg.setBounds(50,20,900,900);
            this.getContentPane().add(bg);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        //清空原本出现的所有图片
        this.getContentPane().removeAll();
        //左:37   上:38   右:39   下:40
        int code = e.getKeyCode();
        if(code == 37){
            //超过边界直接退出方法
            if(y==3){
                return;
            }
            //将空白0方块的 坐标和 右方 坐标进行 交换,实现"向左"移动的动作
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;  //注意! 因为0代表的图片坐标变动了,还需要更新这个 坐标.
            step++;//计步器 数字自增

            //最后调用方法
            initImage();

        }else if(code == 38){
            //超过边界直接退出方法
            if(x==3){
                return;
            }
            //将空白0方块的 坐标和 下方 坐标进行 交换,实现"向上"移动的动作
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;  //注意! 因为0代表的图片坐标变动了,还需要更新这个 坐标.
            step++;//计步器 数字自增

            //最后调用方法
            initImage();

        }else if(code == 39){
            //超过边界直接退出方法
            if(y==0){
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;  //注意! 因为0代表的图片坐标变动了,还需要更新这个 坐标.
            step++;//计步器 数字自增

            //最后调用方法
            initImage();
        }else if(code == 40){
            //超过边界直接退出方法
            if(x==0){
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;  //注意! 因为0代表的图片坐标变动了,还需要更新这个 坐标.
            step++;//计步器 数字自增

            //最后调用方法
            initImage();

        }else if(code == 65){ //A
            initImage();
        }else if(code == 87){ //W
            data = new int [][]{
                {1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}
            };
            initImage();
        }

    }
    //判断是否胜利
    public boolean victory(){
        //数组不能直接相等,需要遍历
        for(int i =0;i<data.length;i++){
            for(int j =0;j<data[i].length;j++){
                if(data[i][j] != win[i][j]){
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){
            //计步器清零
            step = 0;
            //打乱二维数组数据
            initDate();
            //重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            //关闭虚拟机
            System.exit(0);
        }else if(obj == accountItem){
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //管理容器对象
            JLabel jLabel = new JLabel(new ImageIcon("C:\\Users\\40632\\IdeaProjects\\java_Leaning\\puzzlegame\\image\\微信图片_20230218142034.jpg"));
            jLabel.setBounds(0,0,685,685);
            jDialog.getContentPane().add(jLabel);
            //给弹窗设置大小
            jDialog.setSize(700,700);
            //弹窗置顶与居中
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭,则无法操作 下面的 界面
            jDialog.setModal(true);          //这个设置很霸道
            //让弹框显示出来
            jDialog.setVisible(true);
        }
    }
}
