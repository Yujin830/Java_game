import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartPanel extends JPanel {
	
	static String _name = null;
	static JLabel lblTitle,lblimage,lblHintTitle,lblHint1,lblHint2,lblHint3,lblHint4,imgstart;
	static JTextField txtName;
	static JButton btnName,btnhelp,btnreturn;
	static ImageIcon iconStart;
	static JPanel help;
	private ButtonListener btnL;
	
	public StartPanel() {
		
		btnL = new ButtonListener();
		
		Font fnt = new Font("Verdana",Font.BOLD,30);
		
		help = new JPanel();
		help.setBounds(140, 140, 820, 600);
		help.setBackground(Color.white);
		help.setLayout(null);
		help.setVisible(false);
		this.add(help);
		
		btnreturn = new JButton(new ImageIcon("./image/return.png"));
		btnreturn.setBounds(720, 500, 50 , 50);
		btnreturn.setVisible(false);
		help.add(btnreturn);
		
		lblHintTitle = new JLabel("HELP");
		lblHintTitle.setBounds(0, 0, 820, 100);
		lblHintTitle.setOpaque(true);
		lblHintTitle.setBackground(Color.cyan);
		lblHintTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblHintTitle.setFont(fnt);
		lblHintTitle.setVisible(false);
		help.add(lblHintTitle);
		
		lblHint1 = new JLabel("If the happiness is over 100,Game Over");
		lblHint1.setBounds(0, 100, 820, 100);
		lblHint1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint1.setFont(fnt);
		lblHint1.setVisible(false);
		help.add(lblHint1);
		
		lblHint2 = new JLabel("Try to live a different life by raising your level.");
		lblHint2.setBounds(0, 200, 820, 100);
		lblHint2.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint2.setFont(fnt);
		lblHint2.setVisible(false);
		help.add(lblHint2);
		
     	lblHint3 = new JLabel("Aim for the jackpot with Lotto.");
		lblHint3.setBounds(0, 300, 820, 100);
		lblHint3.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint3.setFont(fnt);
		lblHint3.setVisible(false);
		help.add(lblHint3);
		
		lblHint4 = new JLabel("Enjoy small happiness through donations.");
		lblHint4.setBounds(0, 400, 820, 100);
		lblHint4.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint4.setFont(fnt);
		lblHint4.setVisible(false);
		help.add(lblHint4);
		
		iconStart = new ImageIcon("./image/start1.jpg");
		
		lblTitle = new JLabel(new ImageIcon("./image/Happiness_img.png"));
		lblTitle.setBounds(210,240,700,153);
		lblTitle.setFont(new Font("Verdana",Font.BOLD,80));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle);
		
		
		txtName = new JTextField("Insert name");
		txtName.setBounds(190,415,720,57);
		txtName.setForeground(Color.gray);
		txtName.addMouseListener(btnL);
		txtName.setFont(new Font("Verdana",Font.BOLD,25));
		this.add(txtName);
		
		
		btnName = new JButton(new ImageIcon("./image/start_button2.png"));
		btnName.setBounds(430,480,300,97);
		this.add(btnName);
		
		btnhelp = new JButton(new ImageIcon("./image/hint2.png"));
		btnhelp.setBounds(920, 420, 50, 50);
		this.add(btnhelp);

		
		lblimage = new JLabel(iconStart);
		lblimage.setBounds(0,0,1100,900);
		this.add(lblimage);
		
		

	}
	private static void btnDesign(JButton btnreturn2) {
		// TODO Auto-generated method stub
		
	}
	private class ButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();
			if(obj == txtName) {
				txtName.setText("");
				txtName.setForeground(Color.black);
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
}