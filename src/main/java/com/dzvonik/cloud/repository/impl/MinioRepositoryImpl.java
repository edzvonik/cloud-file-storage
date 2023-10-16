package com.dzvonik.cloud.repository.impl;

import com.dzvonik.cloud.repository.MinioRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MinioRepositoryImpl implements MinioRepository {

    private final String ROOT_BUCKET = "files";
    private final MinioClient minioClient;

    @Override
    public byte[] readFileAsBytes(String filePath) {
        return new byte[0];
    }

    @Override
    public void writeBytesToFile(String filePath, byte[] data) {

    }

    @Override
    public void copyFile(String sourcePath, String destinationPath) {

    }

    @Override
    public void moveFile(String sourcePath, String destinationPath) {

    }

    @Override
    public void deleteFile(String filePath) {

    }

    @Override
    public boolean fileExists(String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        StatObjectResponse response = minioClient.statObject(
                StatObjectArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .object("/" + filePath)
                        .build()
        );

        return response != null;
    }

    @Override
    public long getFileSize(String filePath) {
        return 0;
    }

    @Override
    public void createDirectory(String directoryPath) {
//        String objectName = String.join("/", directoryPath, "/");
//
//        if ()
//        minioClient.putObject(
//                PutObjectArgs.builder()
//                        .bucket(ROOT_BUCKET)
//                        .object(objectName)
//                        .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
//                        .build()
//        );
    }

    @Override
    public void deleteDirectory(String directoryPath) {

    }

    @Override
    public List<String> listFilesInDirectory(String directoryPath) {
        return null;
    }

    @Override
    public void moveDirectory(String sourcePath, String destinationPath) {

    }

    @Override
    public boolean directoryExists(String directoryPath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        StatObjectResponse response = minioClient.statObject(
                StatObjectArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .object("/" + directoryPath + "/")
                        .build()
        );

        return response != null;
    }

    @Override
    public long getDirectorySize(String directoryPath) {
        return 0;
    }

    @Override
    public void downloadFile(String sourceURL, String destinationPath) {

    }

    @Override
    public void uploadFile(String sourcePath, String destinationURL) {

    }

}
