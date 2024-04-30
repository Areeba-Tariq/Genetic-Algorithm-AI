/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package geneticalgorithm;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Areeba Tariq
 */
public class GeneticAlgorithm {
    private static final int GRID_SIZE = 20;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> createAndShowGUI(GRID_SIZE));
    }
     private static void createAndShowGUI(int gridSize) {
        JFrame frame = new JFrame("Star");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 700);
        DemoPanel Panel = new DemoPanel(gridSize);
        frame.add(Panel);
        frame.setVisible(true);
    } 
}
