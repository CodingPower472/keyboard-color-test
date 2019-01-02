import java.awt.*;

public class RgbMan {

    CmdMan man = new CmdMan();

    public void displayColor(String key, Color color) {
        String daemonCmd = "rgb " + key + ":" + String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        String terminalCmd = String.format("echo '%s' > /dev/input/ckb1/cmd", daemonCmd);
        System.out.println(terminalCmd);
        man.runCmd(terminalCmd);
    }

    public void displayColor(String[] keys, Color color) {
        for (String key : keys) {
            displayColor(key, color);
        }
    }

}
