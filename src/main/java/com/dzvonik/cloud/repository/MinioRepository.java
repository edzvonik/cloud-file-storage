package com.dzvonik.cloud.repository;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MinioRepository {

    byte[] readFileAsBytes(String filePath);

    void writeBytesToFile(String filePath, byte[] data);

    void copyFile(String sourcePath, String destinationPath);

    void moveFile(String sourcePath, String destinationPath);

    void deleteFile(String filePath);

    boolean fileExists(String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    long getFileSize(String filePath);

    void createDirectory(String directoryPath);

    void deleteDirectory(String directoryPath);

    List<String> listFilesInDirectory(String directoryPath);

    void moveDirectory(String sourcePath, String destinationPath);

    boolean directoryExists(String directoryPath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    long getDirectorySize(String directoryPath);

    void downloadFile(String sourceURL, String destinationPath);

    void uploadFile(String sourcePath, String destinationURL);

}
