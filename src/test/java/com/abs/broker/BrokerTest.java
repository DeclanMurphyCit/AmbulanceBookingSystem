package com.abs.broker;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.AmbulanceCompany;
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

        for(int i = 0; i < 10; i++) {
            // Build the Solver
            SolverFactory solverFactory = SolverFactory.createFromXmlResource("com/solver/brokerSolverConfig.xml");
            Solver solver = solverFactory.buildSolver();

            PlanningSol unsolvedPlanningSol = new BrokerDataGenerator().createPlanningSol(20);

            // Solve the problem
            solver.solve(unsolvedPlanningSol);
            PlanningSol solvedSolution = (PlanningSol) solver.getBestSolution();

            try {
                System.out.print("Score:" + solvedSolution.getScore());
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                System.out.print("\t\tID: " + solvedSolution.getAbList().get(0).getAmbCompany().getId());
                System.out.print("\t\tCost: " + solvedSolution.getAbList().get(0).getAmbCompany().getCost());
                System.out.print("\t\tCardiac: " + solvedSolution.getAbList().get(0).getAmbCompany().isCardiac()+"\n");
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    public static String toDisplayString(PlanningSol ps) {
        StringBuilder displayString = new StringBuilder();
        displayString.append("|");
        for (AmbulanceCompany ac : ps.getAcList()) {

            displayString.append(ac.isCardiac()).append("|");

        }
        return displayString.toString();
    }
}