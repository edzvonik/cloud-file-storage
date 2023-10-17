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

    byte[] readObjectAsBytes(String objectPath);

    void writeBytesToObject(String objectPath, byte[] data);

    void copyObject(String sourcePath, String destinationPath);

    void moveObject(String sourcePath, String destinationPath);

    void deleteObject(String objectPath);

    boolean objectExists(String objectPath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    long getObjectSize(String objectPath);

    void createObject(String objectPath);

    List<String> listObjects(String objectsPath);

    void downloadObject(String sourceURL, String destinationPath);

    void uploadObject(String sourcePath, String destinationURL);

}
