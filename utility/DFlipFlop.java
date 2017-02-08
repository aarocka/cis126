package utility;

public class DFlipFlop  {
    private ReactiveLogicGate not  = new ReactiveLogicGate(ReactiveLogicGate.NOT);
    private ReactiveLogicGate and1 = new ReactiveLogicGate(ReactiveLogicGate.AND);
    private ReactiveLogicGate and2 = new ReactiveLogicGate(ReactiveLogicGate.AND);
    private ReactiveLogicGate nor1 = new ReactiveLogicGate(ReactiveLogicGate.NOR);
    private ReactiveLogicGate nor2 = new ReactiveLogicGate(ReactiveLogicGate.NOR);

    // ================ CONSTRUCTORS ================
    public DFlipFlop() {
        // putting together circuit

        // Full D FlipFlop
        this.not.solderOutputToInput1Of(this.and1);

        // SR FlipFlop
        this.and1.solderOutputToInput1Of(this.nor1);
        this.and2.solderOutputToInput2Of(this.nor2);

        // SR Latch
        this.nor1.solderOutputToInput1Of(this.nor2);
        this.nor2.solderOutputToInput2Of(this.nor1);

        // resetting the d-flipflop to ensure that the output initializes as false (LOW)
        this.setD(false); // reset value

        // mocking a clock pulse
        this.setClock(true);
        this.setClock(false);
    }

    // ================ END CONSTRUCTORS ================

    public void setD(Boolean input) {
        // sets the flipflop to tru if input is true,
        // resets the flipflop to false  if input is false
        this.not.setInput1(input);
        this.and2.setInput2(input);
    }

    public void setClock(Boolean input) {
        // sets the clock pin to HIGH if input is true,
        // sets the clock pin to LOW if input is false
        this.and1.setInput2(input);
        this.and2.setInput1(input);
    }

    public Boolean getQ() {
        // returns the current state (Q output)
        return this.nor1.getOutput();
    }

    public Boolean getNotQ() {
        // returns the opposite of the current state (Not Q output)
        return this.nor2.getOutput();
    }

    public void solderQToInput1Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the Q output of the current instance
        // and input 1 of gateForInput
        this.nor1.solderOutputToInput1Of(gateForInput);
    }

    public void solderQToInput2Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the Q output of the current instance
        // and input 2 of gateForInput
        this.nor1.solderOutputToInput2Of(gateForInput);
    }

    public void solderNotQToInput1Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the Not Q output of the current instance
        // and input 1 of gateForInput
        this.nor2.solderOutputToInput1Of(gateForInput);
    }

    public void solderNotQToInput2Of(ReactiveLogicGate gateForInput) {
        // makes a connection between the Not Q output of the current instance
        // and input 2 of gateForInput
        this.nor2.solderOutputToInput2Of(gateForInput);
    }
}
