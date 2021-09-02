package mycontroller;

import java.util.ArrayList;
import java.util.HashMap;

import tiles.MapTile;
import utilities.Coordinate;
import world.World;

/**
 * The Class MapRecorder.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: MapRecorder is used to record the car view at to form an overall outlook of entire map
 */
public class MapRecorder
{

    /** The traversed map. */
    public static ArrayList<Coordinate> traversedMap;

    /** The knownmap. */
    public static HashMap<Coordinate, MapTile> knownmap;

    /** The instance. */
    private static MapRecorder instance;

    /**
     * Gets the map recorder.
     *
     * @return the map recorder
     */
    public static MapRecorder getMapRecorder()
    {
        if (instance == null) {
            instance = new MapRecorder();
            traversedMap = new ArrayList<Coordinate>();
            knownmap = World.getMap();
            updateTraversed();
        }
        return instance;
    }

    /**
     * Record the current view of the car.
     *
     * @param currentView the hashmap that contains the current view
     */
    public void recordMap(HashMap<Coordinate, MapTile> currentView)
    {
        knownmap.putAll(currentView);
        for (Coordinate coord : currentView.keySet()) {
            if (!traversedMap.contains(coord)) {
                traversedMap.add(new Coordinate(coord.x, coord.y));
            }
        }
    }

    /**
     * Update traversedmap.
     */
    private static void updateTraversed()
    {
        for (Coordinate coord : knownmap.keySet()) {
            if (!traversedMap.contains(coord) && knownmap.get(coord).isType(MapTile.Type.WALL)) {
                traversedMap.add(new Coordinate(coord.x, coord.y));
            }
        }
    }
}
