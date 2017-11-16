import java.util.Objects;

/**
 * Name: olivergreenwald
 * Lab Name: HTNode
 * Lab purpose: The goal of HTNode is to create a program that
 * Date: 11/7/17
 * Collaborators: Dan Taro
 */

public class HTNode<Freq extends Comparable<Freq>, Value> implements Comparable{

    private Freq freq;
    private Value value;
    private HTNode<Freq, Value> left;
    private HTNode<Freq, Value> right;
    private int size;

    public HTNode(Freq freq, Value value, int size) {
        this.freq = freq;
        this.value = value;
        this.size = size;
    }

    public Freq getFreq() {
        return freq;
    }

    public void setFreq(Freq freq) {
        this.freq = freq;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public HTNode<Freq, Value> getLeft() {
        return left;
    }

    public void setLeft(HTNode<Freq, Value> left) {
        this.left = left;
    }

    public HTNode<Freq, Value> getRight() {
        return right;
    }

    public void setRight(HTNode<Freq, Value> right) {
        this.right = right;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLeaf(){

        if (this.getRight() == null && this.getLeft() == null){
            return true;
        } else {
            return false;
        }

    }

    public int compareTo(Object two){
        return this.getFreq().compareTo((Freq)((HTNode)two).getFreq());
    }

    public String toString(){
        return "[" + this.getFreq() + "," + this.getValue() + "]";
    }

}