package com.hnt.dental.service;

import com.hnt.dental.exception.SystemNotFoundException;
import com.hnt.dental.exception.SystemRuntimeException;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Path root = Paths.get("Storage");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new SystemNotFoundException("Could not initialize folder for upload!");
        }
    }


    public void save(MultipartFile file, String filename) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(filename));
        } catch (IOException e) {
            throw new SystemRuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new SystemNotFoundException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new SystemNotFoundException(e.getMessage());
        }
    }


    public boolean delete(String filename) {
        try {
            Path file = root.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new SystemNotFoundException("Error: " + e.getMessage());
        }
    }


    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new SystemNotFoundException(e.getMessage());
        }
    }
}