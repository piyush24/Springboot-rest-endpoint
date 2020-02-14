package com.springbootRest.challenge.rest_api.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface FileService {
    String storeFile(MultipartFile file);

    Resource getFileAsResource(String fileName) throws MalformedURLException;
}
