import java.util.Arrays;

public class TicTacToe {
    private static final int FIELD_LENGTH = 3;
    private final char[][] field = new char[FIELD_LENGTH][FIELD_LENGTH];
    private int move;

    public TicTacToe() {
        newGame();
    }

    void newGame() {
        for (char[] chars : field) {
            Arrays.fill(chars, '-');
        }
        move = 0;
    }

    char[][] getField() {
        char[][] result = new char[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            result[i] = field[i].clone();
        }
        return result;
    }

    String checkGame() {
        char[] array = new char[field.length];
        char[] array1 = new char[field.length];

        for (int j = 0; j < field[0].length; j++) {
            if (check(field[j])) return "" + field[j][0];

            for (int i = 0; i < field.length; i++) {
                array[i] = field[i][j];
            }
            if (check(array)) return "" + array[0];

            array1[j] = field[j][j];
        }
        if (check(array1)) return "" + array1[0];

        for (int i = 0; i < field.length; i++) {
            array[i] = field[i][field.length - i - 1];
        }
        if (check(array)) return "" + array[0];
        if (move == 9) return "D";
        return null;
    }

    private boolean check(char[] chars) {
        return chars[0] == chars[1] && chars[1] == chars[2] && chars[2] != '-';
    }

    String makeMove(int x, int y) {
        if (checkGame() == null) {
            if (field[x - 1][y - 1] == '-') {
                if ((move & 1) == 0) {
                    field[x - 1][y - 1] = 'X';
                } else {
                    field[x - 1][y - 1] = '0';
                }
                move++;

                String gameStatus = checkGame();
                if (gameStatus == null) {
                    return "Move completed";
                } else if (gameStatus.equals("D")) {
                    return "Draw";
                } else {
                    return "Player " + gameStatus + " won";
                }
            } else {
                return "Cell " + x + ", " + y + " is already occupied";
            }
        } else {
            return "Game was ended";
        }
    }
}
