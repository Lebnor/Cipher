package encryptdecrypt;


public class ShiftEncodingStrategy implements EncodingStrategy {
    //todo optimize code in ShiftEncodingStrategy

    
    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final int LOWER_CASE_COUNT = LOWER_CASE_LETTERS.length();
    private static final int UPPER_CASE_COUNT = UPPER_CASE_LETTERS.length();


    private String encryptLine(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(encryptChar(text.charAt(i), shift));
        }
        return builder.toString();
    }

    private String decryptLine(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(decryptChar(text.charAt(i), shift));
        }
        return builder.toString();
    }

    private char encryptChar(char theChar, int shift) {
        if (!LOWER_CASE_LETTERS.contains(theChar + "") && !UPPER_CASE_LETTERS.contains(theChar + "")) {
            return theChar;
        }
        if (UPPER_CASE_LETTERS.contains(theChar + "")) {
            return UPPER_CASE_LETTERS.charAt(indexOfEncryptedChar(theChar, shift, true));
        } else if (LOWER_CASE_LETTERS.contains(theChar + "")){
            return LOWER_CASE_LETTERS.charAt(indexOfEncryptedChar(theChar, shift, false));
        }
        else return theChar;
    }
    private int indexOfEncryptedChar(char theChar, int shift, boolean upperCase) {
        if (upperCase) {
            int index = UPPER_CASE_LETTERS.indexOf(theChar) + shift;
            if (index < 0 ) {
                index = UPPER_CASE_COUNT + index;
            } else {
                index = index % UPPER_CASE_COUNT;
            }
            return index;
        } else {
            int index = LOWER_CASE_LETTERS.indexOf(theChar) + shift;
            if (index < 0) {
                index = LOWER_CASE_COUNT + index;
            } else {
                index = index % LOWER_CASE_COUNT;
            }
            return index;
         }


    }
    private char decryptChar(char theChar, int shift) {
        return encryptChar(theChar, -shift);
    }

    @Override
    public String translateLine(String operation, String text, int shift) {
        if (operation.equals("enc")) {
            return this.encrypt(text, shift);
        } else if (operation.equals("dec")) {
            return this.decrypt(text, shift);
        }
        return null;
    }

    public String encrypt(String text, int shift) {
        return encryptLine(text, shift);
    }

    public String decrypt(String text, int shift) {
        return decryptLine(text, shift);
    }

}
