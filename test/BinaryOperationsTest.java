package test;

import java.util.Arrays;
import greentealatte.src.GreenTeaLatte;
import utility.BinaryOperations;

class BinaryOperationsTest {
    private static final int BITS = 4;
    private static boolean[] testConvertToArray(String binaryData) {
        boolean[] output = new boolean[binaryData.length()];
        for (int i = 0; i < binaryData.length(); i++) {
            int charIndex = binaryData.length() - 1 - i;
            output[i] = binaryData.substring(charIndex, charIndex + 1).equals("1");
        }
        return output;
    }

    private static boolean[] testConvertToArray(int binaryData, int numberOfBits) {
        boolean[] output = new boolean[numberOfBits];
        for (int i = 0; i < numberOfBits; i++) {
            output[i]  = (binaryData & 1) == 1;
            binaryData = binaryData >> 1;
        }
        return output;
    }

    private static int testConvertToInteger(String binaryData) {
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

    private static int testConvertToInteger(boolean[] binaryData) {
        int output = 0;

        for (int i = 0; i < binaryData.length; i++) {
            if (binaryData[i]) output = output | (1 << i);
        }
        return output;
    }

    private static String testConvertToString(int binaryData, int numberOfBits) {
        String output = Integer.toBinaryString(binaryData);

        output = output.substring(Math.max(0, output.length() - numberOfBits)); // trims if numberOfBits < string length
        while (output.length() < numberOfBits) {
            output = "0" + output;
        }
        return output;
    }

    private static String testConvertToString(boolean[] binaryData) {
        String output = "";

        for (boolean bit : binaryData) output = (bit ? "1" : "0") + output;
        return output;
    }

    static void defineTests(GreenTeaLatte driver) {
        driver.describe("class BinaryOperations", () -> {
                driver.describe("convertToArray(String binaryData)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final String binaryData = testConvertToString(i, BITS);
                            boolean[] answer = testConvertToArray(binaryData);
                            String testStatement =
                                String.format("should convert \"%s\" to %s", binaryData, Arrays.toString(answer));
                            driver.it(testStatement, () -> {
                                    driver.assertTest(Arrays.equals(BinaryOperations.convertToArray(binaryData), answer));
                                });
                        }
                    });
                driver.describe("convertToArray(int binaryData, int numberOfBits)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final int binaryData = i;
                            boolean[] answer = testConvertToArray(binaryData, BITS);
                            String testStatement =
                                String.format("should convert 0b%s to %s", Integer.toBinaryString(binaryData),
                                Arrays.toString(answer));
                            driver.it(testStatement, () -> {
                                    driver.assertTest(Arrays.equals(BinaryOperations.convertToArray(binaryData,
                                                BITS), answer));
                                });
                        }
                    });
                driver.describe("convertToArray(int binaryData, int numberOfBits)", () -> {
                        driver.describe("should trim the results based on the number of bits", () -> {
                                for (int i = (1 << BITS); i < (0b100 << BITS); i += 0b101) {
                                    final int binaryData = i;
                                    boolean[] answer = testConvertToArray(binaryData, BITS);
                                    String testStatement =
                                        String.format("should convert (0b%s, 4) to %s",
                                        Integer.toBinaryString(binaryData),
                                        Arrays.toString(answer));
                                    driver.it(testStatement, () -> {
                                            driver.assertTest(Arrays.equals(BinaryOperations.convertToArray(binaryData,
                                                        BITS), answer));
                                        });
                                }
                            });
                    });
                driver.describe("convertToInteger(String binaryData)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final String binaryData = testConvertToString(i, BITS);
                            int answer = i;
                            String testStatement =
                                String.format("should convert \"%s\" to %d", binaryData, answer);
                            driver.it(testStatement, () -> {
                                    driver.assertTest(BinaryOperations.convertToInteger(binaryData) == answer);
                                });
                        }
                    });
                driver.describe("convertToInteger(boolean[] binaryData)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final boolean[] binaryData = testConvertToArray(i, BITS);
                            int answer = i;
                            String testStatement =
                                String.format("should convert %s to %d", Arrays.toString(binaryData), answer);
                            driver.it(testStatement, () -> {
                                    driver.assertTest(BinaryOperations.convertToInteger(binaryData) == answer);
                                });
                        }
                    });
                driver.describe("convertToString(int binaryData, int numberOfBits)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final int binaryData = i;
                            String answer = testConvertToString(binaryData, BITS);
                            String testStatement =
                                String.format("should convert 0b%s to \"%s\"", Integer.toBinaryString(binaryData),
                                answer);
                            driver.it(testStatement, () -> {
                                    driver.assertTest(BinaryOperations.convertToString(binaryData, BITS).equals(answer));
                                });
                        }
                    });
                driver.describe("convertToString(int binaryData, int numberOfBits)", () -> {
                        driver.describe("should trim the results based on the number of bits", () -> {
                                for (int i = (1 << BITS); i < (0b100 << BITS); i += 0b101) {
                                    final int binaryData = i;
                                    String answer = testConvertToString(binaryData, BITS);
                                    String testStatement =
                                        String.format("should convert (0b%s, 4) to \"%s\"",
                                        Integer.toBinaryString(binaryData),
                                        answer);
                                    driver.it(testStatement, () -> {
                                            driver.assertTest(BinaryOperations.convertToString(binaryData,
                                                    BITS).equals(answer));
                                        });
                                }
                            });
                    });
                driver.describe("convertToString(boolean[] binaryData)", () -> {
                        for (int i = 0; i < (1 << BITS); i++) {
                            final boolean[] binaryData = testConvertToArray(i, BITS);
                            String answer = testConvertToString(binaryData);
                            String testStatement =
                                String.format("should convert %s to \"%s\"", Arrays.toString(binaryData), answer);
                            driver.it(testStatement, () -> {
                                    driver.assertTest(BinaryOperations.convertToString(binaryData).equals(answer));
                                });
                        }
                    });
            });
    } /* defineTests */
}
