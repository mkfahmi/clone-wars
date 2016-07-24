package sample;

abstract class Character {

    protected String type;
    protected int hp;
    protected int def;
    protected int power;
    protected int force;
    protected int intelligence;
    protected int lvl;
    protected int xpMax = (int) (4 * Math.pow(1, 3)) / 5;
    protected int hpMax;
    protected int xp = 0;

    public Character() {
        lvl = 1;
    }

    public int physicalAttack(Character enemy) {
        int lost = power - enemy.def;

        if(lost > enemy.hp)
            lost = enemy.hp;

        if(lost > 0) {
            enemy.hp -= lost;
            System.out.println(enemy.type + " a perdu " + lost + " hp.");
        } else {
            System.out.println(enemy.type + " n'a perdu aucun hp.");
        }

        return enemy.hp;
    }

    public void lvlUp() {
        while (xp >= xpMax) {
            this.lvl += 1;
            this.xpMax = (int) (4 * Math.pow(lvl, 3)) / 5;
            this.hpMax *= 1.5;
            this.hp = hpMax;
            this.def *= 1.5;
            this.power *= 1.5;
            this.force *= 1.5;
            this.intelligence *= 1.5;
        }
    }

    public void lvlUp(int newLvl) {

        for (int i = 0; i < newLvl; ++i) {
            this.hpMax *= 1.5;
            this.hp = hpMax;
            this.def *= 1.5;
            this.power *= 1.5;
            this.force *= 1.5;
            this.intelligence *= 1.5;
        }
    }

    public int armedAttack(Character enemy) {
        int lost = intelligence - enemy.def;

        if(lost > enemy.hp)
            lost = enemy.hp;

        if(lost > 0) {
            enemy.hp -= lost;
            System.out.println(enemy.type + " a perdu " + lost + " hp.");
        } else {
            System.out.println(enemy.type + " n'a perdu aucun hp.");
        }

        return enemy.hp;
    }

    public int forceAttack(Character enemy) {
        int lost = (force / 10) * 2;

        if(lost > enemy.hp)
            lost = enemy.hp;

        if(lost > 0) {
            enemy.hp -= lost;
            System.out.println(enemy.type + " a perdu " + lost + " hp.");
        } else {
            System.out.println(enemy.type + " n'a perdu aucun hp.");
        }

        return enemy.hp;
    }

    public String toString() {
        return ("hp : " + hp + "\ndef : " + def + "\npower : " + power + "\nforce : " + force + "\nintelligence : " + intelligence + "\nlvl : " + lvl);
    }
}
