package mycontroller.DrivingStrategies;

import java.util.ArrayList;
import java.util.Stack;

import controller.CarController;
import utilities.Coordinate;

/**
 * The Class CompositeStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: CompositeStrategy is used to store the strategies that are required to accomplish two different
 *               variations.
 */
public class CompositeStrategy implements IDrivingStrategy
{

    /** The arraylist that store strategies. */
    private static ArrayList<IDrivingStrategy> strategies = new ArrayList<IDrivingStrategy>();

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {

        if (!stack.isEmpty()) {
            if (stack.peek().equals(new Coordinate(car.getPosition()))) {
                stack.pop();
            }
        }

        for (IDrivingStrategy strategy : strategies) {
            strategy.action(stack, car);
        }

        if (!stack.isEmpty()) {
            if (stack.peek().equals(new Coordinate(car.getPosition()))) {
                stack.pop();
            }
        }

        strategies.clear();

    }

    /**
     * Add the strategy into arraylist.
     *
     * @param strategy the strategy
     */
    public void add(IDrivingStrategy strategy)
    {
        strategies.add(strategy);
    }

    /**
     * Clear the arraylist.
     */
    public void clear()
    {
        strategies.clear();
    }

    /**
     * Checks if the arraylist is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty()
    {
        return strategies.isEmpty();
    }

}
