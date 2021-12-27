import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {
	
	static JLabel endimage,endtext,endteamname;
	static ImageIcon endimg;
	
	public EndPanel() {
		setLayout(null);
		
		endimg = new ImageIcon("./image/end.jpg");
		
		endtext = new JLabel(new ImageIcon("./image/END.png"));
		endtext.setBounds(400,290,350,149);
		this.add(endtext);
		
		endteamname = new JLabel(new ImageIcon("./image/teamname.png"));
		endteamname.setBounds(880,640,200,243);
		this.add(endteamname);
		
		endimage = new JLabel(endimg);
		endimage.setBounds(0,0,1100,900);
		this.add(endimage);
	}
}