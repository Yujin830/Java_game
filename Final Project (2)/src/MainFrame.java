import java.awt.*;
import javax.swing.*;


public class MainFrame {
	
	static JPanel mainPanel;
	static UpperPanel Upperr;
	static BackgroundPanel background;
	static LottoPanel Lotto;
	static ButtonPanel bPanel;
	static StartPanel start;
	static EndPanel End;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		JFrame frame = new JFrame("HAPPINESS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		mainPanel.setPreferredSize(new Dimension(1100,900));
		
		
		start = new StartPanel();
		start.setLayout(null);
		start.setBounds(0,0,1100,900);
		start.setVisible(true);
		mainPanel.add(start);
		
		Upperr = new UpperPanel();
		Upperr.setBounds(0,0,1100,100);
		Upperr.setVisible(false);
		mainPanel.add(Upperr);
		
		background = new BackgroundPanel();
		background.setBounds(0,100,800,800);
		background.setVisible(false);
		mainPanel.add(background);
		
		Lotto = new LottoPanel();
		Lotto.setBounds(800,100,300,500);
		Lotto.setVisible(false);
		mainPanel.add(Lotto);
		
		bPanel = new ButtonPanel();
		bPanel.setBounds(800,600,300,300);
		bPanel.setVisible(false);
		mainPanel.add(bPanel);
	
		
		End = new EndPanel();
		End.setBounds(0,0,1100,900);
		End.setVisible(false);
		mainPanel.add(End);
		
		
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		
	}

}
