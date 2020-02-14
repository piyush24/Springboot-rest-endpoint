package com.springbootRest.challenge.rest_api.controller;

import com.springbootRest.challenge.rest_api.helper.MessageHelperService;
import com.springbootRest.challenge.rest_api.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.springbootRest.challenge.rest_api.constants.ConfigConstant.*;
import static com.springbootRest.challenge.rest_api.constants.LoggerConstant.*;
import static com.springbootRest.challenge.rest_api.constants.MessageConstants.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(REST_API_URI_FILE)
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private MessageHelperService messageHelperService;
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * get the File
     *
     * @return get with file
     */
    @GetMapping(path = REST_API_URI_FILE_DOWNLOAD, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        logger.debug(LOGGER_SERVICE_STATEMENT_2007, fileName);
        try {
            Resource fileResource = fileService.getFileAsResource(fileName);
            if (fileResource.exists()) {
                InputStream inputStream = fileResource.getInputStream();
                byte[] byteArray = StreamUtils.copyToByteArray(inputStream);
                String contentType = getContentType(request, fileResource.getFilename());
                if (contentType == null) {
                    logger.debug(LOGGER_SERVICE_STATEMENT_2006);
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                }
                return status(HttpStatus.OK).contentType(MediaType.valueOf(contentType)).body(byteArray);
            }
            return status(HttpStatus.NOT_FOUND).body(messageHelperService.getMessage(FILE_NOT_FOUND, fileName));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }

    /**
     * upload the File
     *
     * @return responseEntity with fileName
     */
    @PostMapping(REST_API_URI_FILE_UPLOAD)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        logger.debug(LOGGER_SERVICE_STATEMENT_2008, file.toString());
        try {
            String fileName = fileService.storeFile(file);
            return status(HttpStatus.OK).body(messageHelperService.getMessage(FILE_UPLOAD_SUCCESSFUL, fileName));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }

    /**
     * upload the multiple files
     *
     * @return ResponseEntity with fileNames
     */
    @PostMapping(REST_API_URI_MULTIPLE_FILE_UPLOAD)
    public ResponseEntity uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        logger.debug(LOGGER_SERVICE_STATEMENT_2009, Arrays.toString(files));
        try {
            List<String> fileNames = Arrays.stream(files).map(file -> fileService.storeFile(file)).collect(Collectors.toList());
            return status(HttpStatus.OK).body(messageHelperService.getMessage(FILE_UPLOAD_SUCCESSFUL, fileNames));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }

    private String getContentType(HttpServletRequest request, String absolutePath) {
        if (request == null || request.getServletContext() == null)
            return null;
        return request.getServletContext().getMimeType(absolutePath);
    }
}
