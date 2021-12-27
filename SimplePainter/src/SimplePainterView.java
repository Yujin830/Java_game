import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class SimplePainterView extends JPanel {
	
	private DrawController drawController; // DrawController class와 호환되는 객체 선언
	private JPanel menuP, optionP, messageP; // 각 패널 선언
	public JButton[] btnMenuArray; // 매뉴버튼 배열로 선언
	private JTextField txtSize; // 크기 입력하는 텍스트필드
	private JButton btnColorChooser; // 색 선택하는 버튼
	static JCheckBox chkFill; // 체크박스
	public JLabel lbldraw, lblsize, lblcolor, lbllocation; // message패널에 표시될 문구 라벨로 선언
	public int flag; // 첫 시작인지 알기 위한 변수 하나 선언
	BufferedImage bi = new BufferedImage(160, 160, BufferedImage.TYPE_INT_RGB );
	
	public SimplePainterView() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(820,830));
		setLayout(null);
		
		drawController = new DrawController(this );
		drawController.setBounds(10, 10, 800, 600);
		drawController.setBorder(new TitledBorder(new LineBorder(Color.pink,2),"DRAW"));
		// 패널을 둘러싸는 선의 색상과 굵기, 이름 정함
		add(drawController);
		
		menuP = new JPanel();
		menuP.setBounds(10, 610, 300, 200);
		menuP.setBackground(Color.white);
		menuP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"MENU"));
		menuP.setLayout(new GridLayout(2,4)); // 메뉴패널을 세로2칸 가로4칸으로 나눔
		add(menuP);
		
		optionP = new JPanel();
		optionP.setBounds(310,610,200,200);
		optionP.setBackground(Color.white);
		optionP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"OPTION"));
		optionP.setLayout(new GridLayout(3,1));
		add(optionP);
		
		messageP = new JPanel();
		messageP.setBounds(510,610,300,200);
		messageP.setBackground(Color.white);
		messageP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"MESSAGE"));
		messageP.setLayout(new GridLayout(4,1));
		add(messageP);
		
		Font fnt = new Font("Verdana",Font.BOLD,12); // 많이 사용하는 폰트 변수 fnt로 설정해 사용하기 쉽게 함
		
		lbldraw = new JLabel(Constants.MESSAGE[0]); // Constants클래스의 MESSAGE함수에 있는 string으로 설정
		lbldraw.setVisible(false);
		lbldraw.setFont(fnt);
		messageP.add(lbldraw);
		
		lblsize = new JLabel(Constants.MESSAGE[1]);
		lblsize.setVisible(false);
		lblsize.setFont(fnt);
		messageP.add(lblsize);
		
		lblcolor = new JLabel(Constants.MESSAGE[2]);
		lblcolor.setVisible(false);
		lblcolor.setFont(fnt);
		messageP.add(lblcolor);
		
		lbllocation = new JLabel(Constants.MESSAGE[3]);
		lbllocation.setVisible(false);
		lbllocation.setFont(fnt);
		messageP.add(lbllocation);
		
		btnMenuArray = new JButton[8]; // 배열로 만든 메뉴버튼 선언(버튼 추가)
		for(int i=0; i<8; i++) {
			btnMenuArray[i] = new JButton(Constants.MENU[i]);
			btnMenuArray[i].setBackground(Constants.HOVERING[0]);
			btnMenuArray[i].setForeground(Constants.HOVERING[1]);
			btnMenuArray[i].setFont(new Font("Verdana",Font.ITALIC,13));
			btnMenuArray[i].addMouseListener(new HoveringListener()); // 마우스가 버튼에 겹쳐졌을 경우 변화를 나타낼 mouseListener 호출
			btnMenuArray[i].addActionListener(new MenuListener()); // 버튼이 눌렀을 경우 변화를 나타낼 ActionListener 호출
			menuP.add(btnMenuArray[i]);
		} // for
		btnMenuArray[5].setEnabled(false);
		btnMenuArray[6].setEnabled(false);
		btnMenuArray[7].setEnabled(false); // 첫 시작에는 아무것도 그려져 있지 않기에 배열 5, 6, 7자리에 있는 버튼은 사용 불가능 처리
		
		btnColorChooser = new JButton("COLOR CHOOSER");
		btnColorChooser.setBackground(Constants.HOVERING[2]);
		btnColorChooser.setForeground(Constants.HOVERING[3]);
		btnColorChooser.setFont(new Font("Verdana",Font.ITALIC,16));
		btnColorChooser.addActionListener(new MenuListener());
		btnColorChooser.setVisible(false);
		optionP.add(btnColorChooser);
		
		txtSize = new JTextField();
		txtSize.setFont(new Font("Verdana",Font.BOLD,16));
		txtSize.setVisible(false);
		txtSize.setHorizontalAlignment(JTextField.CENTER); // 입력 글자 가운데정렬
		optionP.add(txtSize);
		
		chkFill = new JCheckBox("FILL");
		chkFill.setBackground(Color.white);
		chkFill.setFont(new Font("Verdana",Font.BOLD,16));
		chkFill.setVisible(false);
		optionP.add(chkFill);
		
		flag = 0; // int형 변수 0으로 초기화
	} // SimplePainterview()
	
	public void setTxtSize(int size) { txtSize.setText(Integer.toString(size)); } // 문자열인 size를 int로 바꿔 저장해주기
	public int getTxtSize() { return Integer.parseInt(txtSize.getText());} // 저장되어 있는 size 블러오기
	
	public boolean getChkFill() { return chkFill.isSelected(); } // 체크박스가 선택되었는지 여부 return
	
	private class HoveringListener implements MouseListener{ // 마우스에 대한 리스너 클래스

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton btnMenu = (JButton)arg0.getSource();
			btnMenu.setBackground(Constants.HOVERING[2]);
			btnMenu.setForeground(Constants.HOVERING[3]);
		} // 마우스가 겹쳐졌을 경우 색 변화

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton btnMenu = (JButton)arg0.getSource();
			btnMenu.setBackground(Constants.HOVERING[0]);
			btnMenu.setForeground(Constants.HOVERING[1]);
		} // 겹쳐져있던 마우스가 나갔을 경우 색 변화

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	} // HoveringListener class
	
	public void message(int i) {
		lbldraw.setText(Constants.MESSAGE[0] + Constants.MENU[i]);
		lbldraw.setVisible(true);
		lblsize.setText(Constants.MESSAGE[1]);
		lblsize.setVisible(true);
		lblcolor.setText(Constants.MESSAGE[2]);
		lblcolor.setVisible(true);
		lbllocation.setText(Constants.MESSAGE[3]);
		lbllocation.setVisible(true);
	} // message패널에 글씨 띄우는 것을 따로 함수로 만듬
	private class MenuListener implements ActionListener{ // 버튼을 눌렀을 경우 actionlistener

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object obj = arg0.getSource();
			
			txtSize.setVisible(true);
			
			for(int i=0;i<8;i++) {
				if(obj == btnMenuArray[i]) { // 메뉴 버튼을 눌렀을 경우
					btnColorChooser.setVisible(true);
					drawController.setDrawsize(i, flag); // DrawController에 있는 함수 호출(입력된 size 유지할건지에 대한)
					message(i);	// 선택한 버튼에 대해 message패널에 출력				
					if(i==Constants.DOT) {
						chkFill.setVisible(false);
					} // 선택된 버튼이 DOT일 경우
					if(i==Constants.LINE) {
						chkFill.setVisible(false);
						lblsize.setText(Constants.MESSAGE[4]);
						lbllocation.setText(Constants.MESSAGE[5]);
					} // 선택된 버튼이 LINE일 경우
					if(i==Constants.RECT||i==Constants.OVAL) {
						chkFill.setVisible(true);
					} // 선택된 버튼이 RECT 혹은 OVAL 일 경우
					if(i==Constants.PEN) {
						chkFill.setVisible(false);
						lblsize.setText(Constants.MESSAGE[6]);
						lbllocation.setText("NO LOCATION PROVIDED");
					} // 선택된 버튼이 PEN일 경우 위치는 제공하지 않는다는 문자열 출력
					if(i==Constants.RE) {
						btnColorChooser.setVisible(false);
						chkFill.setVisible(false);
						txtSize.setVisible(false);
						btnMenuArray[5].setEnabled(false);
						btnMenuArray[6].setEnabled(false);
						message(i);
						drawController.savedList.clear(); // DrawController에 선언한 배열을 초기화 하기
						drawController.vStart.clear(); // DrawController에 선언한 벡터를 초기화 하기
						repaint(); // 전부 지운 화면 바로 나타내기
					} // 선택된 버튼이 CLEAR인 경우
					if(i==Constants.UNDO) {
						btnMenuArray[6].setEnabled(true);
						btnColorChooser.setVisible(false);
						chkFill.setVisible(false);
						txtSize.setVisible(false);
						message(i);
						if(drawController.arraysize(drawController.savedList) == 1) {
							btnMenuArray[5].setEnabled(false);
							btnMenuArray[7].setEnabled(false);
							drawController.vStart.clear();
						} // 계속 UNDO버튼을 눌러 배열에 남는것이 없을경우 UNDO버튼과 CLEAR버튼 사용 불가능 처리
						drawController.removedList.add(drawController.savedList.get(drawController.arraysize(drawController.savedList)-1));
						// removedList에 undo했을때의 데이터 저장
						drawController.savedList.remove(drawController.arraysize(drawController.savedList)-1); // 취소버튼 누를때마다 저장되어 있는 배열에서 하나하나 삭제
						// savedList에서는 삭제
						repaint(); // 취소한 화면 바로 나타내기
					} // 선택된 버튼이 UNDO일 경우
					if(i==Constants.REDO) {
						int count = drawController.arraysize(drawController.removedList) - 1;
						// 리스트의 마지막 index 저장
						btnMenuArray[5].setEnabled(true);
						btnColorChooser.setVisible(false);
						chkFill.setVisible(false);
						txtSize.setVisible(false);
						message(i);
						drawController.savedList.add(drawController.removedList.get(count));
						// 다시 savedList로 옮겨 저장
						drawController.removedList.remove(count);
						// removedList에 저장해둔것은 삭제
						repaint(); // 즉시 반영해서 나타내기
						if(count==0) {
							btnMenuArray[6].setEnabled(false);
						} // 만약 undo한것이 없을경우 redo버튼 비활성화
					} // 선택된 버튼이 REDO일 경우
					break; // 버튼 하나만 클릭하고 for문 빠져나감
				} // if
			} // for
			if(obj==btnColorChooser) {
				Color c = JColorChooser.showDialog(btnColorChooser, "COLOR CHOOSER", Color.black); // 색 선택하는 버튼 만들기
				drawController.setSelectedColor(c); // DrawController의 함수 호출
				btnColorChooser.setBackground(c); // 선택한 색 대로 ColorChooser 버튼 배경 색 바뀜
				if(c.getRed()==255||c.getBlue()==255||c.getGreen()==255) {
					btnColorChooser.setForeground(Constants.HOVERING[1]);
				}
				else {
					btnColorChooser.setForeground(Constants.HOVERING[3]);
				} // 배경색이 너무 밝으면 글씨가 안보일 수 있으므로 색에 따른 글씨 색도 변경
				lblcolor.setText(Constants.MESSAGE[2] + "R = "+c.getRed()+", G = "+c.getGreen()+", B = "+c.getBlue());
			}	
		} // actionPerformed
		
	} // MenuListener class
	
} // SimplePainterView class
