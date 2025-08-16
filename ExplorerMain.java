import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ExplorerMain{
	public static void main(String[] args){
		
		Frame defFrame = new Frame("Fractal Explorer");	
		Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
		defFrame.setSize(screenDims.width, screenDims.height);
		defFrame.setVisible(true);
		
		WindowAdapter closer = new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event){
				defFrame.dispose();
				System.exit(0);
			}
		};
		
		defFrame.addWindowListener(closer);
	}
}
