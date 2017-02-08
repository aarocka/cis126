package test;

import greentealatte.src.GreenTeaLatte;
import utility.DFlipFlop;

class DFlipFlopTest {
    static void defineTests(GreenTeaLatte driver) {
        driver.describe("class DFlipFlop", () -> {
                driver.describe("constructor", () -> {
                        driver.it("should initialize a flip flop without errors", () -> {
                                DFlipFlop flipflop = new DFlipFlop();
                                driver.assertTest(flipflop != null);
                            });
                        driver.it("should initialize a flip flop with Q as false (LOW)", () -> {
                                DFlipFlop flipflop = new DFlipFlop();
                                driver.assertTest(flipflop.getQ() == false);
                            });
                        driver.it("should initialize a flip flop with notQ as true (HIGH)", () -> {
                                DFlipFlop flipflop = new DFlipFlop();
                                driver.assertTest(flipflop.getNotQ() == true);
                            });
                    });
                driver.it("should reset Q if D is false (LOW) on a clock pulse", () -> {
                        DFlipFlop flipflop = new DFlipFlop();
                        flipflop.setD(true);
                        flipflop.setClock(true);
                        flipflop.setClock(false);
                        flipflop.setD(false);
                        flipflop.setClock(true);
                        flipflop.setClock(false);
                        driver.assertTest(flipflop.getQ() == false);
                        driver.assertTest(flipflop.getNotQ() == true);
                    });
                driver.it("should set Q if D is true (HIGH) on a clock pulse", () -> {
                        DFlipFlop flipflop = new DFlipFlop();
                        flipflop.setD(false);
                        flipflop.setClock(true);
                        flipflop.setClock(false);
                        flipflop.setD(true);
                        flipflop.setClock(true);
                        flipflop.setClock(false);
                        driver.assertTest(flipflop.getQ() == true);
                        driver.assertTest(flipflop.getNotQ() == false);
                    });
            });
    } /* defineTests */
}
