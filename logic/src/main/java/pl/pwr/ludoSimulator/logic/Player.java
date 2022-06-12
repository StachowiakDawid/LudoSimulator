package pl.pwr.ludoSimulator.logic;

import java.util.ArrayList;
import java.util.List;

public class Player implements Cloneable {
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Object();
        }
    }

    private List<Pawn> basePawns = new ArrayList<>();
    private final List<Pawn> activePawns = new ArrayList<>();
    private final List<Pawn> endPawns = new ArrayList<>();

    public boolean hasEnded() {
        return endPawns.size() == 4;
    }

    public List<Pawn> getEndPawns() {
        return endPawns;
    }

    public List<Pawn> getBasePawns() {
        return basePawns;
    }

    public void setBasePawns(List<Pawn> basePawns) {
        this.basePawns = basePawns;
    }

    public List<Pawn> getActivePawns() {
        return activePawns;
    }

    private static int counter = 0;
    private final int id;
    private final int startPosition;

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void addActivePawn(Pawn pawn) {
        this.activePawns.add(pawn);
    }

    public void addEndPawn(Pawn pawn) {
        this.endPawns.add(pawn);
    }

    public void addBasePawn(Pawn pawn) {
        this.basePawns.add(pawn);
    }

    public void removeActivePawn(Pawn pawn) {
        this.activePawns.remove(pawn);
    }

    public void removeBasePawn() {
        this.basePawns.remove(basePawns.get(0));
    }

    private final int endPosition;

    public int getId() {
        return id;
    }

    public Player() {
        this.id = counter;
        Player.counter++;
        this.startPosition = StartPositions.values()[this.id].getStartPosition();
        this.endPosition = Math.floorMod(startPosition-1, 40);
        this.basePawns.add(new Pawn());
        this.basePawns.add(new Pawn());
        this.basePawns.add(new Pawn());
        this.basePawns.add(new Pawn());
    }
}