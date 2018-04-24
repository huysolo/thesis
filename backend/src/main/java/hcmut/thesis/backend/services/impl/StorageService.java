package hcmut.thesis.backend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path taskLocation = Paths.get("upload", "task", "1");

    public void storeTask(MultipartFile file, Integer taskId) {
        try {
            String fileName = "upload/task/" + taskId;
            Path path = Paths.get("upload","task", taskId.toString());
            if (!Files.exists(path)) {

                Files.createDirectories(path);

                System.out.println("Directory created");
            } else {

                System.out.println("Directory already exists");
            }

            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = taskLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(taskLocation.toFile());
    }

    public void init() {
//        try {
//            if (!Files.isDirectory(taskLocation)){
//                System.out.println("Du me may");
//                Files.createDirectories(taskLocation);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
    }
}