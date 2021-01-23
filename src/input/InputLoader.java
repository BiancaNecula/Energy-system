package input;

import entities.*;
import updates.Update;
import updates.UpdateDistributorChanges;
import updates.UpdateFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import updates.UpdateProducerChanges;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * The method reads the database
     * @return an InputGame object
     */
    public void readData() {
        JSONParser jsonParser = new JSONParser();
        long numberOfTurns = 0;
        List<Consumer> consumers = new ArrayList<>();
        List<Distributor> distributors = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        List<List<Consumer>> newConsumers = new ArrayList<>();
        List<List<UpdateDistributorChanges>> distributorChanges = new ArrayList<>();
        List<List<UpdateProducerChanges>> producerChanges = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            numberOfTurns = (long) jsonObject.get("numberOfTurns");
            JSONObject initialData = (JSONObject) jsonObject.get("initialData");
            JSONArray monthlyUpdates = (JSONArray) jsonObject.get("monthlyUpdates");

            JSONArray consumerData = (JSONArray) initialData.get("consumers");
            JSONArray distributorsData = (JSONArray) initialData.get("distributors");
            JSONArray producersData = (JSONArray) initialData.get("producers");


            if (consumerData != null) {
                for (Object c : consumerData) {
                    Entity entity = EntityFactory.createEntity("consumer",
                            (long) ((JSONObject) c).get("id"),
                            (long) ((JSONObject) c).get("initialBudget"),
                            (long) ((JSONObject) c).get("monthlyIncome"));
                    consumers.add((Consumer) entity);
                }
            } else {
                consumers = null;
            }
            if (distributorsData != null) {
                for (Object d : distributorsData) {
                    Entity entity = EntityFactory.createEntity("distributor",
                            (long) ((JSONObject) d).get("id"),
                            (long) ((JSONObject) d).get("contractLength"),
                            (long) ((JSONObject) d).get("initialBudget"),
                            (long) ((JSONObject) d).get("initialInfrastructureCost"),
                            (long) ((JSONObject) d).get("energyNeededKW"),
                            (String) ((JSONObject) d).get("producerStrategy"));
                    distributors.add((Distributor) entity);
                }
            } else {
                distributors = null;
            }
            if (producersData != null) {
                for (Object d : producersData) {
                    Entity entity = EntityFactory.createEntity("producer",
                            (long) ((JSONObject) d).get("id"),
                            (String) ((JSONObject) d).get("energyType"),
                            (long) ((JSONObject) d).get("maxDistributors"),
                            (double) ((JSONObject) d).get("priceKW"),
                            (long) ((JSONObject) d).get("energyPerDistributor"));
                    producers.add((Producer) entity);
                }
            } else {
                producers = null;
            }
            if (monthlyUpdates != null) {
                for (Object obj : monthlyUpdates) {
                    if (((JSONObject) obj).get("newConsumers") != null) {
                        JSONArray newConsumersJSON =
                                (JSONArray) ((JSONObject) obj).get("newConsumers");
                        List<Consumer> tempConsumers = new ArrayList<>();
                        if (newConsumersJSON != null) {
                            for (Object c : newConsumersJSON) {
                                Entity entity = EntityFactory.createEntity("consumer",
                                        (long) ((JSONObject) c).get("id"),
                                        (long) ((JSONObject) c).get("initialBudget"),
                                        (long) ((JSONObject) c).get("monthlyIncome"));
                                tempConsumers.add((Consumer) entity);
                            }
                        } else {
                            tempConsumers = null;
                        }
                        newConsumers.add(tempConsumers);
                    }
                    if (((JSONObject) obj).get("distributorChanges") != null) {
                        JSONArray distributorChangesJSON =
                                (JSONArray) ((JSONObject) obj).get("distributorChanges");
                        List<UpdateDistributorChanges> tempDistributorChanges = new ArrayList<>();
                        if (distributorChangesJSON != null) {
                            for (Object o : distributorChangesJSON) {
                                Update update = UpdateFactory.createUpdate("distributorChanges",
                                        (long) ((JSONObject) o).get("id"),
                                        (long) ((JSONObject) o).get("infrastructureCost"));
                                tempDistributorChanges.add((UpdateDistributorChanges) update);
                            }
                        } else {
                            tempDistributorChanges = null;
                        }
                        distributorChanges.add(tempDistributorChanges);
                    }
                    if (((JSONObject) obj).get("producerChanges") != null) {
                        JSONArray producerChangesJSON =
                                (JSONArray) ((JSONObject) obj).get("producerChanges");
                        List<UpdateProducerChanges> tempProducerChanges = new ArrayList<>();
                        if (producerChangesJSON != null) {
                            for (Object o : producerChangesJSON) {
                                Update update = UpdateFactory.createUpdate("producerChanges",
                                        (long) ((JSONObject) o).get("id"),
                                        (long) ((JSONObject) o).get("energyPerDistributor"));
                                tempProducerChanges.add((UpdateProducerChanges) update);
                            }
                        } else {
                            tempProducerChanges = null;
                        }
                        producerChanges.add(tempProducerChanges);
                    }
                }
            } else {
                monthlyUpdates = null;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        InputGame inputGame = InputGame.getInstanceOfInput();
        inputGame.init(numberOfTurns, consumers, distributors, producers, newConsumers, distributorChanges, producerChanges);
    }
}

