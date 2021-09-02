package mycontroller;

import java.util.Stack;

import controller.CarController;
import mycontroller.AStarOptimizer.AStarPathOptimizer;
import mycontroller.DrivingStrategies.IDrivingStrategy;
import mycontroller.MoveCoords.IMoving;
import mycontroller.MoveCoords.MoveCoords;
import swen30006.driving.Simulation;
import swen30006.driving.Simulation.StrategyMode;
import utilities.Coordinate;
import world.Car;
import world.World;

/**
 * The Class MyAutoController.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: MyAutoController is the auto driver for the current implementation of the car.
 */
public class MyAutoController extends CarController
{

    /** The stack that store the coordinates. */
    private Stack<Coordinate> stack;

    /** The map recorder. */
    private MapRecorder mapRecorder;

    /** The move method of the car. */
    private IMoving moveCoords;

    /** The path optimizer to find the path. */
    private AStarPathOptimizer pathOptimizer;

    /** The strategies. */
    private IDrivingStrategy strategies;

    /** The stage. */
    private int stage;

    /** The last coordinate. */
    public static Coordinate lastCoord;

    /**
     * Instantiates a new my auto controller.
     *
     * @param car the car
     */
    public MyAutoController(Car car)
    {
        super(car);

        stack = new Stack<Coordinate>();
        mapRecorder = MapRecorder.getMapRecorder();
        moveCoords = new MoveCoords();
        pathOptimizer = new AStarPathOptimizer();

        if (Simulation.toConserve() == StrategyMode.HEALTH) {
            pathOptimizer.update_variation("health");
        }
    }

    /**
     * {@inheritDoc} The update() is responsible for continuously reading in map Recoreder information and updating the
     * path.
     * 
     * @see controller.CarController#update()
     */
    @Override
    public void update()
    {

        strategies = StrategyFactory.getInstance().getCompositeStrategy(stage, Simulation.toConserve());

        mapRecorder.recordMap(super.getView());

        int count = stack.size();

        strategies.action(stack, this);
        if (count < stack.size()) {
            lastCoord = new Coordinate(stack.peek().x, stack.peek().y);
        }

        if (super.numParcelsFound() >= super.numParcels() && stage == 1) {
            stage = 2;
            stack.clear();
            strategies = StrategyFactory.getInstance().getCompositeStrategy(stage, Simulation.toConserve());
            strategies.action(stack, this);

        } else if (stack.isEmpty()) {
            stage = 1;
            stack.clear();
            strategies = StrategyFactory.getInstance().getCompositeStrategy(stage, Simulation.toConserve());
            strategies.action(stack, this);
            return;
        }

        System.out.println(stack.peek().toString() + " " + stack.size());

        stack.remove(new Coordinate(super.getPosition()));

        pathOptimizer.optimizePath(World.getMap(), stack, new Coordinate(super.getPosition()));

        moveCoords.moveToCoords(stack.peek(), this);
    }

}
