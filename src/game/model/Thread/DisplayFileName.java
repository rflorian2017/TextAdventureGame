package game.model.Thread;

import javafx.concurrent.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


// Will show all the file names in a given folder
public class DisplayFileName extends Task<List<String>> {
    final String path = "c:\\Windows\\";
    @Override
    protected List<String> call() throws Exception {
        File folder = new File(path);
        File[] files = folder.listFiles();

        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile()) {
                fileNames.add(files[i].getName());
                this.updateMessage(files[i].getName());
                Thread.sleep(500);
            }
            this.updateProgress(i, files.length);
        }

        return fileNames;
    }
}
