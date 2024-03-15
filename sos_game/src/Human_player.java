public class Human_player extends Player{
    public Human_player() {
    }

    public Human_player(String color) {
        super(color);
    }

    public Human_player(String color, String type) {
        super(color, type);
    }

    public Human_player(String color, char choice) {
        super(color, choice);
    }

    @Override
    public int move() {
        if(color == "red" && choice == 'S'){
            return 1;
        }
        else if(color == "red" && choice == 'O'){
            return 2;
        }
        else if(color == "blue" && choice == 'S'){
            return 3;
        }
        else if(color == "blue" && choice == 'O'){
            return 4;
        }
        return 0;
    }

}
