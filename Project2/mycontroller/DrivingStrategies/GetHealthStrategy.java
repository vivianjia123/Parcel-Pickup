package mycontroller.DrivingStrategies;

import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import mycontroller.MapRecorder;
import tiles.MapTile;
import tiles.TrapTile;
import utilities.Coordinate;
import world.World;

/**
 * The Class GetHealthStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: GetHealthStrategy is a basic strategy that is used to get the nearest health trap.
 */
public class GetHealthStrategy extends GetStrategy
{

    /** The lower car health level. */
    private final int LOWER_CAR_HP = 50;

    /**
     * Check if the health level rech the lower health level.
     *
     * @param car the car
     * @return true, if successful
     */
    private boolean checkHealth(CarController car)
    {
        float current_hp = car.getHealth();
        if (current_hp < LOWER_CAR_HP) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.GetStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        HashMap<Coordinate, MapTile> knownmap = MapRecorder.knownmap;
        Coordinate getHealth = null;
        System.out.println("***********");
        if (checkHealth(car)) {
            getHealth = searchNearby(knownmap, new Coordinate(car.getPosition()));
            if (getHealth == null) {
                return;
            }
            if (stack.peek().equals(getHealth)) {
                return;
            } else {
                stack.add(getHealth);
                return;
            }
        }

    }

    /**
     * Search nearby health traps.
     *
     * @param knownmap the knownmap
     * @param ccoord the ccoord
     * @return the coordinate
     */
    private Coordinate searchNearby(HashMap<Coordinate, MapTile> knownmap, Coordinate ccoord)
    {
        HashMap<Coordinate, MapTile> entiremap = World.getMap();

        float xval = 0;
        float yval = 0;

        float xclo = 0;
        float yclo = 0;

        Coordinate currentClosest = null;

        for (Coordinate coord : entiremap.keySet()) {
            if (entiremap.get(coord).isType(MapTile.Type.TRAP)) {
                TrapTile tile = (TrapTile) knownmap.get(coord);
                if (tile.getTrap().equals("water") || tile.getTrap().contentEquals("health")) {
                    xval = Math.abs(coord.x - ccoord.x);
                    yval = Math.abs(coord.y - ccoord.y);
                    if (currentClosest == null) {
                        currentClosest = coord;
                        continue;
                    } else {
                        xclo = Math.abs(currentClosest.x - ccoord.x);
                        yclo = Math.abs(currentClosest.y - ccoord.y);
                        if (Math.hypot(xval, yval) <= Math.hypot(xclo, yclo)) {
                            currentClosest = new Coordinate(coord.x, coord.y);
                        }
                    }
                }
            }
        }
        return currentClosest;
    }
}
