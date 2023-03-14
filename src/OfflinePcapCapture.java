import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacketHandler;

public class OfflinePcapCapture {
    public static void main(String[] args) {
        String pcapFilePath = "D:/hello/1212.pcap";
        StringBuilder errorBuffer = new StringBuilder();
        Pcap pcap = Pcap.openOffline(pcapFilePath, errorBuffer);
        if (pcap == null) {
            System.err.println("Error opening pcap file: " + errorBuffer);
            return;
        }

        PcapPacketHandler<String> packetHandler = (packet, user) -> System.out.println(packet.toString());

        int packetCount = pcap.loop(-1, packetHandler, "");
        if (packetCount < 0) {
            System.err.println("Error capturing packets: " + pcap.getErr());
        } else {
            System.out.println("Captured " + packetCount + " packets from " + pcapFilePath);
        }
        pcap.close();
    }
}
