package com.github.forhacks.evolve2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Player {

    private static final int MAX_INIT_LAYER = 5;
    private static final int MIN_INIT_LAYER = 3;
    private static final double LAYER_ADD_PROB = 0.05;

    List<Layer> layers;

    Player() {

        int size = Main.random.nextInt((MAX_INIT_LAYER - MIN_INIT_LAYER) / 2) * 2 + MIN_INIT_LAYER;

        layers = new ArrayList<>();

        layers.add(new Layer());

        for (int i = 1; i < size; i++) {

            layers.add(new Layer(layers.get(i - 1)));

        }

    }

    Player(Player original) {

        layers = new ArrayList<>();

        layers.add(new Layer());

        for (int i = 1; i < original.layers.size(); i++) {
            layers.add(new Layer(original.layers.get(i), layers.get(i - 1)));
        }

    }

    int run(Game game) {

        double[] move = new double[4];

        for (int i = 0; i < 16; i++) {
            Neuron n = layers.get(0).neurons.get(i);
            if (n instanceof InputNeuron) {
                InputNeuron n2 = (InputNeuron) n;
                n2.setValue(game.grid[i/4][i%4], game.maxTile());
            }
        }

        for (int i = 0 ; i < layers.get(layers.size() - 1).neurons.size(); i++) {

            double[] data = layers.get(layers.size() - 1).neurons.get(i).getData();

            move[0] += data[1];
            move[1] += data[2];
            move[2] += data[3];
            move[3] += data[4];

        }

        Integer[] indices = new Integer[] {0, 1, 2, 3};

        Arrays.sort(indices, Comparator.comparingInt((Integer o) -> (int) move[o]));

        int i = 0;
        while (!game.canMove(indices[i])) {
            i++;
        }

        return indices[i];

    }

    void mutate() {


        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).mutate(layers.get(i - 1));
        }

        if (Main.random.nextDouble() < LAYER_ADD_PROB) {
            layers.add(new Layer(layers.get(layers.size() - 1)));
            layers.add(new Layer(layers.get(layers.size() - 1)));
        }

    }

}
