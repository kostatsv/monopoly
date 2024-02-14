package com.formfill.monopoly.game.player;

import com.formfill.monopoly.game.square.PropertySquare;
import com.formfill.monopoly.game.square.Square;
import com.formfill.monopoly.game.utils.GameUtils;

public class Player {

  private final String name;

  private int money = 2000;

  private int position = 0;

  private boolean skipTurn = false;

  private boolean bankrupt = false;

  private int laps = 1;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getMoney() {
    return money;
  }

  public void addMoney(int amount) {
    money += amount;

    if (bankrupt) {
      bankrupt = false;
      System.out.printf("%s is no longer bankrupt!%n", name);
    }
  }

  public void reduceMoney(int amount) {
    money -= amount;
  }

  public void takeTurn(Square[] board) {
    int roll = rollDice();
    System.out.printf("%s rolled %s. ", getName(), roll);

    int previousPosition = position;
    setPosition((position + roll) % board.length); // 39

    System.out.printf("Player is now on %s[ %s ]. ", getPosition(), board[getPosition()].getName());
    board[getPosition()].apply(this);

    if (position < previousPosition) {
      laps++;
      System.out.println(name + " passed Go and collects 200");
      System.out.println(name + " has done " + laps + " laps");
      addMoney(GameUtils.AROUND_BOARD_REWARD);
    }
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  private int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

  public void buy(PropertySquare propertySquare) {
    if (getMoney() >= propertySquare.getCost()) {
      propertySquare.setOwner(this);
      reduceMoney(propertySquare.getCost());
      System.out.println(getName() +
                         " bought " + propertySquare.getName() +
                         " for " + propertySquare.getCost() +
                         ". Player has " + getMoney() + " left");
    }
    else {
      System.out.println(getName() +
                         " has " + getMoney() +
                         " not enough to buy " + propertySquare.getName() +
                         " for " + propertySquare.getCost());
    }
  }

  public void payRentTo(Player owner, int rent) {
    if (getMoney() >= rent) {
      owner.addMoney(rent);
      reduceMoney(rent);
      System.out.println(getName() + " paid " + owner.getName() + " " + rent + " in rent. Money left " + getMoney());
    }
    else {
      System.out.printf("%s only has %s and rent is %s%n", this.getName(), this.getMoney(), rent);
      System.out.println(getName() + " is bankrupt!");
      owner.addMoney(getMoney()); // give whatever the player has left to the owner
      reduceMoney(getMoney()); // set money to 0
      setBankrupt(true);
    }
  }

  public boolean isSkipTurn() {
    return skipTurn;
  }

  public void setSkipTurn(boolean skipTurn) {
    this.skipTurn = skipTurn;
  }

  public boolean isBankrupt() {
    return bankrupt;
  }

  public void setBankrupt(boolean bankrupt) {
    this.bankrupt = bankrupt;
  }

  public int getLaps() {
    return laps;
  }

  public void setLaps(int laps) {
    this.laps = laps;
  }
}
