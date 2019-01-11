import util.huffman.HuffmanTree;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame
{

    public Main()
    {
        setVisible(true);
        setBounds(600, 300, 600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton compress = new JButton("压缩");
        JButton depress = new JButton("解压缩");
        compress.setLocation(new Point(100, 120));
        compress.setSize(80,30);
        depress.setSize(80,30);
        depress.setLocation(new Point(300, 120));
        getContentPane().add(compress);
        getContentPane().add(depress);
        add(compress);
        add(depress);

        compress.addActionListener((e)->{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File selectedFile = chooser.getSelectedFile();
            HuffmanTree f = new HuffmanTree();
            try
            {
                f.compress(selectedFile.getAbsolutePath(), selectedFile.getParent()+File.separator + selectedFile.getName()+".hfp");
                JOptionPane.showMessageDialog(this, "压缩成功");
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }

        });

        depress.addActionListener((e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File selectedFile = chooser.getSelectedFile();
            HuffmanTree f = new HuffmanTree();
            try
            {
                String path = selectedFile.getAbsolutePath();
                f.depress(path, path.substring(0, path.length()-4));
                JOptionPane.showMessageDialog(this, "解压缩成功");
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }));
    }

    public static void main(String[] args) {
       new Main();
    }
}
