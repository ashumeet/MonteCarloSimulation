package com.mpt.montecarlo.domain;

/**
 * This enum class defines 2 given portfolios for Simulation
 */
public enum Portfolio {

    AGGRESSIVE("A - Aggressive", 100000, 0.094324, 0.15675),
    VERY_CONSERVATIVE("I - Very Conservative", 100000, 0.06189, 0.063438);

    private String name;
    private double investment;
    private double mean;
    private double standardDeviation;

    Portfolio(String name, double investment, double mean, double standardDeviation) {
        this.name = name;
        this.investment = investment;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }

    public String getName() {
        return name;
    }

    public double getInvestment() {
        return investment;
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }
}
