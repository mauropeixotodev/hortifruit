package com.fpsoluctionstechs.hortfruitonline.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FilesService {

    @Value("${diretorio.upload.files}")
    private String diretorioUploadFile;

    private Path root;

    public void init() {
        try {
            root = Paths.get(diretorioUploadFile);
            if(!Files.exists(root)) Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível iniciar a pasta de uploads: "+diretorioUploadFile);
        }
    }
    public String save(MultipartFile file) {
        try {
            String nomeFile = new Date().getTime() + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(nomeFile));
            return nomeFile;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar o arquivo. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Não foi possível ler o arquivo!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível ler os arquivos!");
        }
    }
}
