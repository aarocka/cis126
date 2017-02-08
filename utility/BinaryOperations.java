package utility;

public class BinaryOperations {
    // boolean arrays store the binary data with the index representing the bit position
    // the least significant bit starts at position 0
    // and increases as the significance increases
    // binaryData[0] is the least significant bit
    // binaryData[binaryData.length - 1] is the most significant bit

    public static boolean[] convertToArray(String binaryData) {
        boolean[] output = new boolean[binaryData.length()];
        for (int i = 0; i < binaryData.length(); i++) {
            int charIndex = binaryData.length() - 1 - i;
            output[i] = binaryData.substring(charIndex, charIndex + 1).equals("1");
        }
        return output;
    }

    public static boolean[] convertToArray(int binaryData, int numberOfBits) {
        boolean[] output = new boolean[numberOfBits];
        for (int i = 0; i < numberOfBits; i++) {
            output[i]  = (binaryData & 1) == 1;
            binaryData = binaryData >> 1;
        }
        return output;
    }

    public static int convertToInteger(String binaryData) {
        int output = 0;

        while (binaryData.length() > 0) {
            output = output << 1;
            if (binaryData.substring(0, 1).equals("1")) {
                output = output | 1;
            }
            binaryData = binaryData.substring(1);
        }
        return output;
    }

    public static int convertToInteger(boolean[] binaryData) {
        int output = 0;

        for (int i = 0; i < binaryData.length; i++) {
            if (binaryData[i]) output = output | (1 << i);
        }
        return output;
    }

    public static String convertToString(int binaryData, int numberOfBits) {
        String output = Integer.toBinaryString(binaryData);

        output = output.substring(Math.max(0, output.length() - numberOfBits)); // trims if numberOfBits < string length
        while (output.length() < numberOfBits) {
            output = "0" + output;
        }
        return output;
    }

    public static String convertToString(boolean[] binaryData) {
        String output = "";

        for (boolean bit : binaryData) output = (bit ? "1" : "0") + output;
        return output;
    }
}
