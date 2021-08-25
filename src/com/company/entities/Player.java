package com.company.entities;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Unit> units;
    private int currentId;

    public Player(String name) {
        this.name = name;
        units = new ArrayList<>();
        currentId = 0;
    }

    public void add(Unit unit) {
        currentId++;
        unit.setId(currentId);

        units.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Unit getUnitById(int id) throws Exception {

        for (int i = 0; i < units.size(); i++) {
            Unit currentUnit = units.get(i);

            if (currentUnit.getId() == id) {
                return currentUnit;
            }
        }

        throw new Exception("user not found");
    }

    public String getName() {
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
