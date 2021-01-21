import input.InputGame;
import input.InputLoader;
import input.Solver;
import output.Writer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        InputLoader inputLoader = new InputLoader(args[0]);
        inputLoader.readData();
        InputGame input = InputGame.getInstanceOfInput();
        Solver solver = new Solver();
        solver.solve(input);
        Writer fileWriter = new Writer(args[1]);
        Map<String, ArrayList<Map<String, Object>>> map = fileWriter.writeFile(input);
        fileWriter.closeJSON(map);
    }
}
