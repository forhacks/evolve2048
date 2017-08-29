package com.github.forhacks.evolve2048;

import java.util.ArrayList;

public class InputNeuron extends Neuron {

    private static final double MAX_CHANGE = 0.1;

    private double value;

    public InputNeuron() {
        super(new ArrayList<>(), null);
    }

    public void setValue(int value) {
        this.value = value; this._value = value;
    }

    public double[] getData() {

        return new double[] {_value, 0, 0, 0, 0};

    }

    public void mutate(Layer prev) {}

}