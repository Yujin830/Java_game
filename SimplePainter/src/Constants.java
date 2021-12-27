import java.awt.*;

public class Constants {

	static final public String MENU[] = {
			"DOT", "LINE", "RECT", "OVAL", "PEN", "UNDO", "REDO", "RE"
	}; // final -> 변경 못하도록
	static final public Color HOVERING[]= {
			Color.LIGHT_GRAY, Color.black, Color.black, Color.white
	};
	static final public String MESSAGE[]= {
			"DRAW : ", "SIZE : ", "COLOR : ", "CENTER LOCATION : ", "LENGTH : ", "TRACE : ", "PEN THICKNESS : "
	}; // 자주 사용하는 문자들 배열 만들어서 저장
	
	static final public int DOT = 0;
	static final public int LINE = 1;
	static final public int RECT = 2;
	static final public int OVAL = 3;
	static final public int PEN = 4;
	static final public int UNDO = 5;
	static final public int REDO = 6;
	static final public int RE = 7;
	static final public int NONE = 8;
	// 문자들 숫자로 비교해야 할 경우를 대비해 숫자로 바꿔둠
}
