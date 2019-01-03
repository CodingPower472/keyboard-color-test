import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Animation {
    protected String[][] keyMap = KeyMap.keys;
    protected RgbMan rgbMan;
    protected HashMap<String, Color> keyColors = new HashMap<>();
    protected ArrayList<String> removedKeyColors = new ArrayList<String>();
    private int priority;

    protected Animation(int priority) {
        this.priority = priority;
    }

    public HashMap<String, Color> getKeyColors() {
        return keyColors;
    }

    public void setRgbMan(RgbMan rgbMan) {
        this.rgbMan = rgbMan;
    }

    public int getPriority() {
        return priority;
    }

    public ArrayList<String> getRemovedKeyColors() { return removedKeyColors; }

    public abstract String toString();
    public abstract void start();
}
