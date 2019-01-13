package com.cs.ubbcluj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WifiAnalyser {

    public static String analyse() throws IOException {
        String result = "";
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "netsh wlan show all");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean startReading = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("SHOW INTERFACE CAPABILITIES")) {
                startReading = false;
            }
            if (startReading) {
                result += line;
                result += "\n";
            }
            if (line.contains("SHOW NETWORKS MODE=BSSID")) {
                startReading = true;
            }

        }
        return result;
    }
}
