import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Comp_playerTest {
    @Test
    public void validRandomMoveByComputer(){
        int[][] grid = new int[8][8];
        var compPlayer = new Comp_player("red","computer",grid);
        assertNotEquals(null,compPlayer.makeMove());
    }
    @Test
    public void ValidWinningMoveByComputer(){
        int[][] grid = new int[8][8];
        grid[1][1]=1; //S move
        grid[1][2]=2;  //O move
        var compPlayer = new Comp_player("red","computer",grid);
        assertEquals(new Move(1,3,1), compPlayer.makeMove());
    }
    @Test
    public void ValidDefensiveMoveByComputer(){
        int[][] grid = new int[8][8];
        grid[1][1]=1; //S move
        var compPlayer = new Comp_player("red","computer",grid);
        assertNotEquals(new Move(1,2,2), compPlayer.makeMove()); //will not make a O move near S
    }
    @Test
    public void validRandomMoveByComputerInGeneral(){
        int[][] grid = new int[8][8];
        var board = new Board();
        board.setGameMode("general");
        var compPlayer = new Comp_player("red","computer",grid);
        board.setPlayer_red(compPlayer);
        assertNotEquals(null,compPlayer.makeMove());
    }
    @Test
    public void ValidWinningMoveByComputerInGeneral(){
        int[][] grid = new int[8][8];
        grid[1][1]=1; //S move
        grid[1][2]=2;  //O move
        var board = new Board();
        board.setGameMode("general");
        var compPlayer = new Comp_player("red","computer",grid);
        board.setPlayer_red(compPlayer);
        assertEquals(new Move(1,3,1), compPlayer.makeMove());
    }
    @Test
    public void ValidDefensiveMoveByComputerInGeneral(){
        int[][] grid = new int[8][8];
        grid[1][1]=1; //S move
        var board = new Board();
        board.setGameMode("general");
        var compPlayer = new Comp_player("red","computer",grid);
        board.setPlayer_red(compPlayer);
        assertNotEquals(new Move(1,2,2), compPlayer.makeMove()); //will not make a O move near S
    }

}