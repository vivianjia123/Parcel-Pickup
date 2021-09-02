package mycontroller.DrivingStrategies;

import java.util.HashMap;
import java.util.Stack;

import controller.CarController;
import tiles.MapTile;
import tiles.TrapTile;
import utilities.Coordinate;

/**
 * The Class GetParcelStrategy.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: GetParcelStrategy is a basic strategy that is used to collect the nearest parcels.
 */
public class GetParcelStrategy extends GetStrategy
{

    /**
     * {@inheritDoc}
     * 
     * @see mycontroller.DrivingStrategies.IDrivingStrategy#action(java.util.Stack, controller.CarController)
     */
    @Override
    public void action(Stack<Coordinate> stack, CarController car)
    {
        HashMap<Coordinate, MapTile> knownView = car.getView();
        for (Coordinate coord : knownView.keySet()) {
            if (knownView.get(coord).isType(MapTile.Type.TRAP)) {
                TrapTile traptile = (TrapTile) knownView.get(coord);
                if (stack.contains(coord)) {
                    return;
                } else if (traptile.getTrap().equals("parcel")) {
                    stack.add(coord);
                    return;
                }
            }
        }
    }
}
