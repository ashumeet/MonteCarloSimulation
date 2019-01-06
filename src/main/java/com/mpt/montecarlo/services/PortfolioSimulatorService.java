package com.mpt.montecarlo.services;


import com.mpt.montecarlo.domain.Portfolio;
import com.mpt.montecarlo.helper.SimulationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * This the starting point for Simulation.
 */
@Service
public class PortfolioSimulatorService {

    @Autowired
    private SimulationService simulationService;

    /**
     * @param portfolio     give portfolio to ran simulations on
     * @param years         number of years for which you want to run simulation
     * @param runs          number of times you want to run the simulation
     * @param inflationRate the rate at which the inflation is happening
     */
    public HashMap<String, String> simulate(Portfolio portfolio, int years, int runs, double inflationRate) {

        if (years < 0) {
            throw new IllegalArgumentException("Years range from 0 to " + Integer.MAX_VALUE);
        }

        if (runs < 0) {
            throw new IllegalArgumentException("Runs range from 0 to " + Integer.MAX_VALUE);
        }

        if (inflationRate < 0) {
            throw new IllegalArgumentException("Inflation rate range from 0 to " + Double.MAX_VALUE);
        }

        double[] result = simulationService.MonteCarloSimulator(portfolio, years, runs);

        HashMap<String, String> returnValue = new HashMap<>();
        returnValue.put("Median 20th Year", SimulationHelper.dollarAmount(SimulationHelper.adjustForInflation(simulationService.percentile(result, 50), inflationRate, years)));
        returnValue.put("10% Best Case", SimulationHelper.dollarAmount(SimulationHelper.adjustForInflation(simulationService.percentile(result, 90), inflationRate, years)));
        returnValue.put("10% Worst Case", SimulationHelper.dollarAmount(SimulationHelper.adjustForInflation(simulationService.percentile(result, 10), inflationRate, years)));

        return returnValue;

    }

    public HashMap<String, HashMap<String, String>> simulationReturn() {
        HashMap<String, HashMap<String, String>> returnValue = new HashMap<>();
        returnValue.put(Portfolio.AGGRESSIVE.getName(), simulate(Portfolio.AGGRESSIVE, 20, 10000, 3.5));
        returnValue.put(Portfolio.VERY_CONSERVATIVE.getName(), simulate(Portfolio.VERY_CONSERVATIVE, 20, 10000, 3.5));
        return returnValue;
    }
}
