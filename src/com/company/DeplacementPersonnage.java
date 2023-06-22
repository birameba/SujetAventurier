package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeplacementPersonnage {
    private static char[][] carte;

    // Lecture de la carte depuis un fichier texte
    private static void lireCarte(String nomFichier) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nomFichier));
        String line;
        int rowCount = 0;

        while ((line = reader.readLine()) != null) {
            char[] row = line.toCharArray();
            if (carte == null) {
                carte = new char[row.length][];
            }
            carte[rowCount] = row;
            rowCount++;
        }

        reader.close();
    }

    // Vérifie si une position est valide sur la carte
    private static boolean estPositionValide(int x, int y) {
        if (x < 0 || y < 0 || x >= carte[0].length || y >= carte.length) {
            return false;
        }
        return carte[y][x] != '#';
    }

    // Effectue les déplacements du personnage sur la carte
    private static int[] deplacerPersonnage(int x, int y, String deplacements) {
        for (char deplacement : deplacements.toCharArray()) {
            int newX = x;
            int newY = y;

            if (deplacement == 'N') {
                newY--;
            } else if (deplacement == 'S') {
                newY++;
            } else if (deplacement == 'E') {
                newX++;
            } else if (deplacement == 'O') {
                newX--;
            }

            if (estPositionValide(newX, newY)) {
                x = newX;
                y = newY;
            }
        }

        return new int[]{x, y};
    }

    // Test du déplacement du personnage
    private static void testDeplacementPersonnage() {
        try {
            lireCarte("C:\\Users\\BIRAME BA\\Desktop\\Aventurier\\src\\com\\company\\carte.txt");
            String positionInitiale = "3,0";
            String deplacements = "SSSSEEEEEENN";
            int[] positionFinale = deplacerPersonnage(Integer.parseInt(positionInitiale.split(",")[0]),
                    Integer.parseInt(positionInitiale.split(",")[1]), deplacements);

            System.out.println("Position finale du personnage : (" + positionFinale[0] + ", " + positionFinale[1] + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testDeplacementPersonnage();
    }
}
