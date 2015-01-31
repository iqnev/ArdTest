/**
 * Frame structure: 
 * StartByte|CommenadIndeficator|PayloadLength|Payload|CRC|EndFrame
 */
package testcomunication;

/**
 *
 * @author iqnev
 */
public interface DataLinkLayer {
    /**
     * Converts a command into a byte stream that matches the format of the frame.
     *
     * @param _command the {@link Command} to be send.
     * @return the frame as a byte array.
     */
    public byte[] getRequestPackage(Command _command);

    /**
     * Parses the incoming byte stream from the physical-layer into packets.
     *
     * @param _connection represents the physical-layer; implementation of {@code IOConnection} e.g Bluetooth, Serial, etc.
     * @return the received {@code Packet}.
     */
    public Packet parsePacket(Connection _connection);

}
