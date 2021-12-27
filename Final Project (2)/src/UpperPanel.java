import javax.swing.*;
import java.awt.*;

public class UpperPanel extends JPanel {
		
		static JLabel MONEY;
		static JLabel LEVEL;
		static JLabel HAPPINESS;
		
	
	
		public UpperPanel() {
			setBackground(Color.white);
			setLayout(null);
			
			Font fnt = new Font("VERDANA",Font.BOLD,25);
			
			MONEY = new JLabel("MONEY = 0");
			MONEY.setBounds(80, 20, 300, 50);
			MONEY.setFont(fnt);
			add(MONEY);
			
			LEVEL = new JLabel("<LEVEL 1>");
			LEVEL.setBounds(475,20,250,50);
			LEVEL.setFont(fnt);
			add(LEVEL);
			
			HAPPINESS = new JLabel("HAPPINESS = 0");
			HAPPINESS.setBounds(800,20,300,50);
			HAPPINESS.setFont(fnt);
			add(HAPPINESS);
			
		}
}
