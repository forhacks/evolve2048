package com.github.forhacks.evolve2048;

import java.util.List;

public class MaxNeuron extends Neuron {

    private static final double MAX_CHANGE = 0.1;
    private static final double ADD_PROB = 0.05;

    public MaxNeuron(List<Integer> parents, Layer prev) {
        super(parents, prev);
    }

    @Override
    public void mutate(Layer prev) {

        up += Math.random() * 2 * MAX_CHANGE - MAX_CHANGE;
        left += Math.random() * 2 * MAX_CHANGE - MAX_CHANGE;
        down += Math.random() * 2 * MAX_CHANGE - MAX_CHANGE;
        right += Math.random() * 2 * MAX_CHANGE - MAX_CHANGE;

        if (Math.random() < ADD_PROB) {

            int n1 = (int) (Math.random() * prev.neurons.size());
            int n2 = (int) (Math.random() * prev.neurons.size());
            while (n2 == n1) {
                n2 = (int) (Math.random() * prev.neurons.size());
            }

            parents.add(n1);
            parents.add(n2);

        }

    }

    public double[] getData() {

        int i = parents.size();

        Neuron parent1 = prev.neurons.get(parents.get(i - 2));
        Neuron parent2 = prev.neurons.get(parents.get(i - 1));

        double[] data1 = parent1.getData();
        double[] data2 = parent2.getData();

        if (data1[0] > data2[0]) {

            _value = data1[0];
            _up = data1[1];
            _left = data1[2];
            _down = data1[3];
            _right = data1[4];

        } else {
            _value = data2[0];
            _up = data2[1];
            _left = data2[2];
            _down = data2[3];
            _right = data2[4];

        }

        return new double[] {_value, _up, _left, _down, _right};

    }
}