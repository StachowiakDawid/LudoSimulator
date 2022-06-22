package pl.pwr.ludoSimulator.display;

import pl.pwr.ludoSimulator.logic.Pawn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivePositions {
    private final List<DisplayPosition> positions = new ArrayList<>();
    private final int X_SEPARATOR = 2;
    private final int Y_SEPARATOR = 1;
    private static final int MIN_POSITION = 1;
    private static final int MAX_POSITION = 5;
    public ActivePositions() {
        this.generatePositions(new DisplayPosition(0,4), new DisplayPosition(10, 0));
        this.generatePositions(new DisplayPosition(12,0), new DisplayPosition(20, 5));
        this.generatePositions(new DisplayPosition(20,6), new DisplayPosition(10, 10));
        this.generatePositions(new DisplayPosition(8,10), new DisplayPosition(0, 5));
    }

    private void generatePositions(DisplayPosition player1, DisplayPosition player2) {
        int x = player1.x();
        int y = player1.y();
        positions.add(new DisplayPosition(x, y));

        List<DisplayPosition> positionsToAdd = new ArrayList<>();
        if (x < player2.x() && y > player2.y()) {

            positionsToAdd =  loop(x, MIN_POSITION, MAX_POSITION-1, 1,
                    y, 0, 0, 0);
            positions.addAll(positionsToAdd);

            positionsToAdd = loop(x, MAX_POSITION-1, MAX_POSITION-1, 0,
                    y, -(MAX_POSITION-1), -MIN_POSITION, 1);
            DisplayPosition cos = positionsToAdd.get(0);
            Collections.reverse(positionsToAdd);
            positions.addAll(positionsToAdd);

            positions.add(new DisplayPosition(x + MAX_POSITION*X_SEPARATOR,
                    y - (MAX_POSITION-1)*Y_SEPARATOR));
        }
        if (x < player2.x() && y < player2.y()) {

            positionsToAdd = loop(x, 0, 0, 0,
                    y, MIN_POSITION, MAX_POSITION-1, 1);
            positions.addAll(positionsToAdd);

            positionsToAdd = loop(x, MIN_POSITION, MAX_POSITION-1, 1,
                    y, MAX_POSITION-1, MAX_POSITION-1, 0);
            positions.addAll(positionsToAdd);

            positions.add(new DisplayPosition(x + (MAX_POSITION-1)*X_SEPARATOR,
                    y + MAX_POSITION*Y_SEPARATOR));
        }
        if (x > player2.x() && y < player2.y()) {

            positionsToAdd = loop(x, -(MAX_POSITION-1), -MIN_POSITION, 1,
                    y, 0, 0, 0);
            Collections.reverse(positionsToAdd);
            positions.addAll(positionsToAdd);

            positionsToAdd = loop(x, -(MAX_POSITION-1), -(MAX_POSITION-1), 0,
                    y, MIN_POSITION, MAX_POSITION-1, 1);
            positions.addAll(positionsToAdd);

            positions.add(new DisplayPosition(x - MAX_POSITION*X_SEPARATOR,
                    y + (MAX_POSITION-1)*Y_SEPARATOR));
        }
        if (x > player2.x() && y > player2.y()) {

            positionsToAdd = loop(x, 0, 0, 0,
                    y, -(MAX_POSITION-1), -MIN_POSITION, 1);
            Collections.reverse(positionsToAdd);
            positions.addAll(positionsToAdd);

            positionsToAdd = loop(x, -(MAX_POSITION-1), -MIN_POSITION, 1,
                    y, -(MAX_POSITION-1), -(MAX_POSITION-1), 0);
            Collections.reverse(positionsToAdd);
            positions.addAll(positionsToAdd);

            positions.add(new DisplayPosition(x - (MAX_POSITION-1)*X_SEPARATOR,
                    y - MAX_POSITION*Y_SEPARATOR));
        }
    }

    private List<DisplayPosition> loop(int x, int minX, int maxX, int xInterval,
                                      int y, int minY, int maxY, int yInterval) {
        List<DisplayPosition> displayPositions = new ArrayList<>();

        for (int i = minX, j = minY; i <= maxX && j <= maxY; i += xInterval, j += yInterval)
            displayPositions.add(new DisplayPosition(x + i*X_SEPARATOR, y + j*Y_SEPARATOR));

        return displayPositions;
    }

    public DisplayPosition get(int index) {
        return this.positions.get(index);
    }

    public DisplayPosition get(Pawn pawn) {
        return this.positions.get(pawn.getPosition());
    }
}