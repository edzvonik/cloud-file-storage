package com.dzvonik.cloud.integration;

import com.dzvonik.cloud.repository.MinioRepository;
import com.dzvonik.cloud.repository.impl.MinioRepositoryImpl;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.ErrorResponse;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class MinioIntegrationTest {

    private static MinioRepository minioRepository;
    private static MinioClient minioClient;

    private static final int minioPort = 9000;
    private static final String ADMIN_ACCESS_KEY = "minioadmin";
    private static final String ADMIN_SECRET_KEY = "minioadmin";
    private static final String ROOT_BUCKET = "files";

    @Container
    public static GenericContainer<?> minioServer = new GenericContainer<>("minio/minio")
            .withEnv("MINIO_ACCESS_KEY", ADMIN_ACCESS_KEY)
            .withEnv("MINIO_SECRET_KEY", ADMIN_SECRET_KEY)
            .withCommand("server /data")
            .withExposedPorts(minioPort)
            .waitingFor(new HttpWaitStrategy()
                    .forPath("/minio/health/ready")
                    .forPort(minioPort)
                    .withStartupTimeout(Duration.ofSeconds(10)));


    @BeforeAll
    static void setUp() throws Exception {
        minioClient = MinioClient.builder()
                .endpoint("http://" + minioServer.getHost() + ":" + minioServer.getMappedPort(minioPort))
                .credentials(ADMIN_ACCESS_KEY, ADMIN_SECRET_KEY)
                .build();

        minioClient.makeBucket(
                MakeBucketArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .build()
        );

        minioRepository = new MinioRepositoryImpl(minioClient);
    }

    @AfterAll
    public static void tearDown() {
        minioServer.stop();
    }

    @Test
    public void checkMinioIsRunning() throws Exception {
        assertTrue(minioServer.isRunning());
        boolean isExist = minioClient.bucketExists(
                BucketExistsArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .build()
        );

        assertTrue(isExist);
    }

    @Test
    void testFileExistsWhenObjectExists() throws Exception {
        String objectName = "test-object";
        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(ROOT_BUCKET)
                        .object(objectName)
                        .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build()
        );

        boolean exists = minioRepository.fileExists(objectName);

        assertTrue(exists);
    }

    @Test
    void testFileExistsWhenObjectDoesNotExists() throws Exception {
        String filePath = "non-existing-file.txt";

        ErrorResponseException exception = assertThrows(ErrorResponseException.class, () -> {
            minioRepository.fileExists(filePath);
        });

        assertEquals("Object does not exist", exception.errorResponse().message());
        assertEquals("NoSuchKey", exception.errorResponse().code());
    }

//    @Test
//    public void whenUserCreatedThenCreateUserFolder() throws Exception {
//        minioRepository.("user123");
//
//        String objectName = String.join("/", "user123", "/");
//        StatObjectResponse response = minioClient.statObject(
//                StatObjectArgs.builder()
//                        .bucket(ROOT_BUCKET)
//                        .object(objectName)
//                        .build()
//        );
//
//        assertTrue(response != null);
//    }
//
//    @Test
//    public void whenGetAllObjectsThenReturnList() {
//        minioService.createUserFolder("user124");
//        minioService.createObject("user124", "documents");
//        minioService.createObject("user124", "images");
//
//        String documents = String.join("/", "user123", "/");
//        StatObjectResponse response = minioClient.statObject(
//                StatObjectArgs.builder()
//                        .bucket(ROOT_BUCKET)
//                        .object(objectName)
//                        .build()
//        );
//        StatObjectResponse response = minioClient.statObject(
//                StatObjectArgs.builder()
//                        .bucket(ROOT_BUCKET)
//                        .object(objectName)
//                        .build()
//        );
//    }

}
