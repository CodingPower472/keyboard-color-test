import java.awt.*;

public class Main {
    public static void main(String[] args) {
        RgbMan rgb = new RgbMan();

        rgb.displayColor("f", new Color(0, 255, 255));

        CmdMan cmd = new CmdMan();
        cmd.runCmd("echo 'helloworld' > helloworld.txt");
    }
}
