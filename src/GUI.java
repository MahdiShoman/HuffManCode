import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.io.File;

public class GUI extends Application {
    private Main mainManage;
   static TextArea textArea = new TextArea();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Compression Tool");

        //primaryStage.setResizable(false);

        BorderPane root = new BorderPane();


        textArea.setMaxWidth(300);
        textArea.setMaxHeight(300);
        textArea.setEditable(false);
        root.setCenter(textArea);

        Label label1 = new Label("W E L C O M");
        //root.setTop(label1);
        label1.setFont(new Font("Arial", 20));
        label1.setTextFill(Color.BLUE);
        Label statusLabel = new Label("");
        statusLabel.setFont(new Font("Arial", 20));
        statusLabel.setTextFill(Color.BLUE);

        HBox vBox = new HBox();
        vBox.getChildren().add(statusLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(30);
        vBox.setSpacing(60);
        root.setBottom(vBox);
        HBox buttonBox = new HBox(10);

        Button btnCompress = new Button("Compress");
        btnCompress.setOnAction(e -> { // action of compress btn
            statusLabel.setText("");
            // read file from file browser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose File to Compress");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                String filePath = selectedFile.getAbsolutePath();
                mainManage = new Main(filePath);

                try {
                    mainManage.main();
                    statusLabel.setText("Done!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button btnDecompress = new Button("Decompress");
        btnDecompress.setOnAction(e -> {
            statusLabel.setText("");
            // choose file using file browser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose File to Decompress");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                String filePath = selectedFile.getAbsolutePath();
                System.out.println(filePath);
                if (filePath.endsWith(".huf")) {
                    statusLabel.setText("In progress...");

                   // Decompress decompress = new Decompress(filePath);
                    try {
                       // decompress.readHuffFile();
                        statusLabel.setText("Done!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showErrorDialog();
                }
            }
        });

        Button btnHeader = new Button("Header");// name file , header size  ,contant header
        btnHeader.setOnAction(e -> {
            HeaderFile header = Compression.headerFile;
            textArea.setText("File Extension: " + header.getFileExtension()
                    + "\nFile Size: " + header.getFileSize() + "\n "
                    + "Byte " + "  " + "Count \n");
            header.displayHeaderFile();
           /* for (int i = 0; i < header.getBytes().length; i++)
                headerText.append(header.getBytes()[i])
                        .append("  ")
                        .append(header.getCount()[i])
                        .append("\n");

            textArea.setText(headerText.toString());*/
        });

        Button btnHuffmanCode = new Button("Huffman Code & Frequency");// byte , huffman code

        btnHuffmanCode.setOnAction(e -> {
            try {
                mainManage.getHuffmanTable();
            }catch (NullPointerException nullPointerException){
                System.out.println(nullPointerException.getMessage());
            }

        });


        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> Platform.exit());

        buttonBox.getChildren().addAll(btnCompress, btnDecompress, btnHeader, btnHuffmanCode, btnCancel);
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(label1,buttonBox);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setMinHeight(100);
        vBox1.setMinSize(100,50);
        vBox1.setSpacing(30);
        buttonBox.setAlignment(Pos.CENTER);

        root.setTop(vBox1);

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showErrorDialog() {
        Platform.runLater(() -> {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selected file should be (.huf) file, choose another one.");
            alert.showAndWait();
        });
    }
}
