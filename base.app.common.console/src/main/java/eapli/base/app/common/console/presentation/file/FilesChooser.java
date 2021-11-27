package eapli.base.app.common.console.presentation.file;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesChooser {

    private JFrame frame;

    public FilesChooser() {
        frame = new JFrame();
    }

    public List<byte[]> chooseFiles() {
        System.out.println("=== Choose files (Close to end selection) ===");
        frame.setVisible(true);
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);

        List<byte[]> files = new ArrayList<>();
        byte[] file;
        do {
            if (!frame.isVisible()) frame.setVisible(true);
            file = chooseFile();
            if (file != null)
                files.add(file);
        } while (file != null);
        frame.setVisible(false);
        return files;
    }

    private byte[] chooseFile() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            frame.setVisible(false);

            return toByteArray(fc.getSelectedFile());
        } /*else {
            System.out.println("No file selected");
        }*/
        frame.setVisible(false);
        return null;
    }

    private byte[] toByteArray(File file) {
        byte[] arr = new byte[(int) file.length()];
        try (FileInputStream stream = new FileInputStream(file)) {
            stream.read(arr);
            return arr;
        } catch (IOException e) {
            System.out.println("Erro no ficheiro selecionado");
            return null;
        }
    }

}
