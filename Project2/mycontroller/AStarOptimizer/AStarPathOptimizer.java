package mycontroller.AStarOptimizer;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import tiles.MapTile;
import tiles.TrapTile;
import utilities.Coordinate;
import world.World;

/**
 * The Class AStarPathOptimizer.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: AStarPathOptimizer implement the AStar algorithm that is used to find the path.
 */
public class AStarPathOptimizer
{

    /** The Constant MOVE_COST. */
    private static final int MOVE_COST = 1;

    /** The Constant TRAP_COST. */
    private static final int TRAP_COST = 100;

    /** The grid that represent the map. */
    private HashMap<Coordinate, AStarCell> grid;

    /** The priority queue that store open cells. */
    private PriorityQueue<AStarCell> openCells;

    /** The hashmap that store closed cells. */
    private HashMap<Coordinate, Boolean> closedCells;

    /** The start coordinate. */
    private Coordinate start;

    /** The end coordinate. */
    private Coordinate end;

    /** The variation. */
    private int variation;

    /**
     * Find the best path to go.
     *
     * @param knownmap the map that we already explore
     * @param stack the stack that store coordinateds
     * @param coord the coordinate
     */
    public void optimizePath(HashMap<Coordinate, MapTile> knownmap, Stack<Coordinate> stack, Coordinate coord)
    {
        if ((!checkDirectionX(knownmap, stack.peek(), coord.x) && !checkDirectionX(knownmap, coord, stack.peek().x))
            || (!checkDirectionY(knownmap, stack.peek(), coord.y) && !checkDirectionY(knownmap, coord, stack.peek().y))
            || variation == 1) {
            openCells = new PriorityQueue<AStarCell>((AStarCell c1, AStarCell c2) -> {
                return c1.getFinalCost() < c2.getFinalCost() ? -1 : c1.getFinalCost() > c2.getFinalCost() ? 1 : 0;
            });
            closedCells = new HashMap<Coordinate, Boolean>();
            grid = new HashMap<Coordinate, AStarCell>();

            start = coord;
            end = stack.peek();
            System.out.println(coord.toString());
            for (Coordinate ccord : knownmap.keySet()) {
                grid.put(ccord, new AStarCell(ccord));
                if (variation == 1) {
                    if (knownmap.get(ccord).isType(MapTile.Type.TRAP)) {
                        TrapTile traptile = (TrapTile) knownmap.get(ccord);
                        if (traptile.getTrap().equals("parcel")) {
                            grid.get(ccord).setHeuristicCost(Math.abs(ccord.x - end.x) + Math.abs(ccord.y - end.y));
                        } else {
                            grid.get(ccord)
                                .setHeuristicCost(Math.abs(ccord.x - end.x) + Math.abs(ccord.y - end.y) + TRAP_COST);
                        }
                    } else {
                        grid.get(ccord).setHeuristicCost(Math.abs(ccord.x - end.x) + Math.abs(ccord.y - end.y));
                    }
                } else {
                    grid.get(ccord).setHeuristicCost(Math.abs(ccord.x - end.x) + Math.abs(ccord.y - end.y));
                }
                closedCells.put(ccord, false);
            }

            grid.get(start).setFinalCost(0);
            grid.get(end).setFinalCost(0);

            for (Coordinate ccord : knownmap.keySet()) {
                if (knownmap.get(ccord).isType(MapTile.Type.WALL)) {
                    grid.replace(ccord, null);
                }
            }

            process();
            updateSolution(stack);
        }

    }

    /**
     * Update the cost of coordinate.
     *
     * @param cur the current coordinate
     * @param t the coordinate that need to be checked
     * @param cost the cost to add
     */
    public void updateCost(AStarCell cur, AStarCell t, int cost)
    {
        if (t == null || closedCells.get(t.getCoordinate())) {
            return;
        }

        int tFinalCost = t.getHeuristicCost() + cost;
        boolean isOpen = openCells.contains(grid.get(t.getCoordinate()));

        if (!isOpen || tFinalCost < t.getFinalCost()) {
            t.setFinalCost(tFinalCost);
            t.setParent(cur);
            if (!isOpen) {
                openCells.add(t);
            }
        }
    }

