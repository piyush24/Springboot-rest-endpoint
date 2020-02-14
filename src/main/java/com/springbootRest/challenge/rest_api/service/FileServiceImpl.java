package com.springbootRest.challenge.rest_api.service;

import com.springbootRest.challenge.rest_api.exceptions.FileCreationFailedException;
import com.springbootRest.challenge.rest_api.exceptions.InvalidUrlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.springbootRest.challenge.rest_api.constants.LoggerConstant.*;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private Path fileStorageLocation = null;
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @PostConstruct
    public void init() {
        logger.debug(LOGGER_SERVICE_STATEMENT_2001, uploadDir);
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            logger.debug(LOGGER_SERVICE_STATEMENT_2002, e.getMessage());
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        logger.debug(LOGGER_SERVICE_STATEMENT_2003, fileName);
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            logger.debug(LOGGER_SERVICE_STATEMENT_2004);
            return fileName;
        } catch (IOException e) {
            logger.debug(LOGGER_SERVICE_STATEMENT_2005, e.getMessage());
            throw new FileCreationFailedException();
        }
    }

    @Override
    public Resource getFileAsResource(String fileName) {
        logger.debug(LOGGER_SERVICE_STATEMENT_2010, fileName);
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        try {
            logger.debug(LOGGER_SERVICE_STATEMENT_2011, filePath.toString());
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            logger.debug(LOGGER_SERVICE_STATEMENT_2012, e.getMessage());
            throw new InvalidUrlException();
        }
    }
}
