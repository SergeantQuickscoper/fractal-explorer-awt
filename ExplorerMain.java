import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ExplorerMain{
	private final static int WIDTH_PIX = 800;
	private final static int HEIGHT_PIX = 800;

	public static void main(String[] args){
		Frame defFrame = new Frame("Fractal Explorer");	
		defFrame.setSize(WIDTH_PIX, HEIGHT_PIX);
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

/*
 * This class is a component that will render a buffered image, intended to be used to render some fractal image.
 */
class FractalCanvas extends Canvas{
	final private Color bgColor = new Color(2, 8, 104);
	private BufferedImage fractalImage;
	private int width_;
	private int height_;
	
	/*
	 * The constructor will initalize the fractal image and setup the canvas dimensions.
	 * @param width - the width of the fractal image and the canvas itself.
	 * @param height - the height of the fractal image and the canvas itself.
	 */
	FractalCanvas(int width, int height){
		super();
		fractalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		width_ = width;
		height_ = height;
	}
	
	/*
	 * This function lets the parent component know the component's preferred size.
	 * We are just overriding it to fit the parent width and height.
	 * @returns a dimension object containing the initialized width and height vars
	 */
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(width_, height_);
	}
	
	/*
	 * This function is automatically called whenever the AWT wants to render this component.
	 * So we are telling to draw the set fractalImage whenever the FractalCanvas is to be rendered.
	 * @param drawer - just a graphics object passed in when the AWT calls this, can use it to draw anything you want.
	 */
	@Override
	public void paint(Graphics drawer){
		drawer.drawImage(fractalImage, 0, 0, null);
	}

}
