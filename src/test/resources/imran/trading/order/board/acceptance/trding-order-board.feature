@live-order-board
Feature: As a trading marketplace user,
  I want to get the summary of trading orders
  So that I assess the demand for silver bars on the market.

  Background:
    Given the trading order board service is available
    And there are no orders on the trading order board


  Scenario: Register a sell order consists of user id, order quantity and price per kg.
    Given a sell order with:
      | userId | quantity | price | type |
      | user1  | 3.5      | 306   | SELL |
    When the order is registered in the trading order board service
    Then the trading order board should provide the following summary for "sell" orders:
      | quantity | price |
      | 3.5      | 306   |


  Scenario: Cancel a registered order with the given order id.
    Given a buy order with:
      | userId | quantity | price | type |
      | user2  | 1.2      | 310   | BUY  |
    When the order is registered in the trading order board service
    And when I cancel this order
    Then the trading order board should provide blank summary for "buy" orders


  Scenario: The sell orders for the same price should be merged together (even when they are from different users).
            Also the sell orders on live order board are ordered with lowest prices first.
    Given multiple sell orders with:
      | userId | quantity | price | type |
      | user1  | 3.5      | 306   | SELL |
      | user2  | 1.2      | 310   | SELL |
      | user3  | 1.5      | 307   | SELL |
      | user4  | 2.0      | 306   | SELL |
    When these order are registered in the trading order board service
    Then the trading order board should provide the following summary for "sell" orders:
      | quantity | price |
      | 5.5      | 306   |
      | 1.5      | 307   |
      | 1.2      | 310   |
    And sell orders are ordered with lowest price first


  Scenario: The buy orders for the same price should be merged together (even when they are from different users).
            Also the sell orders on live order board are ordered with highest prices first.
    Given multiple buy orders with:
      | userId | quantity | price | type |
      | user1  | 3.5      | 306   | BUY  |
      | user2  | 1.2      | 310   | BUY  |
      | user3  | 1.5      | 307   | BUY  |
      | user4  | 2.0      | 306   | BUY  |
    When these order are registered in the trading order board service
    Then the trading order board should provide the following summary for "buy" orders:
      | quantity | price |
      | 1.2      | 310   |
      | 1.5      | 307   |
      | 5.5      | 306   |
    And buy orders are ordered with highest price first


