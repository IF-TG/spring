package ifTG.travelPlan.service.filestore;

public interface FileStore {

    String saveFile(byte[] file, String uri, String type);

    void createThumbnailAndSaveFile(String savedFileUri, String fileName, String thumbnailUri, int length);

    byte[] getHash(String path);
    byte[] getHash(byte[] file);

    void deleteFile(String uri);

    String saveFileToBase64Decode(String file, String uri, String type);

    String findFileToEncode(String path);

    String getExtension(String fileName);
}
