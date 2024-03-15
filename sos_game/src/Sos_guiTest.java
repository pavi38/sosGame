import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sos_guiTest {
    private Board board;
    @Before
    public void setUp() throws Exception {
        board = new Board();
    }
    @Test
    void testEmptyBoard() {
        new Sos_gui(board,8);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testNonEmptyBoard() {
        board.makeMove(0, 0);
        board.makeMove(1, 1);
        new Sos_gui(board,89);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}