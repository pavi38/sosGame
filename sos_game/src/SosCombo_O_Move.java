public class SosCombo_O_Move {
    private int[][]  board_map;
    public SosCombo_O_Move(int[][] board_map){
        this.board_map = board_map;
    }
    public boolean checkHorizontal(int row , int col){
        if(col-1 >= 0 && col+1 < board_map[0].length){ //check if o move at the edge of the baord
            return ((board_map[row][col - 1] == 1 || board_map[row][col - 1] == 3) &&
                    (board_map[row][col + 1] == 1 || board_map[row][col + 1] == 3));
        }
        return false;
    }
    public boolean checkVertical(int row , int col){
        if(row-1 >= 0 && row + 1 < board_map.length){ //check if o move at the edge of the baord
            return ((board_map[row - 1][col] == 1 || board_map[row - 1][col] == 3) &&
                    (board_map[row + 1][col] == 1 || board_map[row + 1][col] == 3));
        }
        return false;
    }
    public boolean check_dig_left(int row , int col){
        if(row-1 >= 0 && row+1 < board_map.length && col-1 >= 0 && col+1 < board_map[0].length){ //check if o move at the edge of the baord
            return ((board_map[row - 1][col - 1] == 1 || board_map[row - 1][col - 1] == 3) &&
                    (board_map[row + 1][col+1] == 1 || board_map[row + 1][col + 1] == 3));
        }
        return false;
    }
    public boolean check_dig_right(int row , int col){
        if(row-1 >= 0 && row+1 < board_map.length && col-1 >= 0 && col+1 < board_map[0].length){ //check if o move at the edge of the baord
            return ((board_map[row - 1][col + 1] == 1 || board_map[row - 1][col + 1] == 3) &&
                    (board_map[row + 1][col - 1] == 1 || board_map[row + 1][col - 1] == 3));
        }
        return false;
    }
}
