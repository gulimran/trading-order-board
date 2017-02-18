Trading Order Board – Solution 

Requirements
A trading marketplace has a new requirement to display to its users the demand for silver bars on the market.  They would like to have a Trading Order Board' that can provide them with the order summary with orders with the same price merged together and sorted lowest price first for sell orders and highest price first for buy orders.

How to build
Use maven command line to run tests and package this application
	mvn clean install

Technologies
The following technologies used for the ‘Live Order Board’ application:
•	Dependency injection – Spring 4 with JSR-330 annotations
•	Concurrent Hash Map – In-memory database
•	Lombok – Assist with boilerplate code in domain objects
•	Junit – Unit testing
•	Spring test – Integration testing
•	Cucumber – Acceptance testing using Spring and Java
•	Mockito – Mocking dependencies 
•	Hamcrest – For assertion of test results

Assumptions
•	To get order summary, user must provide the type of order, i.e. SELL or BUY.
•	Validation on Order quantity and price are not required at this time.
•	Database validation for non-existing or duplicate orders is not required at this time.
•	Java doc is only required for client facing interface

Scenarios
See the following feature file:
trading-order-board/src/test/resources/
imran.trading.order.board.acceptance.trading-order-board.feature

Scenarios also listed on next page …

Scenario: Register a sell order consists of user id, order quantity and price per kg.
    Given a sell order with:
      User id	Quantity	Price	Type
      user1	3.5	306	SELL
    
    When the order is registered in the trading order board service
    Then the trading order board should provide the following summary for "sell" orders:
      Quantity	 Price
      3.5	 306

Scenario: Cancel a registered order with the given order id.
    Given a buy order with:
      User id	Quantity	Price	Type
      user2  	1.2	310	BUY

    When the order is registered in the trading order board service
    And when I cancel this order
    Then the trading order board should provide blank summary for "buy" orders

Scenario: The sell orders for the same price should be merged together (even when they are from different users). Also the sell orders on trading order board are ordered with lowest prices first.
    Given multiple sell orders with:
      User id	Quantity	Price	Type
      user1	3.5	306	SELL
      user2	1.2	310	SELL
      user3	1.5	307	SELL
      user4	2.0	306	SELL

    When these order are registered in the trading order board service
    Then the trading order board should provide the following summary for "sell" orders:
      Quantity	 Price
      5.5	 306
      1.5	 307
      1.2	 310

    And sell orders are ordered with lowest price first

Scenario: The buy orders for the same price should be merged together (even when they are from different users).  Also the sell orders on live order board are ordered with highest prices first.
    Given multiple buy orders with:
      User id	Quantity	Price	Type
      user1	3.5	306	BUY  
      user2	1.2	310	BUY  
      user3	1.5	307	BUY  
      user4	2.0	306	BUY  

    When these order are registered in the trading order board service
    Then the trading order board should provide the following summary for "buy" orders:
      Quantity	 Price
      1.2	 310
      1.5	 307
      5.5	 306

    And buy orders are ordered with highest price first

 
