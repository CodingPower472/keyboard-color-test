import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleHorizontalWaveAnimation extends Animation {
    private int col;
    private Color color;
    private long updateInterval;

    public SimpleHorizontalWaveAnimation(int priority, Color color, long updateInterval) {
        super(priority);
        this.color = color;
        this.updateInterval = updateInterval;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    private void mapCol(int colIndex, Color color) {
        for (String key : keyMap[colIndex]) {
            keyColors.put(key, color);
        }
        rgbMan.updateAnimation(this);
    }

    // Start/reset
    public void start() {
        col = 0;
        Animation that = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer running");
                removedKeyColors = new ArrayList<>();
                if (col > 0) {
                    String[] keyColBefore = keyMap[col - 1];
                    for (String key : keyColBefore) {
                        keyColors.remove(key);
                        removedKeyColors.add(key);
                    }
                }
                if (col >= keyMap.length) {
                    col = 0;
                }
                String[] keyCol = keyMap[col];
                for (String key : keyCol) {
                    keyColors.put(key, color);
                }
                rgbMan.updateAnimation(that);
                col++;
            }
        }, 1000, updateInterval);
    }

    public String toString() {
        return "Simple horizontal wave animation";
    }
}
