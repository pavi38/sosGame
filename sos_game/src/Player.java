public abstract class Player {
    protected String color = "Red";
    protected char choice = 'S';
    protected String P_type ="human";

    public Player() {
    }

    public Player(String color, String type) {
        this.color = color;
        this.P_type = type;
    }

    public Player(String color) {
        this.color = color;
    }

    public Player(String color, char choice) {
        this.color = color;
        this.choice = choice;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public void setChoice(char choice) {
        this.choice = choice;
    }

    public String getColor() {
        return color;
    }

    public String getP_type() {
        return P_type;
    }

    public char getChoice() {
        return choice;
    }

    public abstract int move();

    @Override
    public String toString() {
        return "Player{" +
                "color='" + color + '\'' +
                ", choice='" + choice + '\'' +
                '}';
    }
}
