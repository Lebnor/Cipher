package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        String operation = "enc"; // default operation
        int key = 0; // default key
        String data = "";
        String inputFileName = null;
        String outputFileName = null;
        EncodingStrategy cipherStrategy = new ShiftEncodingStrategy();

        boolean recievedData = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-mode":
                    String modeValue = args[++i];
                    if (modeValue.equals("enc")) {

                    } else if (modeValue.equals("dec")) {
                        operation = "dec";
                    } else {
                        System.out.println("Unknown operation");
                    }
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    break;
                case "-data":
                    recievedData = true;
                    data = args[++i];
                    break;
                case "-in":
                    if (!recievedData) {
                        inputFileName = args[++i];
                    }
                    break;
                case "-out":
                    outputFileName = args[++i];
                    break;
                case "-alg" :
                    String alg = args[++i];
                    if (alg.equals("shift")) {
                        cipherStrategy = new ShiftEncodingStrategy();
                    } else if (alg.equals("unicode")) {
                        cipherStrategy = new UnicodeEncodingStrategy();
                    }
            }
        }
        if (inputFileName != null) {
            File file = new File(inputFileName);
            try {
                Scanner scanner = new Scanner(file);
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }
                data = builder.toString();
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String result = "";
        result = cipherStrategy.translateLine(operation, data, key);

        if (outputFileName != null) {
            try {
                Writer writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
                writer.write(result);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(result);
        }

    }



}
