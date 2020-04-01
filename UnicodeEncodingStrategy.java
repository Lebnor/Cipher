package encryptdecrypt;


public class UnicodeEncodingStrategy implements EncodingStrategy {



    @Override
    public String translateLine(String operation, String text, int shift) {
        if (operation.equals("enc")) {
            return this.encrypt(text, shift);
        } else if (operation.equals("dec")) {
            return this.decrypt(text, shift);
        }
        else {
            return null;
        }
    }

    private String encrypt(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            builder.append( (char) (c + shift));
        }
        return builder.toString();
    }

    private String decrypt(String text, int shift) {
        return this.encrypt(text, -shift);
    }

}
