package pro.sky.block3kursovaya.services;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@NoArgsConstructor
public class FileService {
@Value("${path.to.files}")
private String filepath;
@Value("${nameOfFiles}")
private String filename;

}

