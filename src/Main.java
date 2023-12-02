import UI.GameJFrame;
import UI.LoginJFrame;
import UI.RegisterJFrame;

public class Main {
    public static void main(String[] args) {
        //程序的启动入口
        //如果想要开启一个界面, 就创建对应的对象
        new LoginJFrame();
        new GameJFrame();
        new RegisterJFrame();
    }
}