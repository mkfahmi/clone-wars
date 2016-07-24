abstract class Character {

    protected String type;
    protected int hp;
    protected int def;
    protected int power;
    protected int force;
    protected int intelligence;
    protected int lvl;
    private int xpMax;
    private int hpMax;

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

    public void lvlUp(int newLvl) {
        int i = 0;

        while(i < newLvl) {
            this.lvl += 1;
            this.xpMax = (int) (4 * Math.pow(lvl, 3)) / 5;
            this.hpMax *= 1.5;
            this.hp = hpMax;
            this.def *= 1.5;
            this.power *= 1.5;
            this.force *= 1.5;
            this.intelligence *= 1.5;
            i++;
        }
    }

    public String toString() {
    	return ("hp : " + hp + "\ndef : " + def + "\npower : " + power + "\nforce : " + force + "\nintelligence : " + intelligence + "\nlvl : " + lvl);
    }
}
