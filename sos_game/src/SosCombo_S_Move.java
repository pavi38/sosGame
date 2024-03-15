public class SosCombo_S_Move {
    private int[][]  board_map;
    public SosCombo_S_Move(int[][] board_map){
        this.board_map = board_map;
    }
    public boolean rightHorizontal(int row, int col){
        if(col-2 >= 0){
            return ((board_map[row][col - 2] == 1 || board_map[row][col - 2] == 3) &&
                    (board_map[row][col - 1] == 2 || board_map[row][col - 1] == 4));
        }
        return false;
    }
    public boolean leftHorizontal(int row, int col){
        if(col+2 < board_map[0].length){
            return ((board_map[row][col + 2] == 1 || board_map[row][col + 2] == 3) &&
                    (board_map[row][col + 1] == 2 || board_map[row][col + 1] == 4));
        }
        return false;
    }
    public boolean topVertical(int row, int col){
        if(row - 2 >= 0){
            return ((board_map[row - 2][col] == 1 || board_map[row - 2][col] == 3) &&
                    (board_map[row - 1][col] == 2 || board_map[row - 1][col] == 4));
        }
        return false;
    }
    public boolean downVertical(int row, int col){
        if(row + 2 < board_map.length){
            return ((board_map[row + 2][col] == 1 || board_map[row + 2][col] == 3) &&
                    (board_map[row + 1][col] == 2 || board_map[row + 1][col] == 4));
        }
        return false;
    }
    public boolean left_top_dig(int row, int col){
        if(row - 2 >= 0 && col-2 >= 0){
            return ((board_map[row - 2][col - 2] == 1 || board_map[row - 2][col - 2] == 3) &&
                    (board_map[row - 1][col - 1] == 2 || board_map[row - 1][col - 1] == 4));
        }
        return false;
    }
    public boolean right_top_dig(int row, int col){
        if(row - 2 >= 0 && col+2 < board_map[0].length){
            return ((board_map[row - 2][col + 2] == 1 || board_map[row - 2][col + 2] == 3) &&
                    (board_map[row - 1][col + 1] == 2 || board_map[row - 1][col + 1] == 4));
        }
        return false;
    }
    public boolean right_bottom_dig(int row, int col){
        if(row + 2 < board_map.length && col-2 >= 0 && col + 2 < board_map.length ){
            return ((board_map[row + 2][col + 2] == 1 || board_map[row + 2][col + 2] == 3) &&
                    (board_map[row + 1][col + 1] == 2 || board_map[row + 1][col + 1] == 4));
        }
        return false;
    }
    public boolean left_bottom_dig(int row, int col){
        if(row + 2 < board_map.length && col-2 >= 0){
            return ((board_map[row + 2][col - 2] == 1 || board_map[row + 2][col - 2] == 3) &&
                    (board_map[row + 1][col - 1] == 2 || board_map[row + 1][col - 1] == 4));
        }
        return false;
    }
}
