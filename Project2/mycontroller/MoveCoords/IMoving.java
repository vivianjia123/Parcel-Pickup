
package mycontroller.MoveCoords;

import controller.CarController;
import utilities.Coordinate;

/**
 * The Class IMoving.
 *
 * @author Group W13-5. Ziqi Jia, Tan Saint, Kevin Liang.
 * @Description: IMoving is an interface that is used to implement different moving method of the car.
 */
public interface IMoving
{

    /**
     * To determine the direction the car is moving, by using the receiving coordinate and the car object.
     *
     * @param cord the coordinate
     * @param car the car object
     */
    public void moveToCoords(Coordinate cord, CarController car);

}
