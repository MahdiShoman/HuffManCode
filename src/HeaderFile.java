/**
 * header class contain attributes should be saved in the compressed file as a header 
 * like each byte and its frequency 
 */
import java.io.Serializable;


public class HeaderFile implements Serializable{
    private String FileExtension;
    private int FileSize;
    String [] huffmanCodes ;

    public  HeaderFile() {

    }

    public HeaderFile (String FileExtension, int FileSize){
        if (FileExtension.contains("\\")){
            String[] FileNmae = FileExtension.replace('\\', '*').split("\\*");
            //last name in the Path
            this.FileExtension = FileNmae[FileNmae.length-1];
        }
        this.FileSize = FileSize;
    }

    int [] byteFrequencies ;

    public int[] getByteFrequencies() {
        return byteFrequencies;
    }

    public void setByteFrequencies(int[] byteFrequencies) {
        this.byteFrequencies = byteFrequencies;
    }

    void displayHeaderFile() {
        GUI.textArea.setText("Byte\t\thuffCode");
        GUI.textArea.appendText("\n");
        for (int i = 0; i < byteFrequencies.length; i++) {
            if (byteFrequencies[i] > 0) {
                GUI.textArea.appendText(i + "\t\t" + huffmanCodes[i]);
                GUI.textArea.appendText("\n");
            }
        }
    }


    String geHuffmanCodes(int idx){
        return huffmanCodes[idx];
    }
    public String[] getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(String[] huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }


    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int fileSize) {
        FileSize = fileSize;
    }



}
