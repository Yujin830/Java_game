import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

		static ImageIcon back1,back2,back3,back4,ch1,ch2,ch3,ch4,startBack;
		static JLabel imageLabel,imageLabelc,imageLabel1,imageLabelc1,imageLabel2,imageLabelc2,imageLabel3,imageLabelc3,imageBack,lblmarkname;
		static JPanel test;
	
		public BackgroundPanel() {
			setLayout(null);
			
			back1 = new ImageIcon("./image/1u.jpg");
			back2 = new ImageIcon("./image/3s.jpg");
			back3 = new ImageIcon("./image/3back.jpg");
			back4 = new ImageIcon("./image/castle1.jpg");
			ch1 = new ImageIcon("./image/beggar1.png");
			ch2 = new ImageIcon("./image/worker1.png");
			ch3 = new ImageIcon("./image/rich1.png");
			ch4 = new ImageIcon("./image/king1.png");
			startBack = new ImageIcon("./image/start_graphic.jpg");
			

			
			lblmarkname = new JLabel();
			lblmarkname.setBounds(280,440,350,60);
			lblmarkname.setForeground(Color.white);
			lblmarkname.setFont(new Font("Verdana",Font.BOLD,30));
			this.add(lblmarkname);
			
			imageLabelc = new JLabel(ch1);
			imageLabelc.setBounds(300,480,220,300);
			this.add(imageLabelc);
			
			imageLabel = new JLabel(back1);
			imageLabel.setBounds(0,0,800,800);
			this.add(imageLabel);
			
			imageLabelc1 = new JLabel(ch2);
			imageLabelc1.setBounds(300,480,220,300);
			imageLabelc1.setVisible(false);
			this.add(imageLabelc1);
			
			imageLabel1 = new JLabel(back2);
			imageLabel1.setBounds(0,0,800,800);
			imageLabel1.setVisible(false);
			this.add(imageLabel1);
			
			imageLabelc2 = new JLabel(ch3);
			imageLabelc2.setBounds(300,400,220,300);
			imageLabelc2.setVisible(false);
			this.add(imageLabelc2);
			
			imageLabel2 = new JLabel(back3);
			imageLabel2.setBounds(0,0,800,800);
			imageLabel2.setVisible(false);
			this.add(imageLabel2);
		
			imageLabelc3 = new JLabel(ch4);
			imageLabelc3.setBounds(300,450,220,300);
			imageLabelc3.setVisible(false);
			this.add(imageLabelc3);
			
			imageLabel3 = new JLabel(back4);
			imageLabel3.setBounds(0,0,800,800);
			imageLabel3.setVisible(false);
			this.add(imageLabel3);
		}
}





