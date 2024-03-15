import java.util.List;

public class Replay {
    Board board;
    Sos_gui.GameBoardCanvas canvas;

    public Replay(){
        board = new Board();
        board.setBoard_size(4);
        board.setRecordactive(false);
        Sos_gui gui = new Sos_gui(board,4);
        canvas = gui.new GameBoardCanvas();
    }
    public void replayGame(){
        Recoder recoder = new Recoder();
        List<Move> moveList = recoder.getMoveList();
        System.out.println(moveList.size());
        for(Move move : moveList) {

            System.out.println(1);

            try {
                Thread.sleep(1000);
                board.makeMove(move.x(),move.y(),move.move());
                canvas.draw_comp();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}
