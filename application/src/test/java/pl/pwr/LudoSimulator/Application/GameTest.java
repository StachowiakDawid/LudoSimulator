package pl.pwr.ludoSimulator.application;

import org.junit.jupiter.api.Test;
import java.util.List;
import pl.pwr.ludoSimulator.display.Display;
import pl.pwr.ludoSimulator.logic.BoardInitializer;
import pl.pwr.ludoSimulator.logic.actions.*;
import pl.pwr.ludoSimulator.logic.pawns.ActivePawn;
import pl.pwr.ludoSimulator.logic.Board;
import pl.pwr.ludoSimulator.logic.Dice;
import pl.pwr.ludoSimulator.logic.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class GameTest {
    @Test
    void mainTest() {
        Board board = new BoardInitializer(4).initialize();
        Display display = new Display(board);
        for (Player player : board.getPlayers()) {
            int roll = 6;
            Action action = new TakeOutPawnAction();
            if (action.isPossible(board, player, roll)) {
                action.execute(new ActivePawn(player.getStartPosition()));
            }
        }
        display.display();
        for (int i = 0; i < 7; i++) {
            for (Player player : board.getPlayers()) {
                int roll = Dice.roll();
                roll = 6;
                Action action = new MoveActivePawnAction();
                if (action.isPossible(board, player, 6)) {
                    action.execute(((MoveActivePawnAction) action).getPawns().get(0));
                }
            }
        }
        Action action = new TakeOutPawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(new ActivePawn(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 5);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        action = new MoveEndPawnAction();
        if (action.isPossible(board, board.getPlayer(0), 1)) {
            action.execute(((MoveEndPawnAction) action).getPawns().get(0));
        }
        action = new TakeOutPawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((TakeOutPawnAction) action).getPawns().get(0));
        action = new TakeOutPawnAction();
        action.isPossible(board, board.getPlayer(1), 6);
        action.execute(((TakeOutPawnAction) action).getPawns().get(0));
        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 6);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));

        /*board.killPawnIfPossible((board.getPlayer(0).getActivePawn(0).getPosition()+4)%40);
        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 4);
        action.execute(new ActivePawn(0));*/
        action = new KillPawnAction();
        action.isPossible(board, board.getPlayer(0), 4);
        action.execute(((KillPawnAction) action).getPawns().get(0));

        action = new MoveActivePawnAction();
        action.isPossible(board, board.getPlayer(0), 4);
        action.execute(((MoveActivePawnAction) action).getPawns().get(0));
        display.display();
        assertEquals("\n" +
                "1       O O V      22 \n" +
                "        O X O      2  \n" +
                "        O X O         \n" +
                "        O 2 O         \n" +
                "> O O O O X 1 O O O O \n" +
                "O X 1 X 1   X 3 X X O \n" +
                "O O O O O X O O O O < \n" +
                "        O 4 O         \n" +
                "        O X O         \n" +
                "44      O X O      33 \n" +
                "4       ^ O O      3  \n", display.toString());
    }
}