    /**
     * Process of AStar algorithm.
     */
    public void process()
    {
        openCells.add(grid.get(start));

        AStarCell cur;
        int add = 0;

        while (true) {
            cur = openCells.poll();
            if (cur == null) {
                break;
            }

            closedCells.replace(cur.getCoordinate(), true);
            if (cur.equals(grid.get(end))) {
                return;
            }

            AStarCell t;

            if (cur.getCoordinate().x - 1 >= 0) {
                Coordinate curcord = cur.getCoordinate();
                t = grid.get(new Coordinate(curcord.x - 1, curcord.y));
                updateCost(cur, t, cur.getFinalCost() + MOVE_COST);
            }
            if (cur.getCoordinate().x + 1 <= World.MAP_WIDTH) {
                Coordinate curcord = cur.getCoordinate();
                t = grid.get(new Coordinate(curcord.x + 1, curcord.y));
                updateCost(cur, t, cur.getFinalCost() + MOVE_COST);
            }
            if (cur.getCoordinate().y - 1 >= 0) {
                Coordinate curcord = cur.getCoordinate();
                t = grid.get(new Coordinate(curcord.x, curcord.y - 1));
                updateCost(cur, t, cur.getFinalCost() + MOVE_COST);
            }
            if (cur.getCoordinate().y + 1 <= World.MAP_HEIGHT) {
                Coordinate curcord = cur.getCoordinate();
                t = grid.get(new Coordinate(curcord.x, curcord.y + 1));
                updateCost(cur, t, cur.getFinalCost() + MOVE_COST);
            }
        }
    }

    /**
     * Update solution.
     *
     * @param stack the stack that store the coordinate
     */
    private void updateSolution(Stack<Coordinate> stack)
    {
        AStarCell current = grid.get(end);
        grid.get(current.getCoordinate()).setSolution(true);
        if (current.getParent() == null) {
            stack.pop();
        }
        while (current.getParent() != null) {
            Coordinate optRoute = new Coordinate(current.getCoordinate().x, current.getCoordinate().y);
            stack.remove(optRoute);
            stack.add(optRoute);
            grid.get(current.getParent().getCoordinate()).setSolution(true);
            current = current.getParent();
        }
    }

    /**
     * Check direction X.
     *
     * @param knownmap the knownmap
     * @param coord the coordinate
     * @param x the x
     * @return true, if successful
     */
    private boolean checkDirectionX(HashMap<Coordinate, MapTile> knownmap, Coordinate coord, int x)
    {
        int yval = coord.y;

        int difference = coord.x - x;

        int inspector = coord.x;
        if (difference > 0) {
            inspector = x;
        }
        for (int i = 0; i < Math.abs(difference); i++) {
            if (knownmap.get(new Coordinate(inspector + i, yval)).isType(MapTile.Type.WALL)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check direction Y.
     *
     * @param knownmap the knownmap
     * @param coord the coord
     * @param y the y
     * @return true, if successful
     */
    private boolean checkDirectionY(HashMap<Coordinate, MapTile> knownmap, Coordinate coord, int y)
    {
        int xval = coord.x;

        int difference = coord.y - y;

        int inspector = coord.y;
        if (difference > 0) {
            inspector = y;
        }
        for (int i = 0; i < Math.abs(difference); i++) {
            if (knownmap.get(new Coordinate(xval, inspector + i)).isType(MapTile.Type.WALL)) {
                return false;
            }

        }
        return true;
    }

    /**
     * Update variation.
     *
     * @param var the var
     */
    public void update_variation(String var)
    {
        if (var.equals("health")) {
            this.variation = 1;
        } else {
            this.variation = 0;
        }
    }

}
