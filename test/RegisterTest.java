package test;

import greentealatte.src.GreenTeaLatte;
import utility.BinaryOperations;
import utility.DFlipFlop;
import utility.Register;

class RegisterTest {
    static void defineTests(GreenTeaLatte driver) {
        driver.describe("class Register", () -> {
                driver.describe("constructor", () -> {
                        driver.it("should initialize a register with all zeros", () -> {
                                Register register = new Register();
                                driver.assertTest(register.getDatalinesInteger() == 0);
                            });
                        driver.it("should initialize a register to 8 bits if not declared", () -> {
                                Register register = new Register();
                                driver.assertTest(register.size() == 8);
                            });
                        driver.it("should initialize a register to X bits if declared", () -> {
                                Register register = new Register(4);
                                driver.assertTest(register.size() == 4);
                                register = new Register(16);
                                driver.assertTest(register.size() == 16);
                                register = new Register(32);
                                driver.assertTest(register.size() == 32);
                                register = new Register(64);
                                driver.assertTest(register.size() == 64);
                            });
                    });
                driver.describe("setDatalines and getDatalines", () -> {
                        driver.it("should set the value in the register and return the same value", () -> {
                                int bits = 4;
                                Register register = new Register(bits);
                                for (int i = 0; i < (1 << bits); i++) {
                                    register.setDatalines(BinaryOperations.convertToArray(i, bits));
                                    driver.assertTest(BinaryOperations.convertToInteger(register.getDatalines()) == i);
                                }
                            });
                    });
                driver.describe("clearBit", () -> {
                        driver.it("should keep the bit 0 if the bit was 0", () -> {
                                Register register = new Register(1);
                                register.setDatalines(0);
                                register.clearBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 0);
                            });
                        driver.it("should clear the bit to 0 if the bit was 1", () -> {
                                Register register = new Register(1);
                                register.setDatalines(1);
                                register.clearBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 0);
                            });
                        driver.it("should clear the bit with the specified position", () -> {
                                Register register = new Register(2);
                                register.setDatalines(0b11);
                                register.clearBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 0b10);
                                register.clearBit(1);
                                driver.assertTest(register.getDatalinesInteger() == 0b00);
                            });
                    });
                driver.describe("setBit", () -> {
                        driver.it("should keep the bit 1 if the bit was 1", () -> {
                                Register register = new Register(1);
                                register.setDatalines(1);
                                register.setBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 1);
                            });
                        driver.it("should set the bit to 1 if the bit was 0", () -> {
                                Register register = new Register(1);
                                register.setDatalines(0);
                                register.setBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 1);
                            });
                        driver.it("should set the bit with the specified position", () -> {
                                Register register = new Register(2);
                                register.setDatalines(0b0);
                                register.setBit(1);
                                driver.assertTest(register.getDatalinesInteger() == 0b10);
                                register.setBit(0);
                                driver.assertTest(register.getDatalinesInteger() == 0b11);
                            });
                    });
                driver.describe("getBit", () -> {
                        driver.it("should return the bit at the specified position", () -> {
                                int number = 0b0110110000111011;
                                Register register = new Register(16);
                                register.setDatalines(number);
                                for (int i = 0; i < 16; i++) {
                                    driver.assertTest(register.getBit(i) == (((number >> i) & 1) == 1));
                                }
                            });
                    });
            });
    } /* defineTests */
}
