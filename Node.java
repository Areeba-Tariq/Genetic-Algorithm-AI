/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Areeba Tariq
 */
public class Node extends JButton{
    int col;
    int row;
    int hcost;
    int gcost;
    int fcost;
    Node p;
    boolean start;
    boolean end;
    boolean solid;
    boolean open;
    boolean close;
    public Node(int col, int row) {
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
    }
    public void startNode() {
        setBackground(Color.blue);
        start = true;
    }
    public void noPath(){
        setBackground(Color.red);
    }
    public void endNode() {
        setBackground(Color.green);
        end = true;
    }
    public void solid() {
        setBackground(Color.black);
        solid = true;
    }
    public void open(){
        open = true;
    }
    public void close(){
        close = true;
    }
    public void path(){
        setBackground(Color.gray);
    }
}
