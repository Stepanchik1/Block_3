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

    private Path path (byte classType) {return Path.of(dataFilePath, classType(classType)+dataName);}

private String classType (byte classType) {
    System.out.println(classType);
        if (classType == 1) {
        return "reciepts";} else if (classType == 0){return "ingridients";} else {return "";}
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

    public String readFile(byte classType) {
        try {
            return Files.readString(path(classType));
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private boolean cleanFile(byte classType) {
        try {
            Files.deleteIfExists(path(classType));
            Files.createFile(path(classType));
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
