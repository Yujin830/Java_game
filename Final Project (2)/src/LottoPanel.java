import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LottoPanel extends JPanel {
	   
	   static JLabel lblDonate,_FDonate;
	   static JTextField donateInput;
	   static JButton btnDonate,btnDonateC;
	   static JPanel test;
	   
	   
	   static JLabel lblLotto, lblMark, lblRange, lblChance,LottoImageLabel,menu, lottostart;
	   static JLabel lblget1, lblget2, lblget3, lblget4;
	   ImageIcon LottoImage;
	   static JButton btnInput;
	   static JTextField txtInput;
	
		public LottoPanel() {
			setBackground(Color.white);
			setLayout(null);
			LottoImage = new ImageIcon("./image/love.png");
			LottoImageLabel = new JLabel(LottoImage);
			LottoImageLabel.setBounds(0,0,300,500);
			add(LottoImageLabel);
			
			menu = new JLabel("Fill happiness up with 100");
			menu.setFont(new Font("Verdana",Font.BOLD,18));
			menu.setBounds(0, 0, 300, 500);
			menu.setHorizontalAlignment(SwingConstants.CENTER);
			menu.setVerticalAlignment(SwingConstants.TOP);
			LottoImageLabel.add(menu);
		

			//donate UI 라벨 
			lblDonate = new JLabel("DONATE");
			lblDonate.setBounds(10, 10, 280, 70);
			lblDonate.setHorizontalAlignment(SwingConstants.CENTER);
			lblDonate.setFont(new Font("Verdana", Font.BOLD, 50));
			lblDonate.setForeground(Color.magenta);
			lblDonate.setVisible(false);
			add(lblDonate);
			
			//donate UI 라벨("10000원당 1행복도를 올려준다") 
			_FDonate = new JLabel("1H per 10,000 $");
			_FDonate.setBounds(10, 240, 280, 50);
			_FDonate.setHorizontalAlignment(SwingConstants.CENTER);
			_FDonate.setFont(new Font("Verdana", Font.BOLD, 25));
			_FDonate.setVisible(false);
			add(_FDonate);
			
			//donate input 버튼 
			btnDonate = new JButton("INPUT");
			btnDonate.setBounds(50, 195, 90, 30);
			btnDonate.setFont(new Font("Verdana",Font.BOLD,10));
			btnDonate.setVisible(false);
			add(btnDonate);
			
			//donate cancle 버튼 
			btnDonateC = new JButton("CANCLE");
			btnDonateC.setBounds(160, 195, 90, 30);
			btnDonateC.setFont(new Font("Verdana",Font.BOLD,10));
			btnDonateC.setVisible(false);
			add(btnDonateC);
			
			//기부금을 입력할 textfield 
			donateInput = new JTextField();
			donateInput.setBounds(50, 135, 200, 50);
			donateInput.setFont(new Font("Verdana", Font.BOLD, 23));
			donateInput.setVisible(false);
			add(donateInput);
			
			
			/////////////////////////////////////////////////
			
			
			// 로또 버튼을 눌렀을 경우 나타나는 로또 배경 이미지
			lottostart = new JLabel(new ImageIcon("./image/Lottoback.png"));
			lottostart.setBounds(0, 0, 300, 500);
			lottostart.setVisible(false);
			add(lottostart);
			
			// 로또 숫자 범위를 나타내주는 JLabel (로또 배경 이미지에 add)
			lblRange = new JLabel("1~45");
			lblRange.setBounds(10,90,270,50);
			lblRange.setHorizontalAlignment(SwingConstants.CENTER);
			lblRange.setFont(new Font("Verdana", Font.BOLD, 23));
			lottostart.add(lblRange);
			
			// 로또 숫자가 6개임을 알려주는 '?'모양 6개 JLabel (로또 배경 이미지에 add)
			lblMark = new JLabel("? ? ? ? ? ?");
			lblMark.setBounds(10, 140, 280, 140);
			lblMark.setHorizontalAlignment(SwingConstants.CENTER);
			lblMark.setFont(new Font("Verdana", Font.ITALIC, 50));
			lottostart.add(lblMark);
			
			// 로또글씨 보여주는 JLabel (로또 배경 이미지에 add)
			lblLotto = new JLabel("LOTTO");
			lblLotto.setBounds(10, 10, 280, 70);
			lblLotto.setHorizontalAlignment(SwingConstants.CENTER);
			lblLotto.setFont(new Font("Verdana", Font.BOLD, 70));
			lblLotto.setForeground(Color.red);
			lottostart.add(lblLotto);
			
			// 숫자 6개를 다 맞추었을 경우 얻는 돈과 행복도 표시 JLabel (로또 배경 이미지에 add)
			lblget1 = new JLabel("Correct 6 : 1,000,000 $ + 70H");
			lblget1.setBounds(10, 370, 280, 30);
			lblget1.setHorizontalAlignment(SwingConstants.CENTER);
			lblget1.setFont(new Font("Verdana", Font.BOLD, 16));
			lottostart.add(lblget1);
			
			// 숫자 5개를 맞추었을 경우 얻는 돈과 행복도 표시 JLabel (로또 배경 이미지에 add)
			lblget2 = new JLabel("5 : 900,000 $ + 60H");
			lblget2.setBounds(10, 400, 280, 30);
			lblget2.setHorizontalAlignment(SwingConstants.CENTER);
			lblget2.setFont(new Font("Verdana", Font.BOLD, 16));
			lottostart.add(lblget2);
			
			// 숫자 4개를 맞추었을 경우 얻는 돈과 행복도 표시 JLabel (로또 배경 이미지에 add)
			lblget3 = new JLabel("4 : 800,000 $ + 50H");
			lblget3.setBounds(10, 430, 280, 30);
			lblget3.setHorizontalAlignment(SwingConstants.CENTER);
			lblget3.setFont(new Font("Verdana", Font.BOLD, 16));
			lottostart.add(lblget3);
			
			// 숫자 3개를 맞추었을 경우 얻는 돈과 행복도 표시 JLabel (로또 배경 이미지에 add)
			lblget4 = new JLabel("3 : 700,000 $ + 40H");
			lblget4.setBounds(10, 460, 280, 30);
			lblget4.setHorizontalAlignment(SwingConstants.CENTER);
			lblget4.setFont(new Font("Verdana", Font.BOLD, 16));
			lottostart.add(lblget4);
			
			// 숫자를 입력하는 칸 위에 표시되는 JLabel, 숫자를 하나씩 입력할수록 바뀜 (로또 배경 이미지에 add)
			lblChance = new JLabel("Enter 6 Numbers");
			lblChance.setBounds(10, 280, 280, 30);
			lblChance.setHorizontalAlignment(SwingConstants.CENTER);
			lblChance.setFont(new Font("Verdana", Font.PLAIN, 20));
			lottostart.add(lblChance);
			
			// 로또 숫자 입력하는 JButton
			btnInput = new JButton("INPUT");
			btnInput.setBounds(200, 320, 80, 50);
			btnInput.setFont(new Font("Verdana",Font.BOLD,13));
			lottostart.add(btnInput);
			
			// 생각하고 있는 로또 숫자를 쓰는 JTextField
			txtInput = new JTextField();
			txtInput.setBounds(20, 320, 180, 50);
			txtInput.setFont(new Font("Verdana", Font.BOLD, 23));
			lottostart.add(txtInput);
			
		}
		 
}