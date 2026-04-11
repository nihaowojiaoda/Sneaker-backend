package com.imdat.service;

import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {

    public byte[] readImage(String pathStr) throws Exception {
        Path path = Paths.get(pathStr);
        return Files.readAllBytes(path);
    }
}