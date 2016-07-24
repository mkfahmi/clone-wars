// Classe usine qui représente un bâtiment capable de construire des unités.
public class Usine 
{
    private EnemyFactory enemyFactory;// Attribut contenant la enemyFactory simple.
     
    // Le constructeur permet de sélectionner la enemyFactory à utiliser.
    public Usine() {
        this.enemyFactory = new EnemyFactory();
    }
     
    // Méthode qui permet de construire l'ensemble des unités.
    public Character buildUnit(EnemyTypes type) {
        Character unit = this.enemyFactory.createUnit(type);
        return unit;
    }
}
