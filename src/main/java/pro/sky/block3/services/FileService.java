package pro.sky.block3.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.block3.controllers.model.Reciept;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class FileService {
    @Value("${path.to.files.folder}")
    private String dataFilePath;

    @Value("${name.of.data.file}")
    private String dataName;

    private Path path(byte classType) {
        return Path.of(dataFilePath, classType(classType) + dataName);
    }

    private String classType(byte classType) {
        System.out.println(classType);
        if (classType == 1) {
            return "reciepts";
        } else if (classType == 0) {
            return "ingridients";
        } else {
            return "";
        }
    }

    public boolean saveToFile(String json, byte classType) {
        try {
            cleanFile(classType);
            Files.writeString(path(classType), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public File getNewFile(byte classType) {
        return new File(dataFilePath + "/" + classType(classType) + dataName);
    }

    public String readFile(byte classType) throws IOException {
        try {
            return Files.readString(path(classType));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean cleanFile(byte classType) {
        try {
            Files.deleteIfExists(path(classType));
            Files.createFile(path(classType));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Path createTempleFile(String suffix) throws IOException {
        return Files.createTempFile(Path.of("src/main/resources"), "temp", suffix);
    }

    private Path writeToFile(String text, byte classtype) throws IOException {
        String suffix = "some";
        if (classtype == 1) {
            suffix = "reciepts";
        }
        if (classtype == 0) {
            suffix = "ingridients";
        }
        Path path = createTempleFile(suffix);
        try (Writer writter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            writter.append(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public Path writeToFileReciepts(String text) throws IOException {
        return writeToFile(text, (byte) 1);
    }
    public Path writeToFileIngridients(String text) throws IOException {
        return writeToFile(text, (byte) 0);
    }
}


