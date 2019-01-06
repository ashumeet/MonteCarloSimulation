package com.mpt.montecarlo.helper;

import org.springframework.context.annotation.ComponentScan;

import java.text.NumberFormat;

/**
 * This is the helper class for Simulation Service
 * All standard static code should go here
 */
public class SimulationHelper {

    private static NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();

    /**
     * SimulationHelper class should not to be instantiated
     */
    private SimulationHelper() {
    }

    /**
     * @param investment investment at the 0th year
     * @param rate       rate of inflation
     * @param years      number of years for which adjustment is needed
     * @return adjusted value of investment over a given period of time
     */
    public static double adjustForInflation(double investment, double rate, double years) {
        return investment / Math.pow(1d + rate / 100, years);
    }

    /**
     * @param investment dollar amount in double
     * @return dollar amount in beatified string
     */
    public static String dollarAmount(double investment) {
        if (investment < 0d) {
            throw new IllegalArgumentException("Value rate range from 0 to " + Double.MAX_VALUE);
        }
        return FORMATTER.format(investment);
    }
}
