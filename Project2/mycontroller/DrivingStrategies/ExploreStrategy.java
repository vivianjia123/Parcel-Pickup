package mycontroller.DrivingStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import mycontroller.MapRecorder;
import tiles.MapTile;
import utilities.Coordinate;
import world.World;

/**
 * The Class ExploreStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: ExploreStrategy is a basic strategy that is used to explore the entire map.
 */
public class ExploreStrategy implements IDrivingStrategy
{

    /** The instance. */
    private static ExploreStrategy instance;

    /** The current coordinate. */
    private Coordinate currentCord;

    /**
     * Gets the explore strategy.
     *
     * @return the explore strategy
     */
    public static ExploreStrategy getExploreStrategy()
    {
        if (instance == null) {
            instance = new ExploreStrategy();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        ArrayList<Coordinate> traversedmap = MapRecorder.traversedMap;
        HashMap<Coordinate, MapTile> knownmap = MapRecorder.knownmap;
        Coordinate carcoord = new Coordinate(car.getPosition());
        Coordinate insert = null;
        int i = 0;

        if (stack.contains(currentCord)) {
            return;
        }

        while (insert == null) {
            insert = getUnfound(traversedmap, knownmap, carcoord, i++);
            if (i > World.MAP_HEIGHT && i > World.MAP_WIDTH) {
                while (true) {
                    insert = new Coordinate((int) (Math.random() * World.MAP_WIDTH + 1),
                        (int) (Math.random() * World.MAP_HEIGHT + 1));
                    if (knownmap.get(insert).isType(MapTile.Type.ROAD)) {
                        break;
                    }
                }
            }
        }

        if (stack.contains(insert)) {
            return;
        }

        currentCord = insert;
        stack.add(insert);
    }

    /**
     * Gets the unfound coordinates.
     *
     * @param traversedmap the traversedmap
     * @param knownmap the knownmap
     * @param coord the coordinate
     * @param value the value
     * @return the unfound
     */
    private Coordinate getUnfound(ArrayList<Coordinate> traversedmap, HashMap<Coordinate, MapTile> knownmap,
        Coordinate coord, int value)
    {
        Coordinate insert;
        if ((!traversedmap.contains(insert = new Coordinate(coord.x + value, coord.y)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x - value, coord.y)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x, coord.y + value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x, coord.y - value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        }
        if ((!traversedmap.contains(insert = new Coordinate(coord.x + value, coord.y + value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x - value, coord.y + value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x - value, coord.y + value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        } else if (!traversedmap.contains((insert = new Coordinate(coord.x + value, coord.y - value)))
            && (knownmap.get(insert) != null)) {
            return insert;
        }
        return null;
    }
}
