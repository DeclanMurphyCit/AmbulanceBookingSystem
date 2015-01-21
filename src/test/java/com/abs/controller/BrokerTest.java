package com.abs.controller;

import com.abs.domain.PlanningSol;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Declan on 20/01/2015.
 */
@WebAppConfiguration
public class BrokerTest {

    public static void main(String[] args) {

        // Build the Solver
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(
                "com/solver/brokerSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        PlanningSol unsolvedPlanningSol = new PlanningSol();

        // Solve the problem
        solver.solve(unsolvedPlanningSol);
        PlanningSol solvedCloudBalance = (PlanningSol) solver.getBestSolution();

        // Display the result
        System.out.println("\nHere/Solved (BrokerTest.JaVa");

    }
}
