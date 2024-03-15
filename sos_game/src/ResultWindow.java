import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class ResultWindow extends JFrame {
    public ResultWindow(String result){
        //setLayout(new BorderLayout());
        Font f = new Font("serif", Font.BOLD, 20);
        JLabel printResult = new JLabel(result);
        printResult.setFont(f);
        printResult.setHorizontalAlignment(JLabel.CENTER); // Center horizontally
        printResult.setVerticalAlignment(JLabel.CENTER);
        JPanel centerp = new JPanel(new BorderLayout());
        centerp.add(printResult);
        add(centerp,BorderLayout.CENTER);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(520,420);
        setTitle("SOS");
        setResizable(false);
        setVisible(true);
    }

}
