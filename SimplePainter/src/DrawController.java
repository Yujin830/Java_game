import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JPanel;

public class DrawController extends JPanel {

	private SimplePainterModel nowData; // SimplePainterModel 이랑 연결되는 nowData선언
	public ArrayList<SimplePainterModel> savedList; // 그림 그린 것들을 저장할 배열
	public ArrayList<SimplePainterModel> removedList; // 삭제한것을 저장할 배열
	private DrawListener drawL;
	private SimplePainterView View; // SimplePainterView 와 연결되는 View 선언
	private boolean bDrag;
	public Vector<Point> vStart = new Vector<Point>();
	
	public DrawController(SimplePainterView v) {
		View = v;
		
		setBackground(Color.white);
		
		drawL = new DrawListener();
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		nowData = new SimplePainterModel();
		savedList = new ArrayList<SimplePainterModel>();
		removedList = new ArrayList<SimplePainterModel>();
		
		nowData.nDrawMode = Constants.NONE;
		bDrag = false;
	}

	public int arraysize(ArrayList<SimplePainterModel> list) {
		return list.size();
	} // list 사이즈 반환

	public void setDrawsize(int mode, int flag) {
		nowData.nDrawMode = mode;
		if(flag == 0) {
			View.setTxtSize(10);
			View.flag = 1;
		} // flag가 0일때(=처음일때) 도형 사이즈(굵기)를 10으로 저장 후 flag는 1로 설정
		if (nowData.nDrawMode == Constants.RE) {
			View.setTxtSize(10);
			View.flag=0;
		} // CLEAR을 누를 경우 다 리셋되게끔 도형 사이즈(굵기)를 10으로 바꾸고 flag는 다시 0으로 설정
		else if(flag != 0 && nowData.nDrawMode != Constants.RE) {
			View.setTxtSize(nowData.nSize);		
		} // 위의 경우 둘 다 아닐경우 버튼을 바꿔도 설정해놓은 사이즈에서 변하지 않음
	}
	
