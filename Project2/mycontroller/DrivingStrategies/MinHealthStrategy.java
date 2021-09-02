package mycontroller.DrivingStrategies;

import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import mycontroller.CheckDirection;
import mycontroller.MapRecorder;
import mycontroller.MyAutoController;
import tiles.MapTile;
import utilities.Coordinate;

/**
 * The Class MinimizeHealth.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: MinimizeHealth is a basic strategy that is used to minimize the health usage.
 */
public class MinHealthStrategy implements IDrivingStrategy
{

    /** The check direction object of CheckDirection. */
    private CheckDirection checkDirection = CheckDirection.getInstance();

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        HashMap<Coordinate, MapTile> knownmap = MapRecorder.knownmap;
        Coordinate coord = new Coordinate(car.getPosition());

        if (stack.isEmpty()) {
            return;
        }

        // Coordinate destination = new Coordinate(stack.firstElement().x, stack.firstElement().y);
        Coordinate destination = new Coordinate(MyAutoController.lastCoord.x, MyAutoController.lastCoord.y);
        if (checkDirection.checkfront(knownmap, car).isType(MapTile.Type.TRAP)) {
            stack.clear();
            stack.add(destination);
        }
    }

}
