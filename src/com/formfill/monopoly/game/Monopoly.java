package com.formfill.monopoly.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.formfill.monopoly.game.checks.AllLapsCheck;
import com.formfill.monopoly.game.checks.BankruptCheck;
import com.formfill.monopoly.game.player.Player;
import com.formfill.monopoly.game.square.ApartmentSquare;
import com.formfill.monopoly.game.square.GoSquare;
import com.formfill.monopoly.game.square.HotelSquare;
import com.formfill.monopoly.game.square.HouseSquare;
import com.formfill.monopoly.game.square.JailSquare;
import com.formfill.monopoly.game.square.Square;
import com.formfill.monopoly.game.utils.GameUtils;

public class Monopoly {

  private final Square[] board = new Square[39];

  private final List<Player> players = new ArrayList<>();

  private boolean gameOn = true;

  private BankruptCheck allPlayersBankruptCheck;

  public Monopoly() {
    createBoard();
    createChecks();
  }

  private void createChecks() {
    allPlayersBankruptCheck = new BankruptCheck();
    AllLapsCheck allLapsCheck = new AllLapsCheck();

    allPlayersBankruptCheck.setNextCheck(allLapsCheck);
  }

  public List<Player> getPlayers() {
    return players;
  }

  private void createPlayers(int numberOfPlayers) {
    for (int i = 1; i <= numberOfPlayers; i++) {
      players.add(new Player("P" + i));
    }
  }

  private void createBoard() {
    board[0] = new GoSquare();
    board[1] = new HouseSquare("Mediterranean Avenue");
    board[2] = new HouseSquare("Baltic Avenue");
    board[3] = new HouseSquare("Oriental Avenue");
    board[4] = new ApartmentSquare("Vermont Avenue");
    board[5] = new ApartmentSquare("Connecticut Avenue");
    board[6] = new HouseSquare("St. Charles Place");
    board[7] = new HotelSquare("States Avenue");
    board[8] = new HouseSquare("Virginia Avenue");
    board[9] = new JailSquare();
    board[10] = new HotelSquare("St. James Place");
    board[11] = new ApartmentSquare("Tennessee Avenue");
    board[12] = new ApartmentSquare("New York Avenue");
    board[13] = new HouseSquare("Kentucky Avenue");
    board[14] = new HotelSquare("Indiana Avenue");
    board[15] = new ApartmentSquare("Illinois Avenue");
    board[16] = new HouseSquare("Atlantic Avenue");
    board[17] = new ApartmentSquare("Ventnor Avenue");
    board[18] = new ApartmentSquare("Marvin Gardens");
    board[19] = new JailSquare();
    board[20] = new HouseSquare("Pacific Avenue");
    board[21] = new ApartmentSquare("North Carolina Avenue");
    board[22] = new ApartmentSquare("Pennsylvania Avenue");
    board[23] = new HouseSquare("Park Place");
    board[24] = new ApartmentSquare("Boardwalk");
    board[25] = new HouseSquare("Reading Railroad");
    board[26] = new HouseSquare("Pennsylvania Railroad");
    board[27] = new HouseSquare("B. & O. Railroad");
    board[28] = new HouseSquare("Short Line");
    board[29] = new JailSquare();
    board[30] = new HouseSquare("Electric Company");
    board[31] = new HouseSquare("Water Works");
    board[32] = new HotelSquare("Community Chest");
    board[33] = new ApartmentSquare("Community Chest");
    board[34] = new HotelSquare("Community Chest");
    board[35] = new ApartmentSquare("Chance 1");
    board[36] = new ApartmentSquare("Chance 2");
    board[37] = new ApartmentSquare("Chance 3");
    board[38] = new ApartmentSquare("Luxury Tax");
  }

  public void playGame(int numberOfPlayers) {

    if (numberOfPlayers > GameUtils.MAX_PLAYERS) {
      System.out.printf("%s players are just too many! Max allowed is %s.%n", numberOfPlayers, GameUtils.MAX_PLAYERS);
      return;
    }
    else if (numberOfPlayers <= 1) {
      System.out.println("Not enough players! No one wants to play alone");
      return;
    }

    createPlayers(numberOfPlayers);

    while (checkIfGameIsOn()) {

      System.out.println("-".repeat(80));

      for (Player player : players) {

        if (!player.isSkipTurn()) {
          player.takeTurn(board);
/*          System.out.println(player.getName() +
                             " is now on " + player.getPosition() +
                             " - " + board[player.getPosition()].getName());
          board[player.getPosition()].apply(player);*/
        }
        else {
          player.setSkipTurn(false);
          System.out.println(player.getName() + " is in jail. Skips a turn!");
        }
      }
    }
  }

  private boolean checkIfGameIsOn() {
    allPlayersBankruptCheck.check(this);
    return this.gameOn;
  }

  private Player getWinner() {
    return Collections.max(players, Comparator.comparing(Player::getMoney).thenComparing(p -> !p.isBankrupt()));
  }

  public void stopGame(String reason) {
    this.gameOn = false;

    System.out.println("-".repeat(80));
    System.out.println("Game over! " + reason);
    System.out.println("-".repeat(80));
    players.forEach(player -> System.out.printf("%s has %s%s%n",
                                                player.getName(),
                                                player.getMoney(),
                                                player.isBankrupt() ? " / bankrupt" : ""));
    System.out.println("-".repeat(80));
    System.out.println(getWinner().getName() + " won!");
    System.out.println("-".repeat(80));
  }
}
