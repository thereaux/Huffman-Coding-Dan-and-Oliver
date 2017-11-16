import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.PriorityQueue;

/**
 * Name: olivergreenwald && dantaro
 * Lab Name: HuffmanTree
 * Lab purpose: The goal of HuffmanTree is to create a program that
 * Date: 11/7/17
 */
public class HuffmanTree {

    HTNode root;
    PriorityQueue p;
    HTNode ht;
    Dictionary d;
    int size;

    public HuffmanTree(){

    }

    public HuffmanTree(String s){
        ArrayList chars = new ArrayList();
        ArrayList freqs = new ArrayList();
        int count = 1;
        p = new PriorityQueue();
        for (int i = 0; i < s.length(); i++) {

            int c = chars.indexOf(s.charAt(i));

            if (c == -1){
                chars.add(s.charAt(i));
                freqs.add(1);
                count = 1;
            } else {
                //chars.add(c, s.charAt(i));
                freqs.set(c, (int)freqs.get(c)+1);

            }
        }

        size = chars.size();

        for (int i = 0; i < chars.size(); i++) {
            ht = new HTNode((Comparable)freqs.get(i),chars.get(i), 1);
            p.add(ht);
        }
        System.out.println("Char array: "+chars);
        System.out.println("Frequency array: "+freqs);

     /*
    Builds the Huffman tree
     */
int rightLevels = 0;
int leftLevels = 0;

        while (p.size() != 1) {

            //remove two from p
            HTNode one = (HTNode)p.remove();
            HTNode two = (HTNode)p.remove();
            HTNode newNode = new HTNode((int)one.getFreq()+(int)(two.getFreq()), -1, 1);
            newNode.setLeft(one);
            if (one != null){
                leftLevels++;
            }
            newNode.setRight(two);
            if (two != null){
                rightLevels++;
            }
            p.add(newNode);
        }
        root = (HTNode) p.remove();

        d = new Dictionary() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration keys() {
                return null;
            }

            @Override
            public Enumeration elements() {
                return null;
            }

            @Override
            public Object get(Object key) {
                return null;
            }

            @Override
            public Object put(Object key, Object value) {
                return null;
            }

            @Override
            public Object remove(Object key) {
                return null;
            }
        };
    }

    //base case is a leaf
    //your higher level will have to give the lower levels a string of whats occurred so far (String so far)

    public String binaryToString(){
        String unicode = Integer.toBinaryString(32);
        return unicode + binaryToString(root);
    }

    public String binaryToString(HTNode h){

        String binaryNum = "";

        if (h.isLeaf()){
            binaryNum += "1" + Integer.toBinaryString((Character)h.getValue());
            return binaryNum;
        } else {
            binaryNum += "0";
            binaryNum += binaryToString(h.getLeft()) + binaryToString(h.getRight());
            return binaryNum;
        }
    }

//    public String characterToString(){
//
//    }

    public String to2String(){
        return to2String(root);
    }

    public String to2String(HTNode h){
        String toReturn = "";

        if (h.isLeaf()){
            return toReturn += "[" + h.getValue() + "," + h.getFreq() + "]";
        } else {
            return toReturn+ h.toString() + to2String(h.getLeft()) + to2String(h.getRight());
        }
    }

//go through and travserse the tree, then store the value with the substring it took to get there (binary)
//higher freq shorter the string
//travserse the tree once start at root, then only go through recursing

    public String binaryEncoding(){
        return binaryEncoding(root, "");
    }

    public String binaryEncoding(HTNode h, String encodingSoFar){

        String toReturn = "";

        if (h.isLeaf()){
            //add to dictionary
            d.put(h, encodingSoFar);
            //System.out.println("is leaf true");
            return encodingSoFar;
        } else {
            //recurse, and add to the string so far
                encodingSoFar += binaryEncoding(h.getLeft(), encodingSoFar + "0")
                        + binaryEncoding(h.getRight(), encodingSoFar + "1");
                d.put(h, encodingSoFar);
            return encodingSoFar;
        }
    }

    //compression rate is determined by, number of bits used to represent the uncompressed string
    //ratio of uncompressed to compressed
    public double compRate(){
        double original = 32*size;
        double binaryLength = (double)this.binaryEncoding().length();
        double compRate = binaryLength/original;
        return compRate;
    }

    public static void main(String[] args) {
        //HuffmanTree h = new HuffmanTree("Hello my name is bobby thanks boys");
        HuffmanTree h = new HuffmanTree("The dog gandered at the gander.");
        //HuffmanTree h = new HuffmanTree("1234567890qwertyuiopasdfghjklzxcvbnm,./");

        System.out.println("Node toString"+h.to2String());
        System.out.println("Binary toString: "+h.binaryToString());
        System.out.println("Binary Encoding: "+h.binaryEncoding());
        System.out.println("Huffman compression rate: "+h.compRate()*100+" protzent (% in German)");
    }
}