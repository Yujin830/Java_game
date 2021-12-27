import javax.swing.*;

public class SimplePainter {

	public static void main(String[] args) { // main함수
		JFrame frame = new JFrame("SIMPLE PAINTER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // 크기 늘리거나 줄이는거 불가능하게
		
		SimplePainterView view = new SimplePainterView();
		frame.getContentPane().add(view);
				
		frame.pack();
		frame.setVisible(true);
	}

}
