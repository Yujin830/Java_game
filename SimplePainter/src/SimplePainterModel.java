import java.awt.*;

public class SimplePainterModel {

	public int nDrawMode;
	public Point ptone, pttwo;
	public int nSize;
	public boolean bFill;
	public Color selectedColor;
	
	public SimplePainterModel() {
		nDrawMode = Constants.NONE;
		ptone = new Point();
		pttwo = new Point();
		nSize = 10;
		bFill = false;
		selectedColor = Color.black;
	} // 처음 변수 초기화
	
	public SimplePainterModel(SimplePainterModel data) {
		nDrawMode = data.nDrawMode;
		ptone = data.ptone;
		pttwo = data.pttwo;
		nSize = data.nSize;
		bFill = data.bFill;
		selectedColor = data.selectedColor;
	} // 그림을 그리면서 연결되어 바뀔 새로운 함수
}
