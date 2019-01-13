package com.cs.ubbcluj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pcap4j.core.*;
import org.pcap4j.util.NifSelector;

import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;

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
        while ((line = reader.readLine()) != null) {
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

    public static void sniffingPackets(String[] args) throws IOException, PcapNativeException, NotOpenException, InterruptedException {
        String filter = "tcp port 80";
        if (args.length != 0) {
            filter = args[0];
        }

        PcapNetworkInterface nif = new NifSelector().selectNetworkInterface();
        if (nif == null) {
            System.exit(1);
        }

        final PcapHandle handle = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10);

        if (filter != null && filter.length() != 0) {
            handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
        }

        PacketListener listener = packet -> printPacket(packet, handle);

        handle.loop(200, listener);
    }

    private static void printPacket(Packet packet, PcapHandle ph) {
        StringBuilder sb = new StringBuilder();
        sb.append("A packet captured at ")
                .append(ph.getTimestamp())
                .append(":");
        System.out.println(sb);
        System.out.println(packet);
    }

    public static void main(String [] args) throws PcapNativeException, IOException, NotOpenException, InterruptedException {
        wifiAnalyser();
//        sniffingPackets(args);
    }
}
