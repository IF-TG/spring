package ifTG.travelPlan.service.filestore;


import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

@Service
@Slf4j
public class FileStoreImpl implements FileStore {
    @Value("${path.absolute}")
    private String absoluteFilePath;

    @Override
    public String saveFileToBase64Decode(String file, String uri, String type) {
        return saveFile(Base64.decodeBase64(file), uri, type);
    }

    @Override
    public String findFileToEncode(String path) {
        Path filePath = Paths.get(absoluteFilePath + path);
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.encodeBase64String(fileBytes);
    }

    @Override
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }



    @Override
    public String saveFile(byte[] file, String uri, String type) {
        String path = absoluteFilePath + uri;
        try {
            createFolder(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writeImageFile(file, path, type);
    }

    @Override
    public void createThumbnailAndSaveFile(String savedFileUri, String fileName, String thumbnailUri, int length) {
        log.info("thumbnail = {}", savedFileUri+fileName);
        File savedImgFile = new File(absoluteFilePath + savedFileUri + fileName);
        File thumbNailImgFile = new File(absoluteFilePath+savedFileUri+thumbnailUri + fileName);
        try {
            createParentFolder(thumbNailImgFile);
            Thumbnailator.createThumbnail(savedImgFile, thumbNailImgFile, length, length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getHash(String path) {
        try{
            byte[] file = readFileToByteArray(path);
            return getSHA256(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getHash(byte[] file) {
        return getSHA256(file);
    }

    @Override
    public void deleteFile(String uri) {
        File deleteFile = new File(absoluteFilePath+uri);
        if(deleteFile.delete()){
            log.info("{} file delete", deleteFile.toPath());
        }else{
            log.error("{} not found", deleteFile.toPath());
        }
    }



    private static byte[] getSHA256(byte[] file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(file);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    private byte[] readFileToByteArray(String path) throws IOException {
        Path filePath = Paths.get(absoluteFilePath + path);
        return Files.readAllBytes(filePath);
    }


    private String writeImageFile(byte[] file, String path, String type) {
        String fileName = getFileName(type);
        try(FileOutputStream fileOutputStream = new FileOutputStream(path + fileName)){
            fileOutputStream.write(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    private static void createFolder(String path) throws IOException {
        File folder = new File(path);
        if(!folder.exists()){
            if(folder.mkdirs()){
                log.info("path = {} , create folder", path);
            }
            else{
                throw new IOException("create folder fail > " + path);
            }
        }
    }

    private static void createParentFolder(File file) throws IOException {
        if(!file.getParentFile().exists()){
            if(file.getParentFile().mkdirs()){
                log.info("path = {} , create folder", file.getParentFile().getPath());
            }
            else{
                throw new IOException("create folder fail");
            }
        }
    }


    private String getFileName(String type){
        return UUID.randomUUID() + "." + type;
    }
}
