package genetic;

import java.util.List;

public class Node {
   private Character operation;
   private int numper;

    public Character getOperation() {
        return operation;
    }

    public void setOperation(Character operation) {
        this.operation = operation;
    }

    public int getNumper() {
        return numper;
    }

    public void setNumper(int numper) {
        this.numper = numper;
    }

    public Node(Character operation, int numper) {
        this.operation = operation;
        this.numper = numper;
    }

    @Override
    public String toString() {
        return String.format("[%c , %2d]",operation,numper);
    }

}
