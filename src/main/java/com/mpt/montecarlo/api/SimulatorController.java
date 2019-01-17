package com.mpt.montecarlo.api;


import com.mpt.montecarlo.services.PortfolioSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class SimulatorController {

    @Autowired
    PortfolioSimulatorService portfolioSimulatorService;

    @RequestMapping("/simulation")
    public HashMap<String, HashMap<String, String>> greeting() {
        return portfolioSimulatorService.simulationReturn();
    }
}
