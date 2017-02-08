package test;

import greentealatte.src.GreenTeaLatte;
import utility.BinaryOperations;

class BinaryOperationsTest {
    static void defineTests(GreenTeaLatte driver) {
        driver.describe("class BinaryOperations", () -> {
                driver.describe("convertToArray & convertToString", () -> {
                        driver.it("should convert a string to an array and back to the same string", () -> {
                                int bits = 4;
                                for (int i = 0; i < (1 << bits); i++) {
                                    String binary = Integer.toBinaryString(i);
                                    while (binary.length() < bits) {
                                        binary = "0" + binary;
                                    }
                                    String converted =
                                        BinaryOperations.convertToString(BinaryOperations.convertToArray(binary));
                                    driver.assertTest(converted.equals(binary));
                                }
                            });
                    });
                driver.describe("convertToInteger & convertToString", () -> {
                        driver.it("should convert a string to an integer and back to the same string", () -> {
                                int bits = 4;
                                for (int i = 0; i < (1 << bits); i++) {
                                    String binary = Integer.toBinaryString(i);
                                    while (binary.length() < bits) {
                                        binary = "0" + binary;
                                    }
                                    String converted =
                                        BinaryOperations.convertToString(BinaryOperations.convertToInteger(binary),
                                        bits);
                                    driver.assertTest(converted.equals(binary));
                                }
                            });
                    });
                driver.describe("convertToInteger & convertToArray", () -> {
                        driver.it("should convert an integer to an array and back to the same integer", () -> {
                                int bits = 4;
                                for (int i = 0; i < (1 << bits); i++) {
                                    int converted =
                                        BinaryOperations.convertToInteger(BinaryOperations.convertToArray(i, bits));
                                    driver.assertTest(converted == i);
                                }
                            });
                    });
                driver.describe("converting integers to arrays and strings", () -> {
                        int n = 0b10100101;
                        int bits = 4;
                        int answer = n % (1 << bits);
                        driver.it(
                            "convertToArray should trim the results if the number of bits is less the amount of bits in the integer",
                            () -> {
                                driver.assertTest(answer ==
                                    BinaryOperations.convertToInteger(BinaryOperations.convertToArray(n, bits)));
                            });
                        driver.it(
                            "convertToString should trim the results if the number of bits is less the amount of bits in the integer",
                            () -> {
                                driver.assertTest(answer ==
                                    BinaryOperations.convertToInteger(BinaryOperations.convertToString(n, bits)));
                            });
                    });
            });
    } /* defineTests */
}
