package com.mpt.montecarlo.services;

import com.mpt.montecarlo.domain.Portfolio;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * All the simulation logic resides here
 */
@Service
public class SimulationService {

    /**
     * This is where the actual MONTY CARLO SIMULATION for given number of years and runs happens
     *
     * @param portfolio portfolio on which we want to run simulation
     * @param years     number of years for which we want to run simulation for
     * @param runs      number of simulation ran
     * @return sorted array of growth simulations ran for given number of years
     */
    public double[] MonteCarloSimulator(Portfolio portfolio, int years, int runs) {
        NormalDistribution normalDistribution = new NormalDistribution(portfolio.getMean(), portfolio.getStandardDeviation());
        return IntStream.range(0, runs).parallel().mapToDouble(r -> {
            double investmentGrowth = portfolio.getInvestment();
            for (int i = 0; i < years; i++) {
                investmentGrowth *= normalDistribution.sample() +1d;
            }
            return investmentGrowth;
        }).sorted().toArray();
    }

    /**
     * @param sortedSimulatedGrowth sorted list of simulated growth
     * @param percentile            percentile value to be extracted
     * @return the value at the given percentile index of a sorted list of simulated growth
     */
    public double percentile(final double[] sortedSimulatedGrowth, int percentile) {
        if (percentile <= 0 && percentile >= 100) {
            throw new IllegalArgumentException("Percentile value needs to be between 0 and 100");
        }
        return sortedSimulatedGrowth[((sortedSimulatedGrowth.length / 100) * percentile) - 1];
    }
}
