import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sos_gui extends JFrame {
    private Board board;
    JButton newGame;
    JButton replay;
    JTextField tf;
    private String mode="simple";
    private String redplayertype = "human";
    private String blueplayertype = "human";
    static int boardSize=8; //board size for the drawboard
    JPanel panel_north;
    JPanel panel_west;
    JPanel panel_east;
    JPanel panel_south;
    static  int cols;
    static int rows;
    static final int xStart=0;
    static final int yStart=0;
    static final int cellSize=30;
    public static final int CELL_PADDING = cellSize / 6;
    public static final int letterSize = cellSize - CELL_PADDING * 2;
    private static boolean activeGameMode = true;

    public Sos_gui() throws HeadlessException {
    }

    public Sos_gui(Board board, int boardSize) {
        this.board = board;
        this.boardSize = boardSize;
        rows=boardSize;
        cols=boardSize;
        setLayout(new BorderLayout());

        //north panel
        panel_north = new JPanel();
        panel_north.setPreferredSize(new Dimension(100, 70));
        add(panel_north,BorderLayout.NORTH);
        gameMode();


        //west panel
        panel_west=new JPanel();
        panel_west.setLayout(new BoxLayout(panel_west,BoxLayout.Y_AXIS));
        panel_west.setPreferredSize(new Dimension(100, 100));
        add(panel_west,BorderLayout.WEST);
        westPanel();

        //east panel
        panel_east=new JPanel();
        panel_east.setLayout(new BoxLayout(panel_east,BoxLayout.Y_AXIS));
        panel_west.setPreferredSize(new Dimension(100, 100));
        add(panel_east,BorderLayout.EAST);
        eastPanel();

        //south panel
        panel_south= new JPanel();
        panel_south.setPreferredSize(new Dimension(100, 70));
        add(panel_south,BorderLayout.SOUTH);
        southPanel();

        //center panel
        GameBoardCanvas canvs = new GameBoardCanvas();
        canvs.setSize(100, 100);
        add(canvs,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(520,420);
        setTitle("SOS");
        setResizable(false);
        setVisible(true);

    }
    public void gameMode(){

        JLabel mode = new JLabel("Game mode:");
        ButtonGroup G1 = new ButtonGroup();
        JRadioButton simple = new JRadioButton("simple", activeGameMode);
        JRadioButton general = new JRadioButton("general", !activeGameMode);
        simple.setText("simple");
        general.setText("general");
        JLabel size = new JLabel("board size");
        tf = new JTextField(Integer.toString(boardSize) ,2);
        panel_north.add(mode);
        panel_north.add(simple);
        panel_north.add(general);
        panel_north.add(size);
        panel_north.add(tf);
        G1.add(simple);
        G1.add(general);
        //Board_size b1 = new Board_size();
        //tf.addActionListener(b1);
        Modehandler h1 = new Modehandler();
        simple.addActionListener(h1);
        general.addActionListener(h1);

    }

    class Modehandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            mode = e.getActionCommand();
            if (mode == "simple"){
                activeGameMode = true;
                System.out.println(activeGameMode);
            }
            else {
                activeGameMode = false;
            }

        }

    }
    public void actionPreformed(ActionListener e){

    }
    private void westPanel(){
        JLabel l1 = new JLabel("Red Player");
        l1.setForeground(Color.red);
        JRadioButton human = new JRadioButton("human");
        JRadioButton comp = new JRadioButton("computer");
        JRadioButton s = new JRadioButton("S",true);
        JRadioButton o = new JRadioButton();
        ButtonGroup g1 = new ButtonGroup();
        ButtonGroup g2 = new ButtonGroup();
        s.setText("S");
        o.setText("O");
        s.setMargin(new Insets(1,20,1,1));
        o.setMargin(new Insets(1,20,1,1));
        panel_west.add(l1);
        panel_west.add(human);

        panel_west.add(s);
        panel_west.add(o);
        panel_west.add(comp);
        g1.add(human);
        g1.add(comp);
        g2.add(s);
        g2.add(o);
        handler h = new handler(1);
        player_type h1 = new player_type(1);
        s.addActionListener(h);
        o.addActionListener(h);
        human.addActionListener(h1);
        comp.addActionListener(h1);
    }

    private void eastPanel(){
        JLabel l1 = new JLabel("Blue Player");
        l1.setForeground(Color.blue);
        JRadioButton human = new JRadioButton("human");
        JRadioButton comp = new JRadioButton("computer");
        JRadioButton s = new JRadioButton("S",true);
        JRadioButton o = new JRadioButton();
        ButtonGroup g2 = new ButtonGroup();
        ButtonGroup g1 = new ButtonGroup();
        s.setMargin(new Insets(1,20,1,1));
        o.setMargin(new Insets(1,20,1,1));
        s.setText("S");
        o.setText("O");

        panel_east.add(l1);
        panel_east.add(human);
        panel_east.add(s);
        panel_east.add(o);
        panel_east.add(comp);
        g1.add(human);
        g1.add(comp);
        g2.add(s);
        g2.add(o);
        handler h1 = new handler(2);
        player_type h2 = new player_type(2);
        s.addActionListener(h1);
        o.addActionListener(h1);
        human.addActionListener(h2);
        comp.addActionListener(h2);

    }
    class player_type implements  ActionListener{
        private int num;

        public player_type(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (num == 1){

                redplayertype = e.getActionCommand();
            }
            else {
                blueplayertype = e.getActionCommand();
            }
        }
    }
    class handler implements ActionListener{
        public int num;
        public handler(int player_num){
            num=player_num;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(num==1) {
                board.setMoveChoiceRed(e.getActionCommand().charAt(0));
            }
            else{
                board.setMoveChoiceBlue(e.getActionCommand().charAt(0));
            }
        }

    }
    private void southPanel(){
        JCheckBox c1 = new JCheckBox("RECORD");
        JLabel turn = new JLabel("                               ");

        newGame = new JButton("new game");
        replay = new JButton("replay");
        panel_south.add(c1);
        panel_south.add(turn);
        panel_south.add(newGame);
        panel_south.add(replay);
        NewGame game_selection = new NewGame();
        newGame.addActionListener(game_selection);
        replay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Replay replay1 = new Replay();
                replay1.replayGame();
            }
        });


    }

    class NewGame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==newGame){
                boardSize=Integer.parseInt(tf.getText());


                //new board object created when new game button clicked
                Board board1 = new Board();
                board1.setBoard_size(Integer.parseInt(tf.getText()));
                board1.setGameMode(mode);
                new Sos_gui(board1,boardSize);
                if (redplayertype == "human") {
                    board1.setPlayer_red(new Human_player("red","human"));
                }
                else {
                    board1.setPlayer_red(new Comp_player("red","computer"));
                }
                if (blueplayertype == "human"){
                    board1.setPlayer_blue(new Human_player("blue","human"));
                }
                else{
                    System.out.println(1);
                    board1.setPlayer_blue(new Comp_player("blue","computer"));
                }
                System.out.println(redplayertype);
                System.out.println(blueplayertype);
                if(redplayertype == "computer"){
                    board1.compMove("red");
                }
            }

            //board.setGameMode(e.getActionCommand());

        }

    }
    class GameBoardCanvas extends JPanel {

        GameBoardCanvas(){
            //this.board=board;
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int rowSelected = e.getY() / cellSize;
                    int colSelected = e.getX() / cellSize;
                    board.humanMove(rowSelected, colSelected);
                    repaint();
                }
            });
        }
        public void draw_comp(){
            repaint();
            //System.out.println(123);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //setBackground(Color.CYAN);

            drawGridLines(g);
            drawBoard(g);
        }

        private void drawGridLines(Graphics g) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < rows+1; i++) {
                g.drawLine(xStart, yStart+i*cellSize, xStart+cols*cellSize,yStart+i*cellSize);
            }
            for (int i = 0; i < cols+1; i++) {
                g.drawLine(xStart+i*cellSize,yStart,xStart+i*cellSize,yStart+rows*cellSize);
            }

        }
        private void drawBoard(Graphics g){
            //System.out.println(boardSize);
            Font font = new Font("Serif", Font.BOLD,letterSize);
            g.setFont(font);
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    int x1 = (col) * cellSize+2*CELL_PADDING;
                    int y1 = (row+1) * cellSize-CELL_PADDING;
                    if (board.getCell(row,col) == 1){
                        g.setColor(Color.RED);
                        g.drawString("S",x1,y1);
                        //System.out.println(x1);
                        //System.out.println(y1);

                    }
                    else if (board.getCell(row,col) == 2){
                        g.setColor(Color.RED);
                        g.drawString("O",x1,y1);

                    }
                    else if (board.getCell(row,col) == 3){
                        g.setColor(Color.BLUE);
                        g.drawString("S",x1,y1);

                    }
                    else if (board.getCell(row,col) == 4){
                        g.setColor(Color.BLUE);
                        g.drawString("O",x1,y1);

                    }
                }
            }
        }
    }

        public static void main(String[] args) {

        new Sos_gui(new Board(),8); //create inital borad with size 8*8
    }
}