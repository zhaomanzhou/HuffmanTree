package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import util.huffman.HuffmanTree;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private Button compressBtn;
    @FXML
    private Button uncompressBtn;

    @FXML
    public ProgressBar progressBar;

    private String curDir = "D:\\Maze";

    public void compress()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(curDir));
        File file = fileChooser.showOpenDialog(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(file == null)
        {

            alert.setContentText("未选择文件， 无法压缩");
            alert.show();
            return;
        }
        curDir = file.getParent();
        try {
            new HuffmanTree().compress(file.getAbsolutePath(), file.getParent()+File.separator + file.getName()+".hfp");
            alert.setContentText("压缩成功\n");
        } catch (IOException e) {
            e.printStackTrace();
            alert.setContentText("压缩失败");
        }
        alert.show();

        //HuffmanTree
    }


    public void uncompress()
    {
        FileChooser chooser = new FileChooser();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        File file = chooser.showOpenDialog(null);
        chooser.setInitialDirectory(new File(curDir));
        if(file == null)
        {

            alert.setContentText("未选择文件， 无法解压缩");
            alert.show();
            return;
        }
        curDir = file.getParent();

        HuffmanTree f = new HuffmanTree();
        try
        {

            String path = file.getAbsolutePath();
            f.depress(path, path.substring(0, path.length()-4));
            alert.setContentText("解压缩成功\n");
        } catch (IOException e1)
        {
            alert.setContentText("解压缩失败");
            e1.printStackTrace();
        }
        alert.show();

    }
}
