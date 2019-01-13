package com.cs.ubbcluj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.util.NifSelector;

public class Main {

    public static void wifiAnalyser() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "netsh wlan show all");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean startReading = false;
        while (reader.read() != -1) {
            line = reader.readLine();
            if (line.contains("SHOW INTERFACE CAPABILITIES")) {
                startReading = false;
            }
            if (startReading) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if (line.contains("SHOW NETWORKS MODE=BSSID")) {
                startReading = true;
            }

        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args)  {
        // The class that will store the network device
        // we want to use for capturing.
        PcapNetworkInterface device = null;

        // Pcap4j comes with a convenient method for listing
        // and choosing a network interface from the terminal
        try {
            // List the network devices available with a prompt
            device = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You chose: " + device);
    }
}
