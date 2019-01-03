import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        RgbMan rgb = new RgbMan();

        /*
        rgb.displayColorOnWhole(new Color(255, 255, 255));
        rgb.displayColor("f", new Color(0, 255, 255));
        rgb.displayColorOnCol(2, new Color(0, 255, 0));*/

        Color waveColor = Color.GREEN;
        Animation waveAnimation = new SimpleHorizontalWaveAnimation(1, waveColor, 500);
        rgb.displayAnimation(waveAnimation);

        Color bgColor = Color.WHITE;
        Animation bgAnimation = new BackgroundAnimation(0, bgColor);
        rgb.displayAnimation(bgAnimation);

    }
}
