
package mycontroller;

import java.util.HashMap;

import controller.CarController;
import tiles.MapTile;
import utilities.Coordinate;
import world.WorldSpatial.Direction;

/**
 * The Class CheckDirection.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: CheckDirection is used to check each direction of the car.
 */
public class CheckDirection
{

    /** The Constant instance. */
    private static final CheckDirection instance = new CheckDirection();

    /**
     * Gets the single instance of CheckDirection.
     *
     * @return single instance of CheckDirection
     */
    public static CheckDirection getInstance()
    {
        return instance;
    }

    /**
     * Check left.
     *
     * @param knownmap the knownmap
     * @param car the car
     * @return the map tile
     */
    public MapTile checkleft(HashMap<Coordinate, MapTile> knownmap, CarController car)
    {
        Coordinate coord = new Coordinate(car.getPosition());
        Direction dir = car.getOrientation();
        int x = 0;
        int y = 0;
        MapTile tile;

        switch (dir) {
            case NORTH:
                x -= 1;
                break;
            case SOUTH:
                x += 1;
                break;
            case EAST:
                y += 1;
                break;
            case WEST:
                y -= 1;
                break;
        }
        tile = knownmap.get(new Coordinate(coord.x + x, coord.y + y));
        return tile;
    }

    /**
     * Check right.
     *
     * @param knownmap the knownmap
     * @param car the car
     * @return the map tile
     */
    public MapTile checkright(HashMap<Coordinate, MapTile> knownmap, CarController car)
    {
        Coordinate coord = new Coordinate(car.getPosition());
        Direction dir = car.getOrientation();
        int x = 0;
        int y = 0;
        MapTile tile;

        switch (dir) {
            case NORTH:
                x += 1;
                break;
            case SOUTH:
                x -= 1;
                break;
            case EAST:
                y -= 1;
                break;
            case WEST:
                y += 1;
                break;
        }
        tile = knownmap.get(new Coordinate(coord.x + x, coord.y + y));
        return tile;
    }

    /**
     * Check front.
     *
     * @param knownmap the knownmap
     * @param car the car
     * @return the map tile
     */
    public MapTile checkfront(HashMap<Coordinate, MapTile> knownmap, CarController car)
    {
        Coordinate coord = new Coordinate(car.getPosition());
        Direction dir = car.getOrientation();
        int x = 0;
        int y = 0;
        MapTile tile;

        switch (dir) {
            case NORTH:
                y += 1;
                break;
            case SOUTH:
                y -= 1;
                break;
            case EAST:
                x += 1;
                break;
            case WEST:
                x -= 1;
                break;
        }
        tile = knownmap.get(new Coordinate(coord.x + x, coord.y + y));
        return tile;
    }

}
