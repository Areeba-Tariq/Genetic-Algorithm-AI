/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Areeba Tariq
 */
public class Population {
    private java.util.List<Chromosome> population;
    private final int initialSize;

    public Population (final Gene[] points,final int initialSize) {
        this.population = init(points, initialSize);
        this.initialSize = initialSize;
    }

    java.util.List<Chromosome> getPopulation() {
        return this.population;
    }

    Chromosome getAlpha() {
        return this.population.get(0);
    }

    private java.util.List<Chromosome> init(final Gene[] points, final int initialSize) {
        final java.util.List<Chromosome> k = new ArrayList<>();
        for(int i = 0; i < initialSize; i++) {
            final Chromosome chromosome = Chromosome.create(points);
            k.add(chromosome);
        }
        return k;
    }

    private void doSelection() {
        this.population.sort(Comparator.comparingDouble(Chromosome::getDistance));
        this.population = this.population.stream().limit(this.initialSize).collect(Collectors.toList());
    }

    private void doSpawn() {
        IntStream.range(0, 100).forEach(e -> this.population.add(Chromosome.create(Utils.s)));
    }

    private void doMutation() {
        final java.util.List<Chromosome> newPopulation = new ArrayList<>();
        for(int i = 0; i < this.population.size()/10; i++) {
            final Chromosome mutation = this.population.get(Utils.randomIndex(this.population.size())).mutate();
            newPopulation.add(mutation);
        }
        this.population.addAll(newPopulation);
    }
    
    public void doGnes(){
        doCrossOver();
        doMutation();
        doSpawn();
        doSelection();
    
    }
    
    private void doCrossOver() {

        final java.util.List<Chromosome> newPopulation = new ArrayList<>();
        for(final Chromosome chromosome : this.population) {
            final Chromosome partner = getCrossOverPartner(chromosome);
            newPopulation.addAll(Arrays.asList(chromosome.crossOver(partner)));
        }
        this.population.addAll(newPopulation);
    }

    private Chromosome getCrossOverPartner(final Chromosome chromosome) {
        Chromosome partner = this.population.get(Utils.randomIndex(this.population.size()));
        while(chromosome == partner) {
            partner = this.population.get(Utils.randomIndex(this.population.size()));
        }
        return partner;
    }
}
