package sample;

import java.util.Random;

public class Room {

    //Le singleton
    private static Room single;

    private int lvl;

    private Room(int lvl) {
        this.lvl = lvl;
    }

    public Character generateEnemy() {

        Usine usine = new Usine();
        Character unit = usine.buildUnit(EnemyTypes.randomEnemy());

        unit.lvl = new Random().nextInt((lvl + 2 - lvl) + 1) + lvl;
        unit.lvlUp(unit.lvl);

        return unit;
    }

    // Méthode d'accès au singleton
    public static Room getInstance(int lvl){
        if(single == null)
            single = new Room(lvl);

        return single;
    }

    // GETTERS
    public int getLvl() {
        return lvl;
    }

    // SETTERS
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

}
