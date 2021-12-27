import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class ButtonPanel extends JPanel {
		
	    private JLabel imgmoney;
		
		private JButton moneyButton,levelButton,lottoButton,donateButton;
		private GameListener buttonL;
		static int _money,_level,_donate,_happiness,_tHappiness,_IHappiness;
		static int _Num, _Input, _Count, _getM, _getH;
		static int[] _Random = new int[6];
		static String _name = null;
		
		public String ToConvert(int num) {
			DecimalFormat price = new DecimalFormat("#,##0");
			String convStr = price.format(num);
			
			return convStr+"$"; 
		} // 금약을 보기좋게 ',' 찍고 금액 뒤에 달러 표시되는 함수
		
	
		public ButtonPanel() {
			setBackground(Color.white);
			setLayout(null);
			setBorder(BorderFactory.createTitledBorder("BUTTON")); // 버튼 패널이라는 것을 알기 쉽게 선으로 표시
			
			buttonL = new GameListener();
			
			imgmoney = new JLabel(new ImageIcon("./image/mon.png"));
			imgmoney.setBounds(10,15,280,75);
			add(imgmoney); // 돈모양 그림 위에 글씨가 쓰여지게 하기 위해 JLabel로 이미지 만들어줌
			
			moneyButton = new JButton("+10,000 $");
			moneyButton.setBounds(0, 0, 280, 70);
			moneyButton.setFont(new Font("Verdana",Font.ITALIC,20));
			btnDesign(moneyButton);
			moneyButton.addActionListener(buttonL);
			imgmoney.add(moneyButton); // JLabel로 선언한 돈모양 사진 위에 버튼을 add해줌
			
			levelButton = new JButton("-50,000$, +10H",new ImageIcon("./image/level2.jpg")); // 그림과 글씨가 같이 버튼 안에 들어가게끔 선언
			levelButton.setBounds(10,90,280,95);
			levelButton.setFont(new Font("Verdana",Font.ITALIC,15));
			levelButton.setEnabled(false);
			btnDesign(levelButton);
			levelButton.addActionListener(buttonL);
			add(levelButton);
			
			lottoButton = new JButton("-100,000 $",new ImageIcon("./image/lotto.png")); // 그림과 글씨가 같이 버튼 안에 들어가게끔 선언
			lottoButton.setBounds(10,195,135,95);
			lottoButton.setFont(new Font("Verdana",Font.ITALIC,15));
			lottoButton.setEnabled(false);
			btnDesign(lottoButton);
			lottoButton.addActionListener(buttonL);
			add(lottoButton);
			
			donateButton = new JButton("DONATE",new ImageIcon("./image/donate.jpg")); // 그림과 글씨가 같이 버튼 안에 들어가게끔 선언
			donateButton.setBounds(155,195,135,95);
			donateButton.setFont(new Font("Verdana",Font.ITALIC,15));
			donateButton.setEnabled(true);
			btnDesign(donateButton);
			donateButton.addActionListener(buttonL);
			add(donateButton);
			
			// int형 변수 초기화
			_money = 0;
			_level = 1;
			_donate = 0;
			_happiness = 0;
			_Num = _Input = _Count = 0;
			_tHappiness = 0;
			
			// ButtonPanel에 있는 버튼actionlistener을 사용하기 위해 로또패널과 시작패널의 버튼들 불러와서 add해주기
			LottoPanel.btnDonate.addActionListener(buttonL);
			LottoPanel.btnDonateC.addActionListener(buttonL);
			LottoPanel.donateInput.addActionListener(buttonL);
			LottoPanel.btnInput.addActionListener(buttonL);
			LottoPanel.txtInput.addActionListener(buttonL);
			
			StartPanel.btnName.addActionListener(buttonL);
			StartPanel.txtName.addActionListener(buttonL);
			StartPanel.btnhelp.addActionListener(buttonL);
			StartPanel.btnreturn.addActionListener(buttonL);
		}
		
		// 버튼의 테두리 밑 디자인 변경을 위한 함수
		public void btnDesign(JButton btn) {
			btn.setBorderPainted(false); // 테두리 숨김
			btn.setVerticalTextPosition(SwingConstants.BOTTOM); // 글씨는 이미지 밑에 쓰여지게됨
			btn.setHorizontalTextPosition(SwingConstants.CENTER); // 수평적으로는 center
			btn.setOpaque(false); // 배경 투명하게
			btn.setFocusPainted(false); // 버튼을 눌렀을때 표시되는 디자인 숨김
			btn.setContentAreaFilled(false); // 배경 투명하게
		}
		
		//기부버튼 이후에 실행되는 메소드 
		public void afterDonate()
		{
			LottoPanel.lblDonate.setVisible(false);
			LottoPanel.btnDonate.setVisible(false);
			LottoPanel.donateInput.setVisible(false);
			LottoPanel._FDonate.setVisible(false);
			LottoPanel.btnDonateC.setVisible(false);
			LottoPanel.LottoImageLabel.setVisible(true);
			moneyButton.setEnabled(true);
			
			if(_money>=100000) {lottoButton.setEnabled(true);} //기부이후에도 로또의 최소금액 이상의 돈이 있어야 로또버튼 활성화 
			if(_level == 1) //기부버튼 이후이면서 level이 1일때 level 2로 업그레이드 하는 비용에 따라서 level버튼 활성화 및 비활성화 
			{
				if(_money<50000) {levelButton.setEnabled(false);}
				else {levelButton.setEnabled(true);}
			}
			else if(_level == 2) //기부버튼 이후이면서 level이 2일때 level 3로 업그레이드 하는 비용에 따라서 level버튼 활성화 및 비활성화 
			{
				if(_money<100000) {levelButton.setEnabled(false);}
				else {levelButton.setEnabled(true);}
			}
			else if(_level == 3) {levelButton.setEnabled(false);} //기부버튼 이후이면서 level이 max level에 도달했기때문에 level버튼 비활성화 
		}
		
		//재시작 메소
		public void restart()
		{
			//돈,레벨,행복도,지금까지 누적된 행복도를 다 초기값으로 초기화 
			_money = 0;
			UpperPanel.MONEY.setText("MONEY : "+ ToConvert(_money));	
			_level = 1;
			UpperPanel.LEVEL.setText("<LEVEL 1>");
			_happiness = 0;
			_tHappiness = 0;
			UpperPanel.HAPPINESS.setText("HAPPINESS = "+_happiness);
			
			//lottoPanel,버튼,BackgroundPanel을 초기상태로 되돌림 
			LottoPanel.lblDonate.setVisible(false);
			LottoPanel.btnDonate.setVisible(false);
			LottoPanel.donateInput.setVisible(false);
			LottoPanel._FDonate.setVisible(false);
			LottoPanel.btnDonateC.setVisible(false);
			LottoPanel.lblMark.setVisible(false);
			levelButton.setEnabled(false);
			moneyButton.setEnabled(true);
			lottoButton.setEnabled(false);
			BackgroundPanel.imageLabel.setVisible(true);
			BackgroundPanel.imageLabelc.setVisible(true);
			LottoPanel.LottoImageLabel.setVisible(true);
			levelButton.setText("LEVEL UP (-100,000 $, +15H)");
			moneyButton.setText("MONEY +10,000 $");
		}
		
		//각각의 경우에서 나오는 행복도 수치를 합해주는 메소드 
		public void setHappiness(int _IHappiness)
		{
			_tHappiness+=_IHappiness;
			
			//총 행복도가 100을 넘어가게 된다면 
			if(_tHappiness >= 100)
			{
				int result = JOptionPane.showConfirmDialog(BackgroundPanel.test,"Congratulation!!\nretry?","retry?", JOptionPane.YES_NO_OPTION); //YES_NO_OPTION을 가진 팝업창을 띄운다 
	            //"아니오"를 눌렀을 경우 
				if(result == JOptionPane.NO_OPTION) { 
	            	//게임실행화면을 안보이게 하고 end화면을 보이게 함으로써 화면을 전환한다 
	            	MainFrame.bPanel.setVisible(false);
					MainFrame.background.setVisible(false);
					MainFrame.Lotto.setVisible(false);
					MainFrame.Upperr.setVisible(false);
					MainFrame.End.setVisible(true);
	            }	 
	            else if(result == JOptionPane.YES_OPTION) { restart();} //"예"를 눌렀을 경우에는 restart메소드를 불러온다.
			}
		}
		
		//donate버튼을 눌렀을 때 나타나는 메소드 
		public void cansee()
		{
			LottoPanel.lblDonate.setVisible(true);
			LottoPanel.btnDonate.setVisible(true);
			LottoPanel.donateInput.setVisible(true);
			LottoPanel._FDonate.setVisible(true);
			LottoPanel.btnDonateC.setVisible(true);
			LottoPanel.LottoImageLabel.setVisible(false);
		}
		
		//donate버튼을 눌렀을 때 비활성화 되는 버튼메소드 
		public void cantenable()
		{
			moneyButton.setEnabled(false);
			levelButton.setEnabled(false);
			lottoButton.setEnabled(false);
		}
		
		//Lotto버튼을 눌렀을 때 나오는 JLabel들과 나머지 버튼 비활성화 하기 위한 함수
		public void seeLotto()
		{
			LottoPanel.lblMark.setText("? ? ? ? ? ?");
			LottoPanel.lblChance.setText("Enter 6 Numbers");
			LottoPanel.lblChance.setVisible(true);
			LottoPanel.lblMark.setVisible(true);
			LottoPanel.lblLotto.setVisible(true); 
			LottoPanel.lblget1.setVisible(true);
			LottoPanel.lblget2.setVisible(true);
			LottoPanel.lblget3.setVisible(true);
			LottoPanel.lblget4.setVisible(true);
			LottoPanel.lblRange.setVisible(true);
			LottoPanel.txtInput.setVisible(true);
			LottoPanel.btnInput.setVisible(true);
			LottoPanel.LottoImageLabel.setVisible(false);
			LottoPanel.lottostart.setVisible(true);
			moneyButton.setEnabled(false);
			donateButton.setEnabled(false);
			lottoButton.setEnabled(false);
			levelButton.setEnabled(false);
		}
		
		// Lotto에 성공하거나 실패했을 경우 lotto숨기는 함수
		public void hideLotto() {
			LottoPanel.lblLotto.setVisible(false);
			LottoPanel.lblget1.setVisible(false);
			LottoPanel.lblget2.setVisible(false);
			LottoPanel.lblget3.setVisible(false);
			LottoPanel.lblget4.setVisible(false);
			LottoPanel.lblRange.setVisible(false);
			LottoPanel.lblChance.setVisible(false);
			LottoPanel.txtInput.setVisible(false);
			LottoPanel.btnInput.setVisible(false);
		}
		//시작화면의 도움말 버튼을 눌렀을 때 
		public void help()
		{
			StartPanel.help.setVisible(true);
			StartPanel.btnreturn.setVisible(true);
			StartPanel.lblHintTitle.setVisible(true);
			StartPanel.lblHint1.setVisible(true);
			StartPanel.lblHint2.setVisible(true);
			StartPanel.lblHint3.setVisible(true);
			StartPanel.lblHint4.setVisible(true);
			StartPanel.btnhelp.setVisible(false);
			StartPanel.btnName.setEnabled(false);
			StartPanel.txtName.setEnabled(false);
		}
		public void rturn()
		{
			StartPanel.help.setVisible(false);
			StartPanel.btnreturn.setVisible(false);
			StartPanel.lblHintTitle.setVisible(false);
			StartPanel.lblHint1.setVisible(false);
			StartPanel.lblHint2.setVisible(false);
			StartPanel.lblHint3.setVisible(false);
			StartPanel.lblHint4.setVisible(false);
			StartPanel.btnhelp.setVisible(true);
			StartPanel.btnName.setEnabled(true);
			StartPanel.txtName.setEnabled(true);
		}
		
		// 로또를 맞추었을 때
		public void getlotto(int h, int m, int c) {
			LottoPanel.lblMark.setText(c + "Correct!!");
			LottoPanel.lblMark.setForeground(Color.BLUE); // lblmark 자리에 몇개의 숫자를 맞추었는지 표시
			_money += m;
			_happiness+=h;
			UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money));
			UpperPanel.HAPPINESS.setText("HAPPINESS : "+_happiness); // upperpanel에 즉시 반영
			hideLotto(); // 로또 패널 숨기기
			moneyButton.setEnabled(true);
			donateButton.setEnabled(true); // 로또를 시작할때 불가능하게 만들었던 버튼들 다시 활성화
			if(_money>=100000)
			{
				lottoButton.setEnabled(true);
			} // 현재 보유한 돈이 일정금액 이상일때만 lotto버튼 활성화
			if(_money>=1000000 && _level<4)
			{
				levelButton.setEnabled(true);
			} // 현재 보유한 돈이 많아도 level이 일정 수준에 도달하였을때는 level버튼 비활성화 유지
			setHappiness(h); // 게임종료조건인 행복도는 따로 함수를 만들어 호출
		}
		private class GameListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				
				Object obj = event.getSource();
				
				if(obj == moneyButton) //moneyButton이 눌렸을 때 
				{
					
					LottoPanel.lottostart.setVisible(false);
					LottoPanel.LottoImageLabel.setVisible(true);
					//level이 1인 경우 
					if(_level == 1)
					{
						_money+=10000; //기본적으로 올라가는 돈의 액수는 10000원 
						if(_money >= 50000) {levelButton.setEnabled(true);} //2단계로 업그레이드 할 수 있는 돈이 있게 되면 level버튼이 활성화 
						UpperPanel.MONEY.setText("MONEY : "+ ToConvert(_money)); //UpperPanel에 현재보유하고 있는 돈을 표시 
					}
					//level이 2인 경우 
					else if(_level == 2)
					{
						_money += 20000; //기본적으로 올라가는 돈의 액수는 20000원 
						if(_money >= 100000) {levelButton.setEnabled(true);} //3단계로 업그레이드 할 수 있는 돈이 있게 되면 level버튼이 활성화 
						UpperPanel.MONEY.setText("MONEY : "+ ToConvert(_money)); //UpperPanel에 현재보유하고 있는 돈을 표시 
					}
					//level이 3인 경우
					else if(_level == 3)
					{
						_money += 30000; //기본적으로 올라가는 돈의 액수는 30000원 
						if(_money >= 1000000) {levelButton.setEnabled(true);} //4단계로 업그레이드 할 수 있는 돈이 있게 되면 level버튼이 활성화 
						UpperPanel.MONEY.setText("MONEY : "+ ToConvert(_money)); //UpperPanel에 현재보유하고 있는 돈을 표시 
					}
					//level이 4(MAX LEVEL)인 경우 
					else if(_level ==4)
					{
						_money += 40000; //기본적으로 올라가는 돈의 액수는 40000원 
						UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money)); //UpperPanel에 현재보유하고 있는 돈을 표시 
					}
					if(_money >= 100000) { lottoButton.setEnabled(true);} //보유하고 있는 돈이 100000원 이상이라면 lotto버튼이 활성화 
				}
				else if(obj == levelButton) //level버튼이 눌렸을 때 
				{
					LottoPanel.lottostart.setVisible(false);
					LottoPanel.LottoImageLabel.setVisible(true);
					if(_level == 1) //레벨이 1인경우 
					{
						_money -= 50000; //level 1 -> 2로 가는 비용인 50000원을 현재 보유 금액에서 차감 
						_level = 2; //level 2로 변경 
						_happiness +=10; //level 1 -> 2로 업그레이드가 되면 행복도 10 증가 
						if(_money < 100000) { levelButton.setEnabled(false);} //3단계로 가능 비용보다 현재 보유 금액이 더 적으면 level버튼 비활성화 
						//UpperPanel의 요소에 2단계로 업그레이드에 대한 변경 사항 적용 
						UpperPanel.LEVEL.setText("<LEVEL 2>");
						UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money));
						UpperPanel.HAPPINESS.setText("HAPPINESS = "+_happiness);
						//level버튼과 money버튼에 다음 레벨로 업그레이드에 필요한 금액과 기본적으로 얻어지는 금액 표시 
						levelButton.setText("-100,000$, +15H");
						moneyButton.setText("+20,000 $");
						//level 1의 배경과 캐릭터를 안보이게 하고 level 2의 배경과 캐릭터를 보이게 한다.
						BackgroundPanel.imageLabel.setVisible(false);
						BackgroundPanel.imageLabelc.setVisible(false);
						BackgroundPanel.imageLabelc1.setVisible(true);
						BackgroundPanel.imageLabel1.setVisible(true);
						BackgroundPanel.lblmarkname.setBounds(310,460,350,60); 
						setHappiness(10); //누적 행복도에 10을 추가 
					}
					else if(_level == 2) //레벨이 2인경우 
					{   
						_money -= 100000; //level 2 -> 3로 가는 비용인 100000원을 현재 보유 금액에서 차감 
						_level = 3; //level 3으로 변경 
						_happiness +=15; //level 2 -> 3으로 업그레이드가 되면 행복도 15 증가 
						if(_money<1000000) {levelButton.setEnabled(false);} //4단계로 가능 비용보다 현재 보유 금액이 더 적으면 level버튼 비활성화 
						//UpperPanel의 요소에 3단계로 업그레이드에 대한 변경 사항 적용 
						UpperPanel.LEVEL.setText("< LEVEL 3 >");
						UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money));
						UpperPanel.HAPPINESS.setText("HAPPINESS : "+_happiness);
						//level버튼과 money버튼에 다음 레벨로 업그레이드에 필요한 금액과 기본적으로 얻어지는 금액 표시 
						levelButton.setText("-1,000,000$, +20H");
						moneyButton.setText("+30,000 $");
						//level 2의 배경과 캐릭터를 안보이게 하고 level 3의 배경과 캐릭터를 보이게 한다.
						BackgroundPanel.imageLabel1.setVisible(false);
						BackgroundPanel.imageLabelc1.setVisible(false);
						BackgroundPanel.imageLabelc2.setVisible(true);
						BackgroundPanel.imageLabel2.setVisible(true);
						BackgroundPanel.lblmarkname.setBounds(280,390,350,60);
						setHappiness(15);//누적 행복도에 15을 추가 
					}
					else if(_level == 3)//레벨이 3인경우 
					{
						_money -= 1000000; //level 3 -> 4로 가는 비용인 100000원을 현재 보유 금액에서 차감 
						_level = 4; //level 4으로 변경 
						_happiness += 20; //level 3 -> 4으로 업그레이드가 되면 행복도 15 증가 
						levelButton.setEnabled(false); //MAX LEVEL이기 때문에 level버튼 비활성화 
						//UpperPanel의 요소에 3단계로 업그레이드에 대한 변경 사항 적용 
						UpperPanel.LEVEL.setText("< MAX LEVEL >");
						UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money));
						UpperPanel.HAPPINESS.setText("HAPPINESS : "+_happiness);
						//level버튼과 money버튼에 MAX LEVEL과 기본적으로 얻어지는 금액 표시 
						levelButton.setText("< MAX LEVEL >");
						moneyButton.setText("+40,000 $");
						//level 3의 배경과 캐릭터를 안보이게 하고 level 4의 배경과 캐릭터를 보이게 한다.
						BackgroundPanel.imageLabel2.setVisible(false);
						BackgroundPanel.imageLabelc2.setVisible(false);
						BackgroundPanel.imageLabelc3.setVisible(true);
						BackgroundPanel.imageLabel3.setVisible(true);
						BackgroundPanel.lblmarkname.setBounds(320,440,350,60);
						setHappiness(20); //누적 행복도에 15을 추가 
					}
				}
				else if(obj == lottoButton) // 로또 버튼을 눌렀을 경우
				{
					_money-=100000; // 로또 구입 금액을 현재 보유 잔액에서 감소시킴
					UpperPanel.MONEY.setText("MONEY : "+ToConvert(_money)); // 즉시 반영
					_Count = _Num = 0; // int형 변수 초기화
					LottoPanel.txtInput.setText(""); // 숫자를 한번 입력하면 원래대로 돌아오게끔 한다
					_getM=1000000;
					_getH=70; // int형 변수 초기화
					LottoPanel.lblMark.setForeground(Color.black); // 로또에 성공하거나 실패했을 경우 lblmark색이 변경되므로 첫 시작할때 색인 검정으로 다시 설정해주기
					
					for(int i=0;i<6;i++) {
						_Random[i] = (int)(Math.random()*45)+1;
						System.out.println(_Random[i]);
					} // _Random이라는 배열에 1~45까지의 숫자 랜덤으로 넣는다.
					
					seeLotto(); // 로또패널에 로또가 보이게끔 하는 함수 호출
				}
				else if(obj == donateButton) //기부버튼을 눌렀을 
				{
					LottoPanel.lottostart.setVisible(false); //기부UI를 나타내기위해 기존에 있던 lottoPanel의 UI을 안보이게 설정 
					//정의했던 기부버튼 메소드 
					cansee(); 
					cantenable();
				}
				else if(obj == LottoPanel.btnInput || obj == LottoPanel.txtInput) { // 로또화면에서 숫자를 넣었을 경우
					_Input = Integer.parseInt(LottoPanel.txtInput.getText()); // _Input이라는 변수에 문자를 숫자로 바꿔 저장
					for(int i=0;i<6;i++) {
						if(_Random[i] == _Input) {
							_Count++;
						}
					} // 만약 내가 입력한 숫자가 랜덤배열에 있는 수자랑 같으면 _Count가 증가함
					if(_Input>45 || _Input<1) {
						LottoPanel.lblMark.setText("1 ~ 45");
					} // 내가 입력한 숫자가 주어진 범위(1~45)를 벗어날 경우 0이 찍혀있던 자리에 범위를 다시 나타나게끔 해 범위를 강조한다.
					else {						
						LottoPanel.lblMark.setText("? ? ? ? ? ?");
						_Num++;
					} // 내가 입력한 숫자가 몇개인지 _Num변수에 저장
					if(_Num==6) { // 총 6개의 숫자를 모두 입력했을 경우
						if(_Count == 6) {
							getlotto(_getH,_getM,_Count);
						} // 6개 숫자 다 맞추었을 때
						else if(_Count == 5) {
							getlotto(_getH - 10,_getM - 100000,_Count);
						} // 5개숫자 맞추었을때 다 맞추었을 때보다 행복도는 10, 얻을 수 있는 금약은 100000 감소
						else if(_Count == 4) {
							getlotto(_getH - 20,_getM - 200000,_Count);
						} // 4개 숫자를 맞추었을 경우 다 맞추었을 때보다 행복도는 20, 얻을 수 있는 금약은 200000 감소
						else if(_Count == 3) {
							getlotto(_getH - 30,_getM - 300000,_Count);
						} // 3개 숫자를 맞추었을 경우 다 맞추었을 때보다 행복도는 30, 얻을 수 있는 금액은 300000 감소
						else { // 2개 이하로 맞추어 아무것도 얻지 못하게 되었을 경우
							LottoPanel.lblMark.setText("FAIL.."); // 실패를 알리는 문구가 나타나게됨
							LottoPanel.lblMark.setForeground(Color.blue);
							_happiness-=5; // 얻은것은 없고 로또를 구입하는 비용만 사용했으므로 행복도 5감소
							UpperPanel.HAPPINESS.setText("HAPPINESS : "+_happiness); // 즉시 반영
							setHappiness(-5);
							hideLotto(); // 로또게임이 끝났으므로 숨기는 함수 호출
							moneyButton.setEnabled(true);
							donateButton.setEnabled(true); // 돈과 기부 버튼 활성화
							if(_money>=50000&&_level==1) { 
								levelButton.setEnabled(true);
							}
							else if(_money>=100000) {
								lottoButton.setEnabled(true);
								if(_level<=2) {
									levelButton.setEnabled(true);
								}
							}
							else if(_money>=1000000&&_level<=3) {
								levelButton.setEnabled(true);
							}
							// 레벨에 따라 일정 금액 이상 소지하고 있을 경우만 level버튼이 활성화된다.
						}
					}
					else {
						LottoPanel.txtInput.setText(""); // 숫자하나를 입력하고 나면 숫자입력하는 곳은 처음과 같도록 설정
						LottoPanel.lblChance.setText(6-_Num + " remain"); // 내가 입력해야 하는 숫자가 몇번 남았는지 보여줌
					}
				}
				else if(obj == LottoPanel.btnDonate || obj == LottoPanel.donateInput) //기부금을 입력한 textfield와 입력할 button을 눌렀을 경우 
				{
					_donate = Integer.parseInt(LottoPanel.donateInput.getText()); // textfield에 입력된 숫자를 _donate변수에 저장 
					
					if(_donate%10000 == 0) //기부금이 10000원 단위일 경우 
					{
						if(_money < _donate) //기부금이 10000원 단위이지만 보유하고 있는 금액보다 더 클 경우 
						{
							//자신이 보유하고 있는 금액보다 기부금이 크기 때문에 변화가 일어나지 않음 
							_money-=0; 
							_happiness+=0;
							LottoPanel.donateInput.setText(""); //textfield를 다시 공백으로 만듬 
						}
						else //기부금이 10000원 단위이면서 자신이 가지고 있는 보유금보다 적을 경우 
						{
							_money-=_donate; //보유금에서 기부금을 차감 
							_happiness +=_donate*0.0001; //행복도는 10000원당 1씩 오르도록 설정 
							//UpperPanel의 요소에 기부로 인해 변경된 보유금과 행복도 변경 
							UpperPanel.MONEY.setText("MONEY : "+ ToConvert(_money));
							UpperPanel.HAPPINESS.setText("HAPPINESS = "+_happiness);
							LottoPanel.donateInput.setText(""); //기부가 끝나면 textfield를 다시 공백으로 설정 
							setHappiness((int)(_donate*0.0001)); //누적 행복도에 기부로 얻은 행복도를 추가 
						}
					}
					else { LottoPanel.donateInput.setText("");} //기부금이 10000원 단위가 아니라면 아무일도 일어나지 않고 textfield를 공백으로 만듬 
					afterDonate(); //기부이후메소드를 사용 
				}
				else if(obj == LottoPanel.btnDonateC){ afterDonate();} //기부취소버튼을 눌렀을 때 기부이후메소드 사용 
				else if(obj == StartPanel.txtName || obj == StartPanel.btnName) {
					_name = StartPanel.txtName.getText();
					BackgroundPanel.lblmarkname.setText("NAME :"+_name);
					MainFrame.start.setVisible(false);
					MainFrame.bPanel.setVisible(true);
					MainFrame.background.setVisible(true);
					MainFrame.Lotto.setVisible(true);
					MainFrame.Upperr.setVisible(true);
				}
				else if(obj == StartPanel.btnhelp) //도움말 버튼을 눌렀을 때 
				{
					help();
				}
				else if(obj == StartPanel.btnreturn) //도움말에서 다시 시작화면으로 돌아가는 버튼을 눌렀을 때 
				{
					rturn();
				}
			}
		}
}
