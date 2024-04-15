import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Input {
    /* It's for:
     * read file
     * get freq for all bytes
     * get huffman code
     *
     * */

    String inputFilePath;
    int[] byteFrequencies;

    int numOfByte = 0;// to get file size in bits (to use it in compression operation )

    public Input() {//in GUI

    }

    public Input(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public int[] getByteFrequencies() {
        return byteFrequencies;
    }

    public void setByteFrequencies(int[] byteFrequencies) {
        this.byteFrequencies = byteFrequencies;
    }

    public int getNumOfByte() {
        return numOfByte;
    }

    public void setNumOfByte(int numOfByte) {
        this.numOfByte = numOfByte;
    }

    public void  calculateByteFrequencies(FileChannel inputChannel) throws IOException {
        /*
         * to read file 8 byte every cycle
         * get Byte Frequencies
         * */
        ByteBuffer buffer = ByteBuffer.allocate(8);

        byteFrequencies = new int[256]; //  bytes range from 0 to 255

        while ((inputChannel.read(buffer)) > 0) {
            buffer.flip(); // Change the buffer from write mode to read mode

            //  System.out.print("Read in cycle: ");
            while (buffer.hasRemaining()) {
                byte currentByte = buffer.get();
                //  System.out.print(currentByte + " ");

                // Update byte frequencies
                byteFrequencies[Byte.toUnsignedInt(currentByte)]++;
                numOfByte++;
            }
            //System.out.println();

            buffer.clear(); // Clear the buffer for the next cycle
        }


    }
    public  String getFileExtension() {
        int indexOfDot = -1;
        for (int i = inputFilePath.length() - 1; i >= 0; i--)// move from last idx of string
            if ((inputFilePath.charAt(i) + "").equals(".")) {
                //index of last dot is found, break the loop
                indexOfDot = i;
                break;
            }
        return inputFilePath.substring( indexOfDot , inputFilePath.length()-1);//get name without extension
    }


}
