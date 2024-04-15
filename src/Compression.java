import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.PriorityQueue;
import java.util.Stack;

public class Compression {
    /*
    *to read the orgen file and exchange simple byte with huffman bytes
    * write the result to the file
    * have header size
    * extension of file
    * simplify huffman code
     * */

   static String [] huffmanCodes ;
    Node node ;

     int numOfByte =0;

    public int getNumOfByte() {
        return numOfByte;
    }

    public void setNumOfByte(int numOfByte) {
        this.numOfByte = numOfByte;
    }

/*     void compressAndWriteData(String inputFilePath, String[] huffmanCodes) throws IOException {
        RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
        FileChannel inputChannel = inputFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8);// 8 byte
        StringBuilder compressedData = new StringBuilder();


        while (( inputChannel.read(buffer)) > 0) {
            buffer.flip(); // Change the buffer from write mode to read mode

            while (buffer.hasRemaining()) {
                byte currentByte = buffer.get();
                compressedData.append(huffmanCodes[Byte.toUnsignedInt(currentByte)]);
            }

            buffer.clear(); // Clear the buffer for the next cycle
        }
        System.out.println(compressedData.length()+"hh");
        // Write compressed data to the output file
        writeCompressedData(inputFilePath);
    }*/

    public Compression() {
        huffmanCodes = new String[256];
    }

   static HeaderFile headerFile = new HeaderFile();

    public HeaderFile getHeaderFile() {
        return headerFile;
    }

    public  void setHeaderFile(HeaderFile headerFile) {
        Compression.headerFile = headerFile;
    }
    
    void writeCompressedData(String inputFilePath) throws IOException {
        //attack the header to the file


        FileOutputStream outputFile = new FileOutputStream(new java.io.File(("File") + ".huf"));
        ObjectOutputStream outF = new ObjectOutputStream(outputFile);
        // outF.writeObject(headerFile);

        // bufferedSteam is used to write data into .huf file
        BufferedOutputStream bufferedSteam = new BufferedOutputStream(outputFile);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(inputFilePath));

        byte[] output = new byte[4];//
        int count = 0;
        byte temp;
        int outputCount = 0; // index for output array
        String outputByte = new String();

        //while loop which breaks when all bytes are read
        while (count < numOfByte) {
            /*
             * read each byte, search for its huffman code
             * write each four bytes to the output file
             *  */
            count++;
            //read byte , then search if huffman code exists
            byte tempByte = (byte) in.read();
            String huffmanCode = search( Byte.toUnsignedInt(tempByte));
            outputByte+=(huffmanCode);//it has the huff code
            //00011
            // if output byte is less than 8 bits , continue to append more bits to the output String
            if (outputByte.length() < 8)
                continue;

            temp = toByte(outputByte.substring(0, 8));
            output[outputCount] = temp;
            outputCount++;
            //  HuffnumOfByte++;

            if (outputByte.length() > 8)//?
                // if huffmanCode contain more than 1 byte, we want to keep all bits more than needed byte
                outputByte = outputByte.substring(8);
            else
                outputByte = "";

            // write 4 bytes into .huf file when outputbyte [] is filled
            if (outputCount == 4) {

                bufferedSteam.write(output);
                outputCount = 0;
                output = new byte[4];
            }

        }//end while
        if (outputByte.length() != 0) {//?
            for (int i = 0; i < 8; i++)
                outputByte="0"+outputByte;
            temp = toByte(outputByte.substring(0, 8));
            output[outputCount] = temp;
            outputCount++;
         //   HuffnumOfByte++;
        }
        if (outputCount != 0)
            bufferedSteam.write(output);



        bufferedSteam.close();
        in.close();
        //outF.close();
        //int length = compressedData.length();
        //outputStream.writeInt(length);

    /*    // Write compressed data as bits to the output stream
        for (int i = 0; i < length; i += 8) {
            String chunk = compressedData.substring(i, Math.min(i + 8, length));
            int paddedByte = Integer.parseInt(chunk, 2);
            outputStream.writeByte(paddedByte);
        }*/
    }
    String search(int tempByte){
        return huffmanCodes[tempByte];
    }
    public  byte toByte(String huffmanCode) {
        /*
         * bit wise OR, if the 8 bits are tested return result byte
         * */
        int bitCount = 0;
        byte temp = 0;
        for (byte iIndex = 0; iIndex < huffmanCode.length(); bitCount++, iIndex++) {
            if ((huffmanCode.charAt(iIndex) + "").equals("1"))
                // bit wise or
                //	temp=(byte) (temp| (1<<(7-bitCount%8)));
                temp |= (1 << (7 - bitCount % 8));
            if (bitCount == 7)
                return temp;
        }
        return temp; // return revived value
    }
    public  String fileName(String fileName) {
        int indexOfDot = -1;
        for (int i = fileName.length() - 1; i >= 0; i--)// move from last idx of string
            if ((fileName.charAt(i) + "").equals(".")) {
                //index of last dot is found, break the loop
                indexOfDot = i;
                break;
            }
        return fileName.substring(0, indexOfDot);//get name without extension
    }


    public String[] getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(String[] huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }
}
