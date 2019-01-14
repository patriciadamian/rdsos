package com.cs.ubbcluj;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.io.IOException;

public class SniffingPackets {

    private static StringBuilder result;

    public static String sniff() throws IOException, PcapNativeException, NotOpenException, InterruptedException {
        String filter = "tcp port 80";

        PcapNetworkInterface nif = Pcaps.findAllDevs().get(0);
        if (nif == null) {
            System.exit(1);
        }

        final PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
        handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);

        result = new StringBuilder("Result: \n");
        PacketListener listener = packet -> printPacket(packet, handle);

        handle.loop(10, listener);
        return result.toString();

    }

    private static void printPacket(Packet packet, PcapHandle ph) {
        result.append("===================================================================================\n");
        result.append("A packet captured at " +
                ph.getTimestamp() +
                ":" +
                "\n" +
                packet
        );
    }
}
