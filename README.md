Object Oriented Programming Course
Project - Second Phase
Energetic System

December 2020

Bianca Necula \
Faculty of Automatic Control and Computer Science \
325CA 

# Info
```bash
git clone https://github.com/BiancaNecula/Energy-system
```
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1 \
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2 \
The project is based on the simulation of an energy system in which we will have different entities with well-defined responsibilities, which will be introduced along the way - producers, distributors, consumers, etc. All these entities are trying to fulfill their duties with the ultimate goal of remaining on the market and avoiding bankruptcy.

# About the code
Project Structure:

* checker/resources - contains the tests in JSON format
* src 
    * input package: contains classes for parsing the input files
        * InputGame: Singleton class for making only one instance for input and contains initial lists of objects 
        * InputLoader: utility class for parsing JSON files with json.simple
        * Solver: utility class for making the necessary changes to solve the problem
    * output package: contains class for writing the final result in JSON files (Writer class)
    * entities: contains 2 entities used in program
        * Consumer: entity with the role of a consumer in an energetic system in a city. He can choose a distributor, pay the services or delay the payment, or goes bankrupt.
        * Distributor: entity with the role of a distributor in the same context. He can make contracts with consumers, pay his taxes, or goes bankrupt.
        * Producer: can make contracts with distributors with some strategies
        * Entity: inherited class by Consumer and Distributor 
        * EntityFactory: Factory class for entities for creating objects without direct access to classes
        * EnergyType: available types of energy
    * updates: contains 1 entity used in program
        * UpdateDistributorChanges: used for the monthly changes to distributors
        * UpdateProducerChanges: used for the monthly changes to producers
        * Update: inherited class by UpdateCostsChanges. It's not used for monthly new consumers because they are directly stored in a list of Consumers
        * UpdateFactory: Factory class for updates for creating objects without direct access to classes  
    * utils: contains comparators
        * ConsumersComparator: comparing consumers after id
        * DistributorsComparator: comparing distributors by contract
    * strategies: 
        * Strategy: interface for Green, Price or Quantity strategies
        * StrategyFactory: Factory class for choosing the right type of energy
        * EnergyContext: util class for Strategy design
    * observer:
        * DistributorObserver: observer that contains a distributor
        * DistributorUpdate: subject that contains all the observers and notify them
        
For solving the problem I followed the logic:
* Consumers:
    * their budget is made of initial budget + monthly income
    * they choose the cheapest contract and pay monthly rate until they finish the contract, and then repeat
    * can delay a single payment by making a 2-month payment with penalty
    * formula: Math.round(Math.floor(1.2*old_rate)) + new_rate
    * if he can't pay this, he goes bankrupt
* Distributors:
    * their budget is made of initial budget + monthly profit
    * they can change the cost of contracts monthly, but the actual contracts remains the same
    * if he can't pay the taxes, he goes bankrupt
    * formula for contract without consumers: infrastructure_cost + production_cost + profit
    * formula for contract with consumers: Math.round(Math.floor(infrastructure_cost / number of consumers)) + production_cost + profit
    * formula for profit: profit = Math.round(Math.floor(0.2 * production_cost))
    * formula for taxes: total_cost = infrastructure_cost + number of consumers * production_cost 
* Producers:
    * they have some types of energy, renewable or not
    * they decide the production cost of distributors
    * a distributor can take energy from more producers 
    * the formula for production cost is: 
        * cost = sum (energy * price per Kw )
        * productionCost = Math.round(Math.floor(cost / 10)) \\
    
It's repeating for <number_of_turns> months.
 
To implement object oriented concepts, I used design patterns like Singleton for input (InputGame), Factory for entities and updates, Observer for changes for distributors and Strategy for choosing the producer. I also included other concepts, OOP or Java related, like polymorphism, comparators, streams, collections.

# Testing

The Main class runs the checker.
Comparing ref files with result files.                   
