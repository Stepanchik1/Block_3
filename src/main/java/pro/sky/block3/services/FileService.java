package pro.sky.block3.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class FileService {
    @Value("${path.to.files.folder}")
    private String dataFilePath;

    @Value("${name.of.data.file}")
    private String dataName;

    private final Path PATH = Path.of(dataFilePath, dataName);

    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(PATH, json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readFile() {
        try {
            return Files.readString(PATH);
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private boolean cleanFile() {
        try {
            Files.deleteIfExists(PATH);
            Files.createFile(PATH);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
