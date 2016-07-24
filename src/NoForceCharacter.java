// Personnages ne possédant pas la force
abstract class NoForceCharacter extends Character {

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

}