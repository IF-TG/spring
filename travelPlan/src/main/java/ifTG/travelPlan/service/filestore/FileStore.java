package ifTG.travelPlan.service.filestore;

public interface FileStore {

    String saveFile(byte[] file, String uri, String type);

    void createThumbnailAndSaveFile(String postIdUri, String savedUri, String thumbnailUri);

    byte[] getHash(String path);
    byte[] getHash(byte[] file);

    void deleteFile(String s);

    public String saveFileToBase64Decode(String file, String uri, String type);

    String findFileToEncode(String path);

    String getExtension(String fileName);
}