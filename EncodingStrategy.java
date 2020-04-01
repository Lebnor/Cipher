package encryptdecrypt;

public interface EncodingStrategy {

//    String encrypt(String text, int shift);

//    String decrypt(String text, int shift);

    String translateLine(String operation, String text, int shift);
}
