
package mycontroller;

import mycontroller.DrivingStrategies.AvoidWallStrategy;
import mycontroller.DrivingStrategies.CompositeStrategy;
import mycontroller.DrivingStrategies.EndingStrategy;
import mycontroller.DrivingStrategies.ExploreStrategy;
import mycontroller.DrivingStrategies.GetHealthStrategy;
import mycontroller.DrivingStrategies.GetParcelStrategy;
import mycontroller.DrivingStrategies.IDrivingStrategy;
import mycontroller.DrivingStrategies.MinHealthStrategy;
import swen30006.driving.Simulation.StrategyMode;

/**
 * The Class StrategyFactory.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: StrategyFactory handles the creation of composite strategy.
 */
public class StrategyFactory
{

    /** The instance of StrategyFactory. */
    private static StrategyFactory instance = null;

    /** The IDrivingStrategy strategies. */
    private IDrivingStrategy strategies = null;

    /**
     * Gets the single instance of StrategyFactory.
     *
     * @return single instance of StrategyFactory
     */
    public static StrategyFactory getInstance()
    {
        if (instance == null) {
            instance = new StrategyFactory();
        }
        return instance;
    }

    /**
     * Gets the composite strategy.
     *
     * @param stage the stage
     * @param mode the mode
     * @return the composite strategy
     */
    public IDrivingStrategy getCompositeStrategy(int stage, StrategyMode mode)
    {
        CompositeStrategy composStrat = new CompositeStrategy();
        switch (stage) {
            case 0:
                if (mode == StrategyMode.HEALTH) {
                    composStrat.add(new MinHealthStrategy());
                }
                composStrat.add(new EndingStrategy());
                composStrat.add(new GetParcelStrategy());
                composStrat.add(new GetHealthStrategy());
                composStrat.add(new AvoidWallStrategy());
                strategies = composStrat;
                break;
            case 1:
                System.out.println("wut");
                if (mode == StrategyMode.HEALTH) {
                    composStrat.add(new MinHealthStrategy());
                }
                composStrat.add(ExploreStrategy.getExploreStrategy());
                composStrat.add(new GetParcelStrategy());
                composStrat.add(new GetHealthStrategy());
                composStrat.add(new AvoidWallStrategy());

                strategies = composStrat;
                break;
            case 2:

                composStrat.add(new EndingStrategy());
                composStrat.add(new AvoidWallStrategy());

                strategies = composStrat;
                break;
            default:
                strategies = new AvoidWallStrategy();

        }

        return strategies;
    }

}
