import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    //static String[] huffmanCodes;
    Compression compression = new Compression();
 static    Input input = new Input();
   static Huffman huffman = new Huffman();
    String inputFilePath;
    static String[]  huffmanCodes = new String[256];

    public Main(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void main() {
       // inputFilePath = "C:\\Users\\ATIYA AMG\\Downloads\\411531664_772596281329388_2917368961244995368_n.gif";   // Replace with the path to your input file
        String outputFilePath = "C:\\Users\\ATIYA AMG\\Downloads\\New Text Document.txt"; // Replace with the path to your output file

        try {
            RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
            FileChannel inputChannel = inputFile.getChannel();

            // FileOutputStream outputFile = new FileOutputStream(new java.io.File(fileName(inputFilePath) + ".huf"));

            // Step 1: Read the file and calculate byte frequencies
            //Input input = new Input(inputFilePath);//-
            input.setInputFilePath(inputFilePath);
             input.calculateByteFrequencies(inputChannel);// get freq for every byte

            //DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFilePath)));
            System.out.println(Arrays.toString(input.getByteFrequencies()));
            // Step 2: Build Huffman Tree
            //Huffman huffman = new Huffman(input.getByteFrequencies());//-
            huffman.setByteFrequencies(input.getByteFrequencies());
            huffman.buildHuffmanTree();
           // Node root = compression.buildHuffmanTree(input.getByteFrequencies());
            System.out.println(input.getNumOfByte() + "hi");


            // Step 3: Generate Huffman Codes
            String[] huffmanCodes = new String[256];
            huffman.generateHuffmanCodes(huffman.getRootHuffmanTree(), "", huffman.getHuffmanCodes());
           // compression.generateHuffmanCodes(huffman.getRootHuffmanTree(), "", huffmanCodes);
            huffmanCodes=huffman.getHuffmanCodes();
            System.out.println(Arrays.toString(huffman.getHuffmanCodes()));

            System.out.println(huffman.getHuffCode(65));

            // Step 4: Replace Bytes with Huffman Codes and Write Compressed Data
            compression.setNumOfByte(input.getNumOfByte());
            compression.getHeaderFile().setHuffmanCodes(huffman.getHuffmanCodes());

            compression.setHuffmanCodes(huffman.getHuffmanCodes());
            compression.getHeaderFile().setFileSize(input.getNumOfByte());
            //filext

            compression.getHeaderFile().setFileExtension(input.getFileExtension());
            compression.getHeaderFile().setByteFrequencies(input.getByteFrequencies());
            compression.writeCompressedData(inputFilePath);
            //displayByteFrequencies(byteFrequencies);
            //huffman.displayHuffmanTable();
            System.out.println(compression.getHeaderFile().toString().length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void getHuffmanTable(){
        huffman.displayHuffmanTable();
    }

}
