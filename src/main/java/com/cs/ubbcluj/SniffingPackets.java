package com.cs.ubbcluj;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;

import java.io.IOException;

public class SniffingPackets {

    public static String sniff(String[] args) throws IOException, PcapNativeException, NotOpenException, InterruptedException {
        String filter = "tcp port 80";
        if (args.length != 0) {
            filter = args[0];
        }

        PcapNetworkInterface nif = new NifSelector().selectNetworkInterface();
        if (nif == null) {
            System.exit(1);
        }

        final PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);

        if (filter != null && filter.length() != 0) {
            handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
        }

        String result = "";
        PacketListener listener = packet -> printPacket(packet, handle);

        handle.loop(200, listener);
        return result;
    }

    private static String printPacket(Packet packet, PcapHandle ph) {
        return  "A packet captured at " +
                ph.getTimestamp() +
                ":" +
                "\n" +
                packet;
    }
}
