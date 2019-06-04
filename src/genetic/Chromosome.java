package genetic;

import java.util.List;

public class Chromosome {
    private Node[] exp;
    private Double cost;

    public Chromosome(Node[] exp, double cost) {
        this.exp = exp;
        this.cost = cost;
    }

    public Node[] getExp() {
        return exp;
    }

    public void setExp(Node[] exp) {
        this.exp = exp;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
