/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Areeba Tariq
 */
public class Chromosome {
    private final java.util.List<Gene> chromosome;
    private final double distance;

    public double getDistance() {
        return this.distance;
    }
    double calculateDistance() {
        double total = 0.0f;
        for(int i = 0; i < this.chromosome.size() - 1; i++) {
            total += this.chromosome.get(i).distance(this.chromosome.get(i+1));
        }
        return total;
    }
    private Chromosome(final java.util.List<Gene> chromosome) {
        this.chromosome = Collections.unmodifiableList(chromosome);
        this.distance = calculateDistance();
    }
    static Chromosome create(final Gene[] points) {
        final java.util.List<Gene> genes = Arrays.asList(Arrays.copyOf(points, points.length));
        Collections.shuffle(genes);
        return new Chromosome(genes);
    }
    java.util.List<Gene> getChromosome() {
        return this.chromosome;
    }
    Chromosome[] crossOver(final Chromosome other) {
        final java.util.List<Gene>[] myDNA = Utils.split(this.chromosome);
        final java.util.List<Gene>[] otherDNA = Utils.split(other.getChromosome());

        final java.util.List<Gene> firstCrossOver = new ArrayList<>(myDNA[0]);

        for(Gene gene : otherDNA[0]) {
            if(!firstCrossOver.contains(gene)) {
                firstCrossOver.add(gene);
            }
        }

        for(Gene gene : otherDNA[1]) {
            if(!firstCrossOver.contains(gene)) {
                firstCrossOver.add(gene);
            }
        }

        final java.util.List<Gene> secondCrossOver = new ArrayList<>(otherDNA[1]);

        for(Gene gene : myDNA[0]) {
            if(!secondCrossOver.contains(gene)) {
                secondCrossOver.add(gene);
            }
        }

        for(Gene gene : myDNA[1]) {
            if(!secondCrossOver.contains(gene)) {
                secondCrossOver.add(gene);
            }
        }

        if(firstCrossOver.size() != Utils.s.length || secondCrossOver.size() != Utils.s.length) {
            throw new RuntimeException("oops!");
        }
        return new Chromosome[]
        {
                new Chromosome(firstCrossOver),
                new Chromosome(secondCrossOver)
        };
    }
    Chromosome mutate() {
        final java.util.List<Gene> copy = new ArrayList<>(this.chromosome);
        int indexA = Utils.randomIndex(copy.size());
        int indexB = Utils.randomIndex(copy.size());
        while(indexA == indexB) {
            indexA = Utils.randomIndex(copy.size());
            indexB = Utils.randomIndex(copy.size());
        }
        Collections.swap(copy, indexA, indexB);
        return new Chromosome(copy);
    }
}
