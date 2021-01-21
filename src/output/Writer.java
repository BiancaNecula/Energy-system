package output;

import entities.Consumer;
import entities.Distributor;
import com.fasterxml.jackson.databind.ObjectMapper;
import input.InputGame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class writes the output in files
 */
public final class Writer {
    /**
     * The file where the data will be written
     */
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * Transforms the output in a map
     *
     * @return An Map<String, ArrayList<Map<String, Object>>> Object
     */
    public Map<String, ArrayList<Map<String, Object>>> writeFile(final InputGame input) {

        Map<String, ArrayList<Map<String, Object>>> output = new LinkedHashMap<>();
        ArrayList<Map<String, Object>> outputArray = new ArrayList<>();
        for (Consumer c : input.getConsumers()) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("id", c.getId());
            temp.put("isBankrupt", c.isBankrupt());
            temp.put("budget", c.getMoney());
            outputArray.add(temp);
        }
        output.put("consumers", outputArray);
        ArrayList<Map<String, Object>> outputArray2 = new ArrayList<>();
        for (Distributor d : input.getDistributors()) {
            Map<String, Object> temp2 = new LinkedHashMap<>();
            ArrayList<Map<String, Object>> outputArrayTemp = new ArrayList<>();
            temp2.put("id", d.getId());
            temp2.put("budget", d.getMoney());
            temp2.put("isBankrupt", d.isBankrupt());
            for (Consumer c : d.getContracts().keySet()) {
                Map<String, Object> temp3 = new LinkedHashMap<>();
                temp3.put("consumerId", c.getId());
                temp3.put("price", d.getContracts().get(c));
                temp3.put("remainedContractMonths", c.getMonthsToPay());
                outputArrayTemp.add(temp3);
            }
            temp2.put("contracts", outputArrayTemp);
            outputArray2.add(temp2);
        }
        output.put("distributors", outputArray2);

        return output;
    }

    /**
     * writes to the file and close it
     * uses ObjectMapper for writing the map
     * @param output map
     */
    public void closeJSON(final Map<String, ArrayList<Map<String, Object>>> output) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(output);
            file.write(jsonResult);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
