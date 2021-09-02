package mycontroller.DrivingStrategies;

import java.util.Stack;

import controller.CarController;
import utilities.Coordinate;

/**
 * The Class IDrivingStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: IDrivingStrategy is an interface that is used to implement different strategies for the car.
 */
public interface IDrivingStrategy
{

    /**
     * Pass the stack of the coordinate and the car object into this function to get the next coordinate that car need
     * to follow.
     *
     * @param stack the stack
     * @param car the car
     */
    public void action(Stack<Coordinate> stack, CarController car);
}
