package com.company.entities;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Unit> units;
    private int currentUnitId;

    public Player(String name) {
        this.name = name;
        units = new ArrayList<>();
        currentUnitId = 0;
    }

    public void addUnit(Unit unit) {
        currentUnitId++;
        unit.setId(currentUnitId);

        units.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Unit getUnitById(int id){

        for (int i = 0; i < units.size(); i++) {
            Unit currentUnit = units.get(i);

            if (currentUnit.getId() == id) {
                return currentUnit;
            }
        }

        return null;
    }

    public String getPlayerName() {
        return name;
    }

    public ArrayList<Integer> getAllUnitsIds(){
        ArrayList<Integer> ids = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {
            ids.add(units.get(i).getId());
        }

        return ids;
    }
}
