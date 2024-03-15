import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Recoder {
    public static List<Move> moveList = new ArrayList<Move>();
    private final String x = "ddd";
    private static int movecount = 1;
    private final Path gameDataFilePath = Path.of("game_data_file.txt");
    public Recoder() {
        try {
            Files.deleteIfExists(gameDataFilePath);
            Files.createFile(gameDataFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Move> getMoveList() {
        return moveList;
    }

    public void printToFileGameRules(String gameMode) {
        try {
            Files.write(gameDataFilePath,
                    ("\n" + gameMode+ " Game " + "\n\n").getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void recordMove(int x, int y, int move){
        String color;
        String choice;
        if(move == 1){
            color = "RED";
            choice = "S";
        }
        else if(move == 2){
            color = "RED";
            choice = "O";
        }
        else if(move == 3){
            color = "BLUE";
            choice = "S";
        }
        else{
            color = "BLUE";
            choice = "O";
        }
        moveList.add(new Move(x, y, move));
        try {
            Files.write(gameDataFilePath,
                    ("M" + movecount + " Player " + color + " choice " + choice + " At (" + x + "," + y + ")" + "\n").getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.WRITE);
            movecount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
