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
    public boolean objectExists(String objectPath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        StatObjectResponse response = minioClient.statObject(
                StatObjectArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .object("/" + objectPath)
                        .build()
        );

        return response != null;
    }

    @Override
    public byte[] readObjectAsBytes(String objectPath) {
        return new byte[0];
    }

    @Override
    public void writeBytesToObject(String objectPath, byte[] data) {

    }

    @Override
    public void copyObject(String sourcePath, String destinationPath) {

    }

    @Override
    public void moveObject(String sourcePath, String destinationPath) {

    }

    @Override
    public void deleteObject(String objectPath) {

    }

    @Override
    public long getObjectSize(String objectPath) {
        return 0;
    }

    @Override
    public void createObject(String objectPath) {

    }

    @Override
    public List<String> listObjects(String objectsPath) {
        return null;
    }

    @Override
    public void downloadObject(String sourceURL, String destinationPath) {

    }

    @Override
    public void uploadObject(String sourcePath, String destinationURL) {

    }

}
