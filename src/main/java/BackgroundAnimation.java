import java.awt.*;

public class BackgroundAnimation extends Animation {

    Color background;

    public BackgroundAnimation(int priority, Color background) {
        super(priority);
        this.background = background;
    }

    public void start() {
        for (String[] col : keyMap) {
            for (String key : col) {
                keyColors.put(key, background);
            }
        }
        rgbMan.updateAnimation(this);
    }

    public String toString() {
        return "Background animaton";
    }

}
