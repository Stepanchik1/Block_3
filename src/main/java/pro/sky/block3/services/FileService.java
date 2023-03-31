package pro.sky.block3.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@NoArgsConstructor
public class FileService {
    @Value("${path.to.files.folder}")
    private String dataFilePath;

    @Value("${name.of.data.file}")
    private String dataName;

    @PostConstruct
    private Path path () {return Path.of(dataFilePath, classType(classType)+dataName);}

    byte classType=1;

private String classType (byte classType) {
    System.out.println(classType);
        if (classType == 1) {
        return "reciepts";} else if (classType == 0){return "ingridients";} else {return "";}
}

    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(path(), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readFile() {
        try {
            return Files.readString(path());
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private boolean cleanFile() {
        try {
            Files.deleteIfExists(path());
            Files.createFile(path());
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
