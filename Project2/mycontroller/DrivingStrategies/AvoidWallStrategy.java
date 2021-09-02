package mycontroller.DrivingStrategies;

import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import mycontroller.CheckDirection;
import mycontroller.MapRecorder;
import tiles.MapTile;
import utilities.Coordinate;
import world.WorldSpatial.Direction;

/**
 * The Class AvoidWallStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: AvoidWallStrategy is a basic strategy that to ensure the car is not collides with the wall tiles.
 */
public class AvoidWallStrategy implements IDrivingStrategy
{

    /** The checker is used to call the check direction functions. */
    private CheckDirection checker = CheckDirection.getInstance();

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        Direction dir = car.getOrientation();
        Coordinate coord = new Coordinate(car.getPosition());
        HashMap<Coordinate, MapTile> knownmap = MapRecorder.knownmap;
        MapTile.Type walltile = MapTile.Type.WALL;
        int x = 0;
        int y = 0;
        float xval;
        float yval;
        if (checker.checkfront(knownmap, car).isType(walltile)) {
            switch (dir) {
                case NORTH:
                    if (checker.checkleft(knownmap, car).isType(walltile)) {
                        x += 1;
                    } else if (checker.checkright(knownmap, car).isType(walltile)) {
                        x -= 1;
                    }
                    break;
                case SOUTH:
                    if (checker.checkleft(knownmap, car).isType(walltile)) {
                        x -= 1;
                    } else if (checker.checkright(knownmap, car).isType(walltile)) {
                        x += 1;
                    }
                    break;
                case EAST:
                    if (checker.checkleft(knownmap, car).isType(walltile)) {
                        y -= 1;
                    } else if (checker.checkright(knownmap, car).isType(walltile)) {
                        y += 1;
                    }
                    break;
                case WEST:
                    if (checker.checkleft(knownmap, car).isType(walltile)) {
                        y += 1;
                    } else if (checker.checkright(knownmap, car).isType(walltile)) {
                        y -= 1;
                    }
                    break;
            }

            if (x == 0 && y == 0) {
                Coordinate ccoord = new Coordinate(car.getPosition());
                xval = stack.peek().x - ccoord.x;
                yval = stack.peek().y - ccoord.y;
                if (dir == Direction.EAST || dir == Direction.WEST) {
                    y = decider(xval, yval, 'y');
                    if (y == 0) {
                        y = 1;
                    }
                } else if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    x = decider(xval, yval, 'x');
                    if (x == 0) {
                        x = 1;
                    }
                }
            }
            if (stack.peek().equals((new Coordinate(coord.x + x, coord.y + y)))) {
                return;
            } else {
                stack.add(new Coordinate(coord.x + x, coord.y + y));
            }
        }
    }

    /**
     * Decider the x and y's value.
     *
     * @param xval the x-value
     * @param yval the y-value
     * @param xy the x and y char value
     * @return the int
     */
    private int decider(float xval, float yval, char xy)
    {
        if (xy == 'x') {
            if (xval > 0) {
                return 1;
            } else if (xval < 0) {
                return -1;
            }
        } else if (xy == 'y') {
            if (yval > 0) {
                return 1;
            } else if (yval < 0) {
                return -1;
            }
        }
        return 0;
    }
}
