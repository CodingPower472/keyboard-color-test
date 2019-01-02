import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdMan {
    public void runCmd(String cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder(new String[] { "/bin/bash", "-c", cmd });
            pb.start();
        } catch (Throwable err) {
            System.err.println("Error calling command: " + err);
            err.printStackTrace();
        }
    }
}
