package utility;

public class Register implements RegisterInterface {
    // implementation of a software version of a processor register

    // ================ CLASS PROPERTIES ================
    private static final int DEFAULT_NUMBER_OF_BITS = 8;
    // the index of the elements inside the memory array represent the bit position of the binary number being stored
    // this.memory[0] is the least significant bit
    // this.memory[this.memory.length - 1] is the most significant bit
    private DFlipFlop[] memory;
    // ================ END CLASS PROPERTIES ================


    // ================ CONSTRUCTORS ================
    public Register() {
        // providing a constructor to initialize the register with a default
        // ensures that the constructor is user friendly
        this.initializeRegister(DEFAULT_NUMBER_OF_BITS);
    };

    public Register(int numberOfBits) {
        // initializes the register with the specified number of bits
        this.initializeRegister(numberOfBits);
    };

    private void initializeRegister(int numberOfBits) {
        // initializes a register with the appropriate number of bits
        // method provided to avoid duplication of code in constructors
        this.memory = new DFlipFlop[numberOfBits];
        for (int i = 0; i < numberOfBits; i++) this.memory[i] = new DFlipFlop();
    }

    // ================ END CONSTRUCTORS ================

    public void setDatalines(String binaryData) {
        // see public void setDatalines(boolean[] binaryData)
        this.setDatalines(BinaryOperations.convertToArray(binaryData));
    }

    public void setDatalines(int binaryData) {
        // see public void setDatalines(boolean[] binaryData)
        this.setDatalines(BinaryOperations.convertToArray(binaryData, this.size()));
    }

    public void setDatalines(boolean[] binaryData) {
        // sets the full register with the binary data
        // the index in the boolean array represents the bit position
        // the least significant bit starts at position 0
        // and increases as the significance increases
        // binaryData[0] is the least significant bit
        // binaryData[binaryData.length - 1] is the most significant bit
        for (int i = 0; i < Math.min(binaryData.length, this.size()); i++) {
            this.memory[i].setD(binaryData[i]);
        }
        this.clockIt();
    }

    public String getDatalinesString() {
        // see public boolean[] getDatalines()
        return BinaryOperations.convertToString(this.getDatalines());
    }

    public int getDatalinesInteger() {
        // see public boolean[] getDatalines()
        return BinaryOperations.convertToInteger(this.getDatalines());
    }

    public boolean[] getDatalines() {
        // sets the full register with the binary data
        // the index in the boolean array represents the bit position
        // the least significant bit starts at position 0
        // and increases as the significance increases
        // getDatalines()[0] is the least significant bit
        // getDatalines()[getDatalines().length - 1] is the most significant bit
        boolean[] output = new boolean[this.size()];
        for (int i = 0; i < this.size(); i++) output[i] = this.getBit(i);
        return output;
    }

    public void clockIt() {
        // sends a HIGH pulse across the clock dataline
        // the clock cannot be accurately represented in a serial program
        // this method mocks the clock signal in a processor

        // setting all clock inputs to true (HIGH)
        for (DFlipFlop bit : this.memory) bit.setClock(true);

        // setting all clock inputs to false (LOW)
        for (DFlipFlop bit : this.memory) bit.setClock(false);
    }

    public int size() {
        // returns the number of bits that the register can store
        return this.memory.length;
    }

    public void clearBit(int bitPosition) {
        // clears the bit at the specified position
        // clearing the bit causes the bit to become false
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // setting input D of bit to false (LOW)
        this.memory[bitPosition].setD(false);

        // sending a clock pulse through the register
        // more efficient to only send a pulse to the indiviual bit; however,
        // sending a clock pulse to everything is more representative of the hardware
        this.clockIt();
    }

    public void setBit(int bitPosition) {
        // sets the bit at the specified position
        // setting the bit causes the bit to become true
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // setting input D of bit to true (HIGH)
        this.memory[bitPosition].setD(true);

        // sending a clock pulse through the register
        // more efficient to only send a pulse to the indiviual bit; however,
        // sending a clock pulse to everything is more representative of the hardware
        this.clockIt();
    }

    public boolean getBit(int bitPosition) {
        // returns the value of the bit at the specified position
        //
        // the least significant bit starts at position 0
        // and increases as the significance increases

        // output of d-flipflop is Q
        return this.memory[bitPosition].getQ();
    }
}
