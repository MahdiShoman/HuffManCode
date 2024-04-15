import java.io.*;

public class Decompression {

    String filePath;
    String [] huffmanCodes;
    public Decompression(String filePath) {
        this.filePath = filePath;
        huffmanCodes = new String[256];
    }

    void readHuffmanFile () throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(filePath);
        BufferedInputStream inputFile = new BufferedInputStream(in);
        ObjectInputStream ob = new ObjectInputStream(in);
        HeaderFile h = (HeaderFile) ob.readObject();
        huffmanCodes=h.getHuffmanCodes();




    }
}
