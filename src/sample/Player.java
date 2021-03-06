package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Player extends Character {

    //Le singleton
    private static Player single;

    public Character unit;
    private String name = "";
    private int xp;

    // Calcul xp gagné
	// (((mobXp*mobLvl)/5)*(((2*mobLvl+10)^2.5)/((MobLvl*lvl+10)^2.5))+1)
	// See also:
	// http://bulbapedia.bulbagarden.net/wiki/Experience
	// http://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_effort_value_yield

    // Constructeur en private pour faire le singleton
    private Player() {

    }

	private Player(HeroTypes playerClass) {

        super();
        xp = 0;

		/*switch(playerClass) {
            case JEDI:
            	unit = new Jedi();
            	break;
            case SITH:
            	unit = new Sith();
            	break;
            case BOUNTYHUNTER:
            	unit = new BountyHunter();
            	break;
            case IMPERIALAGENT:
        		unit = new ImperialAgent();
        		break;
            case MERCENARY:
            	unit = new Mercenary();
        		break;
            case JAWA:
            	unit = new Jawa();
        		break;
        }*/

	}

    public void gameName(BufferedReader in) throws IOException {
        // Choix du nom
        while(name == "") {
            System.out.println();
            System.out.println("Quel est ton nom, guerrier ?");
            System.out.print("$> ");
            name = in.readLine().trim();
            System.out.println();
        }
    }

    public void gameType(BufferedReader in) throws IOException {
        // Choix du personnage
        String character;

        while(true) {

            System.out.println("Choisis ta classe :");
            System.out.println("1- Jedi\n2- Sith\n3- Bounty Hunter \n4- Imperial Agent\n5- Mercenary\n6- Jawa");
            System.out.println();
            System.out.print("$> ");
            character = in.readLine();
            System.out.println();

            switch(character) {
                case "1":
                    unit = new Jedi();
                    break;
                case "2" :
                    unit = new Sith();
                    break;
                case "3" :
                    unit = new BountyHunter();
                    break;
                case "4" :
                    unit = new ImperialAgent();
                    break;
                case "5" :
                    unit = new Mercenary();
                    break;
                case "6" :
                    unit = new Jawa();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Sélectionne ton personnage dans la liste fournie.");
            }
            break;
        }

        System.out.println("Bienvenue à toi, " + unit.type + " " + name + ".");
    }

    public void gameAttack(BufferedReader in, Character enemy) throws IOException {
        String attack;

        System.out.println("Tu es face à un " + enemy.type + " de niveau " + enemy.lvl + ".");

        while(enemy.hp > 0 && this.unit.hp > 0) {
            System.out.print("\nÀ l'attaque ! (");

            Method methodsArray[] = unit.getClass().getMethods();

            /*// Liste des méthodes de l'objet unit
            for(int i = 0; i < 4; i++) {
                System.out.println(methodsArray[i]);
            }*/

            System.out.println("1- " + methodsArray[1].getName() + "; 2- " + methodsArray[2].getName() + ")");
            System.out.print("$> ");
            attack = in.readLine();

            switch(attack) {
                case "1":
                    enemy.hp = execAttack(methodsArray[1].getName(), enemy);
                    break;
                case "2":
                    enemy.hp = execAttack(methodsArray[2].getName(), enemy);
                    break;
                case "stat":
                    System.out.println(name + " :\n" + unit + "\n");
                    System.out.println(unit.type + " :\n" + unit);
                    break;
                default:
                    System.out.println("Sélectionne l'attaque à effectuer.");
            }
            enemyAttack(enemy);
        }
        System.out.println("GAME OVER");
    }

    // Exécuter l'attaque choisie
    public int execAttack(String attack, Character enemy) {
        Method method = null;
        Class[] cArg = new Class[1];
        cArg[0] = Character.class;

        try {
            method = unit.getClass().getMethod(attack, cArg);
        } catch (SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        try  {
            enemy.hp = (int)method.invoke(unit, enemy);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if(enemy.hp <= 0) {
            System.out.println("Tu as vaincu " + enemy.type + ".\n");
        }

        return enemy.hp;
    }

    public void enemyAttack(Character enemy) {

        int i = new Random().nextInt(2) + 1;
        System.out.println("i = " + i);

        Method methodsArray[] = enemy.getClass().getMethods();

        /*// Liste des méthodes de l'objet unit
        for(int j = 0; j < 4; j++) {
            System.out.println(methodsArray[j]);
        }*/

        Method method = null;
        Class[] cArg = new Class[1];
        cArg[0] = Character.class;

        try {
            method = enemy.getClass().getMethod(methodsArray[i].getName(), cArg);
        } catch (SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        try  {
            unit.hp = (int)method.invoke(enemy, unit);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if(unit.hp <= 0) {
            System.out.println(enemy.type + " t'a battu.\n");
        }

    }

    // Méthode d'accès au singleton
    public static Player getInstance(){
        if(single == null)
            single = new Player();

        return single;
    }

    public static Player getInstance(HeroTypes playerClass){
        if(single == null)
            single = new Player(playerClass);

        return single;
    }
	
	// SETTERS
	public void setHp(int hp) {
		this.hp = hp;
	}
    public void setName(String name) {
        this.name = name;
    }
	
	// GETTERS
    public String getName() {
        return this.name;
    }
	public int getHp() {
		return this.hp;
	}
	public int getLvl() {
		return this.lvl;
	}
}
