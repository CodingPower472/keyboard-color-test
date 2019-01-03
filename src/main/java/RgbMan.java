import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RgbMan {

    CmdMan man = new CmdMan();
    String[][] keyMap = KeyMap.keys;
    // ordered from highest to lowest priority
    ArrayList<Animation> animations = new ArrayList<>();
    ConcurrentHashMap<String, Color> keyColors = new ConcurrentHashMap<>();

    private void addAnimationWithPriority(Animation toAdd) {
        for (int i = 0; i < animations.size(); i++) {
            Animation animation = animations.get(i);
            if (animation.getPriority() < toAdd.getPriority()) {
                animations.add(i, toAdd);
                return;
            }
        }
        animations.add(toAdd);
    }

    private void displayColor(String key, Color color) {
        String daemonCmd = "rgb " + key + ":" + String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        String terminalCmd = String.format("echo '%s' > /dev/input/ckb1/cmd", daemonCmd);
        //System.out.println(terminalCmd);
        man.runCmd(terminalCmd);
    }

    public void displayAnimation(Animation animation) {
        animation.setRgbMan(this);
        animation.start();
        addAnimationWithPriority(animation);
    }

    private void update() {
        synchronized (keyColors) {
            Iterator<Map.Entry<String, Color>> iterator = keyColors.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                //System.out.println(entry.getKey());
                //System.out.println("Key: " + key);
                //System.out.println(iter.next());
                Color color = (Color)entry.getValue();
                displayColor((String)entry.getKey(), color);
            }
        }
    }

    private void printOutKeyColors(Map<String, Color> keyColorMap) {
        for (Map.Entry<String, Color> entry : keyColorMap.entrySet()) {
            String key = entry.getKey();
            Color color = entry.getValue();
            System.out.println("Key: " + key + ", color: " + color);
        }
    }

    public void updateAnimation(Animation toUpdate) {
        System.out.println("Updating animation " + toUpdate);
        HashMap<String, Color> animationKeyColors = toUpdate.getKeyColors();
        for (String key : animationKeyColors.keySet()) {
            Color color = animationKeyColors.get(key);
            boolean keyTaken = false;
            for (int i = animations.indexOf(toUpdate) - 1; i >= 0; i--) {
                Animation animation = animations.get(i);
                if (animation.getKeyColors().containsKey(key)) {
                    keyTaken = true;
                    break;
                }
            }
            if (!keyTaken) {
                keyColors.put(key, color);
            }
        }
        for (String key : toUpdate.getRemovedKeyColors()) {
            System.out.println("Key " + key + " not found, defaulting to other animations' key color");
            for (int i = 0; i < animations.size(); i++) {
                Animation animation = animations.get(i);
                Map<String, Color> thisAnimationKeyColors = animation.getKeyColors();
                if (thisAnimationKeyColors.containsKey(key)) {
                    keyColors.put(key, thisAnimationKeyColors.get(key));
                    break;
                }
            }
        }
        printOutKeyColors(animationKeyColors);
        update();
    }

}
