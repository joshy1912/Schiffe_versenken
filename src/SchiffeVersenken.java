import java.util.Scanner;


public class SchiffeVersenken {
    private static final int BRETTLAENGE = 5;
    private static final int SCHIFFSLAENGE = 3;
    private static final int ANZAHL_SCHIFFE = 3;
    private static final char SYMBOL_LEER = '-';
    private static final char SYMBOL_SCHIFF = 'S';
    private static final char SYMBOL_GETROFFEN = 'X';
    private static final char SYMBOL_VERFEHLT = 'O';

    private char[][] brett;
    private int versuche;

    public SchiffeVersenken() {
        brett = new char[BRETTLAENGE][BRETTLAENGE];
        versuche = 0;

        for (int i = 0; i < BRETTLAENGE; i++) {
            for (int j = 0; j < BRETTLAENGE; j++) {
                brett[i][j] = SYMBOL_LEER;
            }
        }
    }

    public void schiffePlatzieren() {
        int platzierteSchiffe = 0;

        while (platzierteSchiffe < ANZAHL_SCHIFFE) {
            int x = (int) (Math.random() * BRETTLAENGE);
            int y = (int) (Math.random() * BRETTLAENGE);

            if (brett[x][y] == SYMBOL_LEER) {
                platzierteSchiffe++;
                brett[x][y] = SYMBOL_SCHIFF;
            }
        }
    }

    public void spielen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Bitte geben Sie eine Zeile ein (0-" + (BRETTLAENGE - 1) + "): ");
            int x = scanner.nextInt();

            System.out.print("Bitte geben Sie eine Spalte ein (0-" + (BRETTLAENGE - 1) + "): ");
            int y = scanner.nextInt();

            if (x < 0 || x >= BRETTLAENGE || y < 0 || y >= BRETTLAENGE) {
                System.out.println("Ungültige Koordinaten. Bitte versuchen Sie es erneut.");
                continue;
            }

            if (brett[x][y] == SYMBOL_SCHIFF) {
                System.out.println("Treffer!");
                brett[x][y] = SYMBOL_GETROFFEN;
                versuche++;

                if (versuche == ANZAHL_SCHIFFE) {
                    System.out.println("Herzlichen Glückwunsch! Sie haben alle Schiffe versenkt.");
                    break;
                }
            } else if (brett[x][y] == SYMBOL_LEER || brett[x][y] == SYMBOL_VERFEHLT) {
                System.out.println("Verfehlt!");
                brett[x][y] = SYMBOL_VERFEHLT;
            } else {
                System.out.println("Diese Koordinate wurde bereits ausgewählt. Bitte versuchen Sie es erneut.");
            }

            System.out.println();
            printBrett();
        }

        scanner.close();
    }

    public void printBrett() {
        System.out.println("  0 1 2 3 4");

        for (int i = 0; i < BRETTLAENGE; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < BRETTLAENGE; j++) {
                System.out.print(brett[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        SchiffeVersenken spiel = new SchiffeVersenken();
        spiel.schiffePlatzieren();
        spiel.printBrett();
        spiel.spielen();
    }
}

