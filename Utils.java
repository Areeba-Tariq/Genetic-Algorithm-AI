/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author Areeba Tariq
 */
public class Utils {
        private final static Random R = new Random(100);
    static final Gene[] s = generateData(8);
    private Utils() {
        throw new RuntimeException("No!");
    }
    private static Gene[] generateData(final int numDataPoints) {
        final Gene[] data = new Gene[numDataPoints];
        for(int i = 0; i < numDataPoints; i++) {
            data[i] = new Gene(Utils.randomIndex(30),Utils.randomIndex(30));
        }
        return data;
    }
    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }
    static<T> java.util.List<T>[] split(final java.util.List<T> list) {
        final java.util.List<T> first = new ArrayList<>();
        final java.util.List<T> second = new ArrayList<>();
        final int size = list.size();
        final int partitionIndex = 1 + Utils.randomIndex(list.size());
        IntStream.range(0, size).forEach(i -> {
            if(i < (size+1)/partitionIndex) {
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
        });
        return (java.util.List<T>[]) new java.util.List[] {first, second};
    }
}
