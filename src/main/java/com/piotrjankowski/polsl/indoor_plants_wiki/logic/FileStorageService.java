package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch(Exception ex){
            throw new FileStorageException("Could not create the directory to upload");
        }
    }

    public String storeFile(MultipartFile file, String namePrefix){
        String fileName = StringUtils.cleanPath(namePrefix+file.getOriginalFilename());
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch(IOException ex){
            throw new FileStorageException("Could not store file "+fileName+". Please tryagain!", ex);
        }
    }

    public Resource loadFilesResource(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }else{
                throw new MyFileNotFoundException("File not found" + fileName);
            }
        }catch (MalformedURLException ex){
            throw new MyFileNotFoundException("File not found" + fileName);
        }
    }
}
