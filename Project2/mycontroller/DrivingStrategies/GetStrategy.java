
package mycontroller.DrivingStrategies;

import java.util.Stack;

import controller.CarController;
import utilities.Coordinate;

/**
 * The Class GetStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: GetStrategy is a superclass for the various getTrap classes.
 */
public abstract class GetStrategy implements IDrivingStrategy
{

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {

    }

}
