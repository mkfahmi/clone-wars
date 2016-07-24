import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // Créer fenêtre du jeu

        System.out.println("  ______  __        ______   .__   __.  _______    ____    __    ____  ___      .______          _______.");
        System.out.println(" /      ||  |      /  __  \\  |  \\ |  | |   ____|   \\   \\  /  \\  /   / /   \\     |   _  \\        /       |");
        System.out.println("|  ,----'|  |     |  |  |  | |   \\|  | |  |__       \\   \\/    \\/   / /  ^  \\    |  |_)  |      |   (----`");
        System.out.println("|  |     |  |     |  |  |  | |  . `  | |   __|       \\            / /  /_\\  \\   |      /        \\   \\");
        System.out.println("|  `----.|  `----.|  `--'  | |  |\\   | |  |____       \\    /\\    / /  _____  \\  |  |\\  \\----.----)   |");
        System.out.println(" \\______||_______| \\______/  |__| \\__| |_______|       \\__/  \\__/ /__/     \\__\\ | _| `._____|_______/");
        System.out.println();

        // Menu du jeu
        System.out.println("Menu :");
        System.out.println("1- Nouvelle partie");
        System.out.println("2- Quitter (ou taper exit)");
        System.out.println();

        // Lire le input avec BufferedReader
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        // Boucle du menu
        while(true) {

            System.out.print("Que choisis-tu ? ");
            System.out.println();

            System.out.print("$> ");
            String line = in.readLine();

            // Différents cas du menu
            switch (line) {
                case "1":
                    newGame(in);
                    return;

                case "2" :
                case "exit":
                    System.out.println();
                    System.out.println("À bientôt !");
                    return;
                default:
                    System.out.println("Choisis une des options du menu.");
            }
        }

    }

    private static void newGame(BufferedReader in) throws IOException {

        Player player = Player.getInstance();
        player.gameName(in);
        player.gameType(in);

        // Boucle du jeu
        int roomLvl = 1;

        while(true) {

            Room room = Room.getInstance(roomLvl);

            while(true) {
                Character enemy = room.generateEnemy();
                player.gameAttack(in, enemy);
            }

        }

    }


}