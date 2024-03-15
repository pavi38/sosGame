import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Comp_player extends Player {
    private static final int THINKING_THRESHOLD = 1_000_000;
    private Set<Move> badMoves;
    private int gameBoard[][];

    public Comp_player(String color, String type) {
        super(color, type);
        badMoves = new HashSet<>();
    }

    public Comp_player(String color, String type, int[][] gameBoard) {
        super(color, type);
        this.gameBoard = gameBoard;
        badMoves = new HashSet<>();
    }

    public Comp_player(String color, int[][] gameBoard) {
        super(color);
        this.gameBoard = gameBoard;
        badMoves = new HashSet<>();
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public int move() {
        return 0;
    }
    public Move makeMove() {
        badMoves.clear();
        findBadMoves();
        Move move;
        if((move = findSOSMakingMove()) != null) {
            System.out.println(move);
            return move;
        }
        else {
            int i = 0;
            while(true) {
                move = makeDefaultRandomMove();
                if(!badMoves.contains(move) || i > THINKING_THRESHOLD) {
                    return move;
                }
                i++;
            }
        }
    }
    private Move findSOSMakingMove(){
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if((gameBoard[i][j]==1 || gameBoard[i][j]==3) && (gameBoard[i][j + 1]==2 || gameBoard[i][j + 1]==4) && gameBoard[i][j+2]==0){
                    System.out.println(1);
                    return new Move(i,j+2,(color == "red") ? 1 : 3);

                }
                if((gameBoard[i][j]== 1 || gameBoard[i][j]== 3) && (gameBoard[i][j + 1] == 0)
                        && (gameBoard[i][j + 2] == 1 || gameBoard[i][j + 2] == 3)) {

                    return new Move(i,j+1,(color == "red") ? 2 : 4);
                }
                if(gameBoard[i][j] == 0 && (gameBoard[i][j + 1] == 2 || gameBoard[i][j + 1] == 4)
                        && (gameBoard[i][j + 2] == 1 || gameBoard[i][j + 2] == 3)) {
                    return new Move(i,j,(color == "red") ? 1 : 3);
                }

            }
        }
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if ((gameBoard[j][i] == 1 || gameBoard[j][i] == 3) && (gameBoard[j + 1][i] == 2 || gameBoard[j + 1][i] == 4)
                        && gameBoard[j + 2][i] == 0) {
                    return new Move(j + 2, i, (color == "red") ? 1 : 3);
                }
                if ((gameBoard[j][i] == 1 || gameBoard[j][i] == 3) && gameBoard[j + 1][i] == 0
                        && (gameBoard[j + 2][i] == 1 || gameBoard[j + 2][i] == 3)) {
                    System.out.println(1);
                    return new Move(j + 1, i, (color == "red") ? 2 : 4);
                }
                if(gameBoard[j][i]==0 && (gameBoard[j + 1][i]==2 || gameBoard[j + 1][i] == 4)
                        && (gameBoard[j + 2][i]==1 || gameBoard[j + 2][i]==3)) {
                    return new Move(j, i, (color == "red") ? 1 : 3);
                }
            }
        }

        return null;
    }
    private void findBadMoves() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if((gameBoard[i][j]==1 || gameBoard[i][j]==3) && gameBoard[i][j + 1]==0
                        && gameBoard[i][j + 2]==0) {
                    badMoves.add(new Move(i, j + 1, (color == "red") ? 2 : 4));
                    badMoves.add(new Move(i, j + 2, (color == "red") ? 1 : 3));
                }

                else if(gameBoard[i][j]==0 && gameBoard[i][j + 1]==0
                        && (gameBoard[i][j + 2]==1 || gameBoard[i][j + 2]==3)) {
                    badMoves.add(new Move(i, j, (color == "red") ? 1 : 3));
                    badMoves.add(new Move(i, j + 1, (color == "red") ? 2 : 4));

                }

                else if(gameBoard[i][j] == 0 && (gameBoard[i][j + 1] == 2 || gameBoard[i][j + 1] == 4)
                        && gameBoard[i][j + 2]==0) {
                    badMoves.add(new Move(i, j, (color == "red") ? 2 : 4));
                    badMoves.add(new Move(i, j + 2, (color == "red") ? 1 : 3));
                }
            }
        }
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard.length - 2; j++) {
                if((gameBoard[j][i]==1 || gameBoard[j][i] == 3)   && gameBoard[j + 1][i]==0
                        && gameBoard[j + 2][i]==0) {
                    badMoves.add(new Move(j + 2, i, (color == "red") ? 1 : 3));
                    badMoves.add(new Move(j + 1, i, (color == "red") ? 2 : 4));
                }

                if(gameBoard[j][i]==0 && gameBoard[j + 1][i]==0
                        && (gameBoard[j + 2][i]==1 || gameBoard[j + 2][i] == 3)) {
                    badMoves.add(new Move(j + 1, i, (color == "red") ? 2 : 4));
                    badMoves.add(new Move(j, i, (color == "red") ? 1 : 3));
                }

                if(gameBoard[j][i]==0 && (gameBoard[j + 1][i]== 2 || gameBoard[j + 1][i] == 4)
                        && gameBoard[j + 2][i]==0) {
                    badMoves.add(new Move(j, i, (color == "red") ? 1 : 3));
                    badMoves.add(new Move(j + 2, i, (color == "red") ? 2 : 4));
                }
            }
        }
    }
    private Move makeDefaultRandomMove() {
        Random random = new Random();

        int row;
        int col;
        int num_choice;
        do {
            row = random.nextInt(gameBoard.length);
            col = random.nextInt(gameBoard.length);
        } while (!(gameBoard[row][col] ==0));
        if (color == "red"){
            num_choice = random.nextBoolean() ? 1 : 2;
        }
        else{
            num_choice = random.nextBoolean() ? 3 : 4;
        }
        return new Move(row, col, num_choice);
    }
}
