
package mycontroller.MoveCoords;

import controller.CarController;
import mycontroller.CheckDirection;
import mycontroller.MapRecorder;
import tiles.MapTile;
import utilities.Coordinate;
import world.WorldSpatial.Direction;

/**
 * The Class MoveCoords.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: MoveCoords is used to move the car to the certain coordinate.
 */
public class MoveCoords implements IMoving
{

    /** The car max speed. */
    private int CAR_MAX_SPEED = 10;

    /** The checker. */
    private CheckDirection checker = CheckDirection.getInstance();

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.MoveCoords.IMoving#moveToCoords(utilities.Coordinate, controller.CarController)
     */
    @Override
    public void moveToCoords(Coordinate cord, CarController car)
    {
        if (car.getSpeed() < CAR_MAX_SPEED) {
            car.applyForwardAcceleration();
        }
        moveFoward(cord, car);

    }

    /**
     * Move foward.
     *
     * @param cord the cord
     * @param car the car
     */
    private void moveFoward(Coordinate cord, CarController car)
    {
        Coordinate ccoord = new Coordinate(car.getPosition());
        float xval = cord.x - ccoord.x;
        float yval = cord.y - ccoord.y;
        Direction dir = car.getOrientation();

        System.out.println(xval);

        if (xval > 0) {
            switch (dir) {
                case SOUTH:
                    if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnLeft();
                    }
                    break;
                case NORTH:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnRight();
                    }
                    break;
                case WEST:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && yval > 0) {
                        car.turnRight();
                    } else if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && yval < 0) {
                        car.turnLeft();
                    }
                    break;
            }
            return;
        } else if (xval < 0) {
            switch (dir) {
                case SOUTH:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnRight();
                    }
                    break;
                case NORTH:
                    if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnLeft();
                    }
                    break;
                case EAST:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && yval < 0) {
                        car.turnRight();
                    } else if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && yval > 0) {
                        car.turnLeft();
                    }
                    break;
            }
            return;
        }
        if (yval < 0) {
            switch (dir) {
                case EAST:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnRight();
                    }
                    break;
                case WEST:
                    if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnLeft();
                    }
                    break;
                case NORTH:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && xval > 0) {
                        car.turnRight();
                    } else if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && xval < 0) {
                        car.turnLeft();
                    }
                    break;
            }
            return;
        } else if (yval > 0) {
            switch (dir) {
                case WEST:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnRight();
                    }
                    break;
                case EAST:
                    if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL)) {
                        car.turnLeft();
                    }
                    break;
                case SOUTH:
                    if (!checker.checkright(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && xval < 0) {
                        car.turnRight();
                    } else if (!checker.checkleft(MapRecorder.knownmap, car).isType(MapTile.Type.WALL) && xval > 0) {
                        car.turnLeft();
                    }
                    break;
            }
            return;
        }
    }
}
