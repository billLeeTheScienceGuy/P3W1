import java.util.List;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the Bucky Flight Path.
 * 
 * @author Bill Lee
 */
public interface IFlightPathFrontend {

	//void start(); // setup initial screen (state 0).

	void createButtons(List<ICity> cities); // create necessary buttons (cities and exit). (State 0).

	void createBucky(ICity origin); // Graphical representation of Bucky appears when the user selects the initial
						// city. (State 1).

	void createPathOptions(ICity origin, ICity destination); // creates the path selection buttons. (State 2).

	void createPaths(List<ICity> cities);// takes a list of the three shortest possible paths, and makes lines
											// connecting the cities in each path. (State 2).

	void moveBucky(); // Moves Bucky from the current location to the destination city. (State 3).

	void changeString(); // changes output string according to the state.

}
