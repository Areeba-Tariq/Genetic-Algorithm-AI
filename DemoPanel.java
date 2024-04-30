/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Areeba Tariq
 */
public class DemoPanel extends JPanel{
    private final int nodeSize = 15;
    private final Color backgroundColor = Color.BLACK;
    private final Color nodeColor = Color.WHITE;
    Node[][] node = new Node[20][20];
    Node start, end, solid, current;
    ArrayList<Node> openList  =new ArrayList<>();
    ArrayList<Node> closeList  =new ArrayList<>();
    boolean goalReached=false;
    int c;
    

    public DemoPanel(int gridSize) {
        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(gridSize, gridSize));
        Random random = new Random();
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        
        for (int col = 0; col < gridSize; col++) {
            for (int row = 0; row < gridSize; row++) {
                node[col][row] = new Node(col, row);
                this.add(node[col][row]);
                if (random.nextInt(100) < 10) {
                    solid(col, row);
                }
            }
        }
        start(0, 0);
        end(15, 8);
        heuristic();
    }

    public void start(int col, int row) {
        node[col][row].startNode();
        start = node[col][row];
        current = start;
    }

    public void end(int col, int row) {
        node[col][row].endNode();
        end = node[col][row];
    }

    public void solid(int col, int row) {
        node[col][row].solid();
        solid = node[col][row];
    }
    public void heuristic() {
        for (int col = 0; col < 20; col++) {
            for (int row = 0; row < 20; row++) {
                int xDistance = Math.abs(node[col][row].col - end.col);
                int yDistance = Math.abs(node[col][row].row - end.row);
                node[col][row].hcost = (int) Math.sqrt(xDistance * xDistance + yDistance * yDistance);
                 xDistance= Math.abs(node[col][row].col - start.col);
                 yDistance= Math.abs(node[col][row].row - start.row);
                node[col][row].gcost = xDistance + yDistance;
                node[col][row].fcost = node[col][row].hcost + node[col][row].gcost;
                node[col][row].setMargin(new Insets(0, 0, 0, 0));
            }
        }
    }
    public void search(){
         while (goalReached==false) {
            current.close();
            closeList.add(current);
            openList.remove(current);
            if(current.row-1>=0){
                if(node[current.col][current.row-1].open==false && node[current.col][current.row-1].close==false&&node[current.col][current.row-1].solid==false){
                    node[current.col][current.row-1].open();
                    node[current.col][current.row-1].p = current;
                    openList.add(node[current.col][current.row-1]);
                }
            }
            if(current.row-1>=0 && current.col-1>=0){
                if(node[current.col-1][current.row-1].open==false && node[current.col-1][current.row-1].close==false&&node[current.col-1][current.row-1].solid==false){
                    node[current.col-1][current.row-1].open();
                    node[current.col-1][current.row-1].p = current;
                    openList.add(node[current.col-1][current.row-1]);
                }
            }
            if(current.row-1>=0 && current.col+1<20){
                if(node[current.col+1][current.row-1].open==false && node[current.col+1][current.row-1].close==false&&node[current.col+1][current.row-1].solid==false){
                    node[current.col+1][current.row-1].open();
                    node[current.col+1][current.row-1].p = current;
                    openList.add(node[current.col+1][current.row-1]);
                }
            }
            if(current.row+1<20 && current.col-1>=0){
                if(node[current.col-1][current.row+1].open==false && node[current.col-1][current.row+1].close==false&&node[current.col-1][current.row+1].solid==false){
                    node[current.col-1][current.row+1].open();
                    node[current.col-1][current.row+1].p = current;
                    openList.add(node[current.col-1][current.row+1]);
                }
            }
            if(current.row+1<20 && current.col+1<20){
                if(node[current.col+1][current.row+1].open==false && node[current.col+1][current.row+1].close==false&&node[current.col+1][current.row+1].solid==false){
                    node[current.col+1][current.row+1].open();
                    node[current.col+1][current.row+1].p = current;
                    openList.add(node[current.col+1][current.row+1]);
                }
            }
            if(current.col-1>=0){
                if(node[current.col-1][current.row].open==false && node[current.col-1][current.row].close==false&&node[current.col-1][current.row].solid==false){
                    node[current.col-1][current.row].open();
                    node[current.col-1][current.row].p = current;
                    openList.add(node[current.col-1][current.row]);
                }
            }
            if(current.row+1<20){
                if(node[current.col][current.row+1].open==false && node[current.col][current.row+1].close==false&&node[current.col][current.row+1].solid==false){
                    node[current.col][current.row+1].open();
                    node[current.col][current.row+1].p = current;
                    openList.add(node[current.col][current.row+1]);
                }
            }
            if(current.col+1<20){
                if(node[current.col+1][current.row].open==false && node[current.col+1][current.row].close==false&&node[current.col+1][current.row].solid==false){
                    node[current.col+1][current.row].open();
                    node[current.col+1][current.row].p = current;
                    openList.add(node[current.col+1][current.row]);
                }
            }
            if (openList.isEmpty()) {
                for (int col = 0; col < 20; col++) {
                    for (int row = 0; row < 20; row++) {
                        node[col][row].noPath();
                    }
                }
                break;
            }
            int index=0;
            int max=99999;
            for(int i=0;i<openList.size();i++){
                if(openList.get(i).fcost < max){
                    index = i;
                    max=openList.get(i).fcost;      
                }
                else if(openList.get(i).fcost == max){
                    if(openList.get(i).gcost < openList.get(index).gcost){
                        index=i;
                    }
                }
            }
            current = openList.get(index);
            if(current == end){
                goalReached=true;
                Node a=end;
                while(a!=start){
                    a=a.p;
                    if(a!=start){
                        a.path();
                    }
                }
            }
        }
    }
}
