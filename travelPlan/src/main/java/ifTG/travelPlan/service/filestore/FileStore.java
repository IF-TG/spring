package ifTG.travelPlan.service.filestore;

import java.util.List;

public interface FileStore {

    boolean isExisted(String uri);

    String saveFile(byte[] file, String uri, String type);

    void saveTextFile(String file, String fileUriWithFileName);

    void createThumbnailAndSaveFile(String savedFileUri, String fileName, String thumbnailUri, int length);

    byte[] getHash(String path);
    byte[] getHash(byte[] file);

    void deleteFile(String uri);

    String saveFileToBase64Decode(String file, String uri, String type);

    String findFileToEncode(String path);

    String findFile(String path);

    String getExtension(String fileName);
}
