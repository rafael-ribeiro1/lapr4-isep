package eapli.base.app.common.console.presentation.menuselect;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {

    private JFrame frame;


    public FileChooser(){
        frame= new JFrame();
    }


    public File chooseFile (){
        File file=null;

        while (file==null){
            System.out.println("Chose file");
            file =  startFileSelection();
        }

        return file;
    }

    private File startFileSelection (){
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Information file", "csv"));
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            frame.setVisible(false);
            return fc.getSelectedFile();
        } else {
            System.out.println("No file selected");
        }
        return null;


    }


}
