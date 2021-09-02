package mycontroller.DrivingStrategies;

import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import tiles.MapTile;
import utilities.Coordinate;
import world.World;

/**
 * The Class EndingStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: EndingStrategy is a basic strategy that is used to navigate the car to the exit.
 */
public class EndingStrategy implements IDrivingStrategy
{

    /** The current closest coordinate. */
    private Coordinate currentClosest;

    /**
     * Instantiates a new ending strategy.
     */
    public EndingStrategy()
    {
        currentClosest = new Coordinate(1000, 1000);
    }

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        getClosestEnding(car);
        if (stack.isEmpty()) {
            stack.add(currentClosest);
        }

        else if (stack.firstElement().equals(currentClosest)) {
            return;
        } else {
            stack.remove(0);
            stack.add(0, currentClosest);
        }
    }

    /**
     * Gets the closest ending.
     *
     * @param car the car
     * @return the closest ending
     */
    private void getClosestEnding(CarController car)
    {
        HashMap<Coordinate, MapTile> entiremap = World.getMap();
        Coordinate ccoord = new Coordinate(car.getPosition());

        float xval = 0;
        float yval = 0;

        float xclo = 0;
        float yclo = 0;

        for (Coordinate coord : entiremap.keySet()) {
            if (entiremap.get(coord).isType(MapTile.Type.FINISH)) {
                xval = Math.abs(coord.x - ccoord.x);
                yval = Math.abs(coord.y - ccoord.y);
                xclo = Math.abs(currentClosest.x - ccoord.x);
                yclo = Math.abs(currentClosest.y - ccoord.y);
                if (Math.hypot(xval, yval) <= Math.hypot(xclo, yclo)) {
                    currentClosest = new Coordinate(coord.x, coord.y);
                }
            }
        }
    }

}