	public void setSelectedColor(Color color) {
		nowData.selectedColor = color;
	} // SimplePainterModel 의 selectedColor에 SimplePainterView에서 고른 색 저장
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page); // 실제로 그림을 그리는 그래픽 함수
		
		if(bDrag) { // 만약 드래그를 할 경우(라인, 사각형, 원 세가지 경우)
			switch(nowData.nDrawMode) {
			case Constants.LINE :
				page.setColor(nowData.selectedColor);
				Graphics2D page2 = (Graphics2D)page;
				page2.setStroke(new BasicStroke(nowData.nSize));
				page.drawLine(nowData.ptone.x, nowData.ptone.y, nowData.pttwo.x, nowData.pttwo.y);
				break;
			case Constants.RECT :
				page.setColor(nowData.selectedColor);
				Graphics2D page3 = (Graphics2D)page;
				page3.setStroke(new BasicStroke(nowData.nSize));
				draw4(page, nowData.ptone, nowData.pttwo, nowData.bFill, 2);
				break;
			case Constants.OVAL :
				page.setColor(nowData.selectedColor);
				Graphics2D page4 = (Graphics2D)page;
				page4.setStroke(new BasicStroke(nowData.nSize));
				draw4(page, nowData.ptone, nowData.pttwo, nowData.bFill, 3);
				break;
			case Constants.PEN :
				page.setColor(nowData.selectedColor);
				Graphics2D page5 = (Graphics2D)page;
				page5.setStroke(new BasicStroke(nowData.nSize));
				for(int i=1;i<vStart.size();i++) {
					if(vStart.get(i-1) == null || vStart.get(i) == null) continue;
					else {
						page.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(), (int) vStart.get(i).getX(), (int) vStart.get(i).getY());
					}
				}
				break;
			}	
		} // if
		for ( SimplePainterModel data : savedList) { // 배열에 저장되어있는 버튼에 대해
			switch(data.nDrawMode) {
			case Constants.DOT :
				page.setColor(data.selectedColor);
				page.fillOval(data.ptone.x - data.nSize/2, data.ptone.y - data.nSize/2, data.nSize, data.nSize);
				// 점을 찍는건 전부 색 채우기
				break;
			case Constants.LINE :
				page.setColor(data.selectedColor);
				Graphics2D page2 = (Graphics2D)page;
				page2.setStroke(new BasicStroke(data.nSize));
				page.drawLine(data.ptone.x, data.ptone.y, data.pttwo.x, data.pttwo.y);
				break;
			case Constants.RECT :
				page.setColor(data.selectedColor);
				Graphics2D page3 = (Graphics2D)page;
				page3.setStroke(new BasicStroke(data.nSize));
				draw4(page, data.ptone, data.pttwo, data.bFill, 2);
				break;
			case Constants.OVAL :
				page.setColor(data.selectedColor);
				Graphics2D page4 = (Graphics2D)page;
				page4.setStroke(new BasicStroke(data.nSize));
				draw4(page, data.ptone, data.pttwo, data.bFill, 3);
				break;
			case Constants.PEN :
				page.setColor(nowData.selectedColor);
				Graphics2D page5 = (Graphics2D)page;
				page5.setStroke(new BasicStroke(nowData.nSize));
				for(int i=1;i<vStart.size();i++) {
					if(vStart.get(i-1) == null || vStart.get(i) == null) continue;
					else {
						page.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(), (int) vStart.get(i).getX(), (int) vStart.get(i).getY());
					}
				}
				break;
			}
		} //for
	}
	
	private void draw4(Graphics page, Point pt1, Point pt2, boolean Fill, int i) { // 네 방향으로 그리기위한 함수(사각형, 원)
		
		int x,y,w,h;
		x = y = w = h = 0;
		
		if(pt1.x < pt2.x && pt1.y < pt2.y) {
			x=pt1.x;
			y=pt1.y;
			w=pt2.x-pt1.x;
			h=pt2.y-pt1.y;
		}
		else if(pt1.x < pt2.x && pt1.y > pt2.y) {
			x=pt1.x;
			y=pt2.y;
			w=pt2.x-pt1.x;
			h=pt1.y-pt2.y;
		}
		else if(pt1.x > pt2.x && pt1.y < pt2.y) {
			x=pt2.x;
			y=pt1.y;
			w=pt1.x-pt2.x;
			h=pt2.y-pt1.y;
		}
		else if(pt1.x > pt2.x && pt1.y > pt2.y) {
			x=pt2.x;
			y=pt2.y;
			w=pt1.x-pt2.x;
			h=pt1.y-pt2.y;
		}
		if(i==2) {
			if(Fill) page.fillRect(x,y,w,h);
			else page.drawRect(x, y, w, h);
		}
		else if(i==3) {
			if(Fill) page.fillOval(x,y,w,h);
			else page.drawOval(x, y, w, h);
		}
	}
	
	private class DrawListener implements MouseListener, MouseMotionListener{ // 마우스로 하는 행동에 대한 리스너클래스

		@Override
		public void mouseClicked(MouseEvent e) { // 마우스로 클릭을 했을 경우(점 찍는 버튼)
			if(nowData.nDrawMode == Constants.DOT){
				nowData.ptone = e.getPoint(); // ptone변수에 점을 찍은 곳을 저장
				nowData.nSize = View.getTxtSize();
				savedList.add(new SimplePainterModel(nowData)); // 배열에 저장
				repaint(); // 화면에 바로 표시
				print(); // message창에 표시하는 함수 호출
				canuse(); // UNDO, CLEAR버튼 사용 가능하게 하는 함수 호출
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) { // 한 시점에서 마우스를 눌렀을 경우(선, 사각형, 원)
			if (nowData.nDrawMode == Constants.LINE) {
				bDrag = true; // 드래그를 true로 설정
				
				nowData.ptone = e.getPoint();
				nowData.nSize = View.getTxtSize();
			}
			else if(nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL) {
				bDrag = true;
				
				nowData.ptone=e.getPoint();
				nowData.bFill = SimplePainterView.chkFill.isSelected(); // 체크박스의 체크 유무
				nowData.nSize = View.getTxtSize();
			}
			else if(nowData.nDrawMode == Constants.PEN) {
				bDrag = true;
				
				nowData.ptone = e.getPoint();
				nowData.nSize = View.getTxtSize();
				vStart.add(null);
				vStart.add(e.getPoint());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) { // 누르고 있던 마우스를 뗐을 경우
			if(nowData.nDrawMode == Constants.LINE || nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL || nowData.nDrawMode == Constants.PEN) {
				bDrag = false; // 마우스를 뗐으므로 드래그는 false로 설정
				
				nowData.pttwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			print();
			canuse();
		}

		@Override
		public void mouseDragged(MouseEvent arg0) { // 마우스를 드래그
			if(nowData.nDrawMode == Constants.LINE || nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL) {
				nowData.pttwo = arg0.getPoint();
				repaint();
			}
			else if(nowData.nDrawMode == Constants.PEN) {
				nowData.pttwo = arg0.getPoint();
				vStart.add(arg0.getPoint());
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private void print() { // print함수
		View.lblcolor.setText(Constants.MESSAGE[2] + "R = "+nowData.selectedColor.getRed()+", G = "+nowData.selectedColor.getGreen()+", B = "+nowData.selectedColor.getBlue());
		// red, green, blue 따로따로 색 표시
		int x1,x2,y1,y2,w,h;
		x1=nowData.ptone.x;
		x2=nowData.pttwo.x;
		y1=nowData.ptone.y;
		y2=nowData.pttwo.y;
		if(x1>x2) w=x1-x2;
		else w=x2-x1;
		// 사각형과 원에 필요한 폭 계산
		if(y1>y2) h=y1-y2;
		else h=y2-y1;
		// 사각형과 원에 필요한 높이 계산
		if (nowData.nDrawMode == Constants.DOT) {
			View.lblsize.setText(Constants.MESSAGE[1]+nowData.nSize);
			View.lbllocation.setText(Constants.MESSAGE[3]+"X = "+x1+", Y = "+y1);
		}
		else if (nowData.nDrawMode == Constants.LINE) {
			View.lblsize.setText(Constants.MESSAGE[4]+Math.round(Math.sqrt(w*w+h*h)));
			// 선을 그렸을 경우 size 대신 length길이 표시 -> 소수점 첫째자리 숫자에서 반올림
			View.lbllocation.setText(Constants.MESSAGE[5] + "X = " + x1 + "~" + x2 + ", Y = " + y1 + "~" + y2);
			// 선을 그렸을 경우 x,y 좌표를 흔적으로 나타냄( 처음 마우스를 눌렀을때와 뗐을때)
		}
		else if (nowData.nDrawMode == Constants.OVAL || nowData.nDrawMode == Constants.RECT){
			View.lblsize.setText(Constants.MESSAGE[1]+"Width = "+w+", Height = " + h);
			// 원과 사각형에서 size는 폭과 높이로 나타냄
			View.lbllocation.setText(Constants.MESSAGE[3] + "X = " + ((x1+x2)/2) + ", Y = " + ((y1+y2)/2));
			// 원과 사각형에서 x,y좌표는 도형의 정중앙값으로 표시함
		}
		else if (nowData.nDrawMode == Constants.PEN) {
			View.lblsize.setText(Constants.MESSAGE[6] + nowData.nSize);
			// 자유곡선인 pen은 size대신 선 굵기로 대신함
		}
	}
	private void canuse() {
		if(arraysize(savedList)!=0) {
			View.btnMenuArray[5].setEnabled(true);
			View.btnMenuArray[6].setEnabled(true);
			View.btnMenuArray[7].setEnabled(true);
		} // 만약 그려진 도영이 한개 이상일 경우 UNDO, REDO, CLEAR을 사용 가능하게함
	}
	
} // DrawController class
