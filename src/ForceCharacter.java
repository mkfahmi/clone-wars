// Personnages possédant la force
abstract class ForceCharacter extends Character {

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

}