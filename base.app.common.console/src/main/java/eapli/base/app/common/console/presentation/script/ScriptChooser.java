package eapli.base.app.common.console.presentation.script;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScriptChooser {

    private JFrame frame;

    public ScriptChooser(){
        frame = new JFrame();
    }

    public byte[] chooseScript(){
        System.out.println("=== Choose Script ===");
        byte[] script = null;
        frame.setVisible(true);
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);
        JFileChooser fc = new JFileChooser();
        do {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                frame.setVisible(false);
                script = toByteArray(fc.getSelectedFile());
                return script;
            }
        }while (script == null);
        frame.setVisible(false);
        return script;
    }

    private byte[] toByteArray(File file){
        try {
            return Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return null;
    }
}
