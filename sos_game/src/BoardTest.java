import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
//    private Board board;
//
//    @Before
//    public void setUp() throws Exception {
//        board = new Board();
//    }


    @Test
    public void testInitialGrid() {
        // Test that all cells are initialized to 0
        var board = new Board();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                //System.out.println(board.getCell(row,column));
                assertEquals(0, board.getCell(row, column));
            }
        }
    }
    @Test
    public void testGameModeChoice() {
        // Test setting move choices
        var board = new Board();
        board.setGameMode("General");
        assertEquals("General", board.getGameMode());
    }
    @Test
    public void testDefaultGameMode(){
        var board = new Board();
        assertEquals("simple", board.getGameMode());
    }
    @Test
    public void testValidPLayerOneSMove() {
        var board = new Board();
        board.makeMove(0, 0);
        assertEquals(1, board.getCell(0, 0));
        assertEquals("P2", board.getTurn());
    }
    @Test
    public void testValidPLayerOneOMove() {
        var board = new Board();
        board.setMoveChoiceRed('O');
        board.makeMove(0, 0);
        assertEquals(2, board.getCell(0, 0));
        assertEquals("P2", board.getTurn());
    }
    @Test
    public void testInValidPlayerOneMoveOutsideGrid() {
        var board = new Board();
        board.makeMove(9, 0);
        assertEquals("P1", board.getTurn());
    }
    @Test
    public void testInValidPlayerOneMoveAtOccupiedCell(){
        var board = new Board();
        board.makeMove(0, 0);
        board.makeMove(1,0);
        board.makeMove(1,0);
        assertEquals("P1", board.getTurn());
    }
    @Test
    public void testValidPLayerTwoSMove() {
        var board = new Board();
        board.makeMove(0, 0);
        board.makeMove(1, 0);
        assertEquals(3, board.getCell(1, 0));
        assertEquals("P1", board.getTurn());
    }
    @Test
    public void testValidPLayerTwoOMove() {
        var board = new Board();
        board.setMoveChoiceBlue('O');
        board.makeMove(0, 0);
        board.makeMove(1, 0);
        assertEquals(4, board.getCell(1, 0));
        assertEquals("P1", board.getTurn());
    }
    @Test
    public void testInValidPlayerTwoMoveOutsideGrid() {
        var board = new Board();
        board.makeMove(1, 0);
        board.makeMove(9, 0);
        assertEquals("P2", board.getTurn());
    }
    @Test
    public void testInValidPlayerTwoMoveAtOccupiedCell(){
        var board = new Board();
        board.makeMove(0, 0);
        board.makeMove(0,0);
        assertEquals("P2", board.getTurn());
    }
    @Test
    public void testValidBoardSize(){
        var board = new Board();
        board.setBoard_size(6);
        assertEquals(6 , board.getBoard_size());
    }
    @Test
    public void testInValidBoardSize(){
        var board = new Board();
        board.setBoard_size(5);
        assertEquals(-1 , board.getBoard_size());
    }
    @Test
    public void testBluePlayerWinSimpleGame(){
        var board = new Board();
        board.setBoard_size(4);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 0);
        board.setMoveChoiceBlue('O');
        board.makeMove(0,1);
        board.setMoveChoiceRed('S');
        board.makeMove(1, 2);
        board.setMoveChoiceBlue('S');
        board.makeMove(0, 2);
        assertEquals("blue" , board.getWinner());
    }
    @Test
    public void testRedPlayerWinSimpleGame(){
        var board = new Board();
        board.setBoard_size(4);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 0);
        board.setMoveChoiceBlue('O');
        board.makeMove(0,1);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 2);
        assertEquals("red" , board.getWinner());
    }
    @Test
    public void testSimpleDrawGame(){
        var board = new Board();
        board.setBoard_size(4);
        for (int i = 0; i < 4; i++ ){
            for(int j =0; j < 4; j++){
                board.makeMove(i, j);
            }
        }
        assertEquals("Draw" , board.getWinner());
    }
    @Test
    public void testBluePlayerWinGeneralGame(){
        var board = new Board();
        board.setBoard_size(3);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 0);
        board.setMoveChoiceBlue('O');
        board.makeMove(0,1);
        board.setMoveChoiceRed('S');
        board.makeMove(1, 0);
        board.setMoveChoiceBlue('S');
        board.makeMove(0, 2);
        board.makeMove(1, 1);
        board.makeMove(1, 2);
        board.makeMove(2, 0);
        board.makeMove(2, 1);
        board.makeMove(2, 2);
        assertEquals("blue" , board.getWinner());
    }
    @Test
    public void testRedPlayerWinGeneralGame(){
        var board = new Board();
        board.setGameMode("general");
        board.setBoard_size(3);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 0);
        board.setMoveChoiceBlue('O');
        board.makeMove(0,1);
        board.setMoveChoiceRed('S');
        board.makeMove(0, 2);
        board.setMoveChoiceBlue('S');
        board.makeMove(1, 0);
        board.makeMove(1, 1);
        board.makeMove(1, 2);
        board.makeMove(2, 0);
        board.makeMove(2, 1);
        board.makeMove(2, 2);
        assertEquals("red" , board.getWinner());
    }
    @Test
    public void testGeneralDrawGame(){
        var board = new Board();
        board.setGameMode("general");
        board.setBoard_size(4);
        for (int i = 0; i < 4; i++ ){
            for(int j =0; j < 4; j++){
                board.makeMove(i, j);
            }
        }
        assertEquals("Draw" , board.getWinner());
    }
}

