/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Areeba Tariq
 */
public class Gene {
    private final int x;
    private final int y;

    Gene(final int x,final int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    double distance(final Gene other) {
        return sqrt(pow(getX() - other.getX(), 2) + pow(getY() - other.getY(), 2));
    }
}
