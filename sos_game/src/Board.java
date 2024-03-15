import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Board {
    private int i =0;
    private int[][] grid;
    private String turn = "red";
    private char MoveChoiceRed = 'S';
    private char MoveChoiceBlue = 'S';
    private String GameMode = "simple";
    private int board_size = 8;
    private int red_player_count = 0;
    private int blue_player_count = 0;
    private String winner;
    private boolean recordactive = true;
    Player player_red = new Human_player("red","human");
    Player player_blue = new Human_player("blue","human");
    Sos_gui gui = new Sos_gui();
    Sos_gui.GameBoardCanvas canvas = gui.new GameBoardCanvas();
    Recoder recoder = new Recoder();

    public Board() {
        grid = new int[board_size][board_size];
    }

    public int getCell(int row, int column) {
        //System.out.println(grid[row][column]);
        if (row >= 0 && row < board_size && column >= 0 && column < board_size)

            return grid[row][column];
        else
            return -1;
    }

    public void setPlayer_red(Player player_red) {
        this.player_red = player_red;
    }

    public void setPlayer_blue(Player player_blue) {
        this.player_blue = player_blue;
    }

    public void setMoveChoiceRed(char choice) {
        MoveChoiceRed = choice;
    }

    public void setMoveChoiceBlue(char choice) {
        MoveChoiceBlue = choice;
    }

    public void setGameMode(String mode) {
        GameMode = mode;
        recoder.printToFileGameRules(mode);
    }

    public void setBoard_size(int size) {
            board_size = size;
            grid = new int[size][size];

    }

    public void setRecordactive(boolean recordactive) {
        this.recordactive = recordactive;
    }

    public String getGameMode() {
        return GameMode;
    }

    public String getTurn() {
        return turn;
    }

    public int getBoard_size() {
        if (board_size >= 6) {
            return board_size;
        } else {
            return -1;
        }
    }
    public void compMove(String color){
        Comp_player player = new Comp_player(color,grid);
        Move compmove = player.makeMove();
        makeMove(compmove.x(),compmove.y(),compmove.move());
    }
    public void humanMove(int x, int y){
        int human_move = 0;
        if(turn == "red"){
            player_red.setChoice(MoveChoiceRed);
            human_move = player_red.move();
        }
        else{
            player_blue.setChoice(MoveChoiceBlue);
            human_move = player_blue.move();
        }
        makeMove(x,y,human_move);
    }
    public String getWinner(){
        return winner;
    }
    public void  makeMove(int row, int column){
        makeMove(row, column, 0);
    }
    public void makeMove(int row, int column, int move) {
        if (row >= 0 && row < board_size && column >= 0 && column < board_size
                && grid[row][column] == 0) {
            grid[row][column] = move;
            if(recordactive == true) {
                recoder.recordMove(row, column, move);
            }
            if(GameMode == "simple"){
                 if(SimpleWon(grid[row][column], row, column)){
                     if (turn == "red") {
                         winner = "red";
                         ResultWindow res = new ResultWindow("Red win");
                     }
                     else {
                         winner = "blue";
                         ResultWindow res = new ResultWindow("Blue win");
                     }
                 } else if (isDraw()) {
                     winner ="draw";
                     ResultWindow res = new ResultWindow("Draw");
                 }
                 if(winner != null){
                recoder.printToFileGameRules(winner);
                 }
            } else if (GameMode == "general") {
                //System.out.println(3);
                if(SimpleWon(grid[row][column], row, column)){
                    if(turn == "red"){
                        red_player_count++;
                    }
                    else {
                        blue_player_count++;
                    }
                }
                if (isDraw()) {
                    //System.out.println(2);
                    //System.out.println(red_player_count);
                    //System.out.println(blue_player_count);
                    if (red_player_count > blue_player_count){
                        winner="red";
                        ResultWindow res = new ResultWindow("red win");
                    } else if (blue_player_count > red_player_count) {
                        winner="blue";
                        ResultWindow res = new ResultWindow("blue win");
                    }
                    else if (blue_player_count == red_player_count){
                        //System.out.println(red_player_count);
                        //System.out.println(blue_player_count);
                        winner ="draw";
                        ResultWindow res = new ResultWindow("draw");
                    }
                }
                if (winner !=null) {
                    recoder.printToFileGameRules(winner);
                }
            }
            turn = (turn == "red") ? "blue" : "red";
            //TimeUnit.SECONDS.sleep(1);
            canvas.draw_comp();

        }
        System.out.println(!isDraw());
        if((turn == "blue" && player_blue.getP_type() == "computer") || (turn == "red" && player_red.getP_type()  == "computer") && !(isDraw()) && winner == null) {
            System.out.println(player_blue.getP_type());
            compMove(turn);
        }
    }

    public boolean SimpleWon(int choice,int row, int col) {
        if(choice == 2 || choice == 4){
            SosCombo_O_Move s = new SosCombo_O_Move(grid);
            return (s.checkHorizontal(row, col) || s.checkVertical(row, col) || s.check_dig_left(row, col) || s.check_dig_right(row, col));
        }
        else{
            SosCombo_S_Move sm = new SosCombo_S_Move(grid);
            return (sm.downVertical(row,col) ||sm.topVertical(row,col) || sm.leftHorizontal(row, col) || sm.rightHorizontal(row, col) ||
                    sm.left_top_dig(row, col) || sm.left_bottom_dig(row, col) || sm.right_bottom_dig(row, col) || sm.right_top_dig(row, col));
        }
    }
    private boolean isDraw() {
        //System.out.println(grid.length);
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid.length; ++col) {
                if (grid[row][col] == 0) {
                    return false; // an empty cell found, not draw
                }
            }
        }
        return true;
    }
}
