package mycontroller.AStarOptimizer;

import utilities.Coordinate;

/**
 * The Class AStarCell.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: AStarCell represent the cell that is used in AStar Algorithm, that is, the coordinate with its
 *               heuristic ccost.
 */
public class AStarCell
{

    /** The coordinate. */
    private Coordinate coordinate;

    /** The parent. */
    private AStarCell parent;

    /** The heuristic cost. */
    private int heuristiccost;

    /** The final cost. */
    private int finalcost;

    /** The solution. */
    private boolean solution;

    /**
     * Instantiates a new a star cell.
     *
     * @param coord the coordination
     */
    public AStarCell(Coordinate coord)
    {
        coordinate = coord;
        solution = false;
    }

    /**
     * Gets the final cost.
     *
     * @return the final cost
     */
    public int getFinalCost()
    {
        return finalcost;
    }

    /**
     * Sets the final cost.
     *
     * @param cost the new final cost
     */
    public void setFinalCost(int cost)
    {
        finalcost = cost;
    }

    /**
     * Gets the heuristic cost.
     *
     * @return the heuristic cost
     */
    public int getHeuristicCost()
    {
        return heuristiccost;
    }

    /**
     * Sets the heuristic cost.
     *
     * @param cost the new heuristic cost
     */
    public void setHeuristicCost(int cost)
    {
        heuristiccost = cost;
    }

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    public AStarCell getParent()
    {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the new parent
     */
    public void setParent(AStarCell parent)
    {
        this.parent = parent;
    }

    /**
     * Gets the coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate()
    {
        return coordinate;
    }

    /**
     * Gets the solution.
     *
     * @return the solution
     */
    public boolean getSolution()
    {
        return this.solution;
    }

    /**
     * Sets the solution.
     *
     * @param solution the new solution
     */
    public void setSolution(boolean solution)
    {
        this.solution = solution;
    }
}
