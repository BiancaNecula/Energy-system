import input.InputGame;
import input.InputLoader;
import input.Solver;
import output.Writer;

import java.util.ArrayList;
import java.util.Map;

public class Main_et1 {

    public static void main(String[] args) throws Exception {
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
