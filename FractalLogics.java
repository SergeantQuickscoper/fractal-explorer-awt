import java.awt.image.*;

/*
 * This interface is intended to be able to represent any general fractical image.
 * It is deliberately left vague as I think being restrictive could make some kinds of fractals harder to make.
 * Though as a  TODO: Eventually get more restrictive to ensure that zooming would work correctly across all fractals
 */
interface FractalLogic{
    	private int width_;
	private int height_;

	/*
	* This function should generate a final bufferred 
	* @param width - the width of the image
	* @param height - the height of the image
	* @returns a BufferedImage containing the rendered fractal, to be passed into FractalCanvas
	*/
	public BufferedImage generate();
}

public class FractalLogics{

	/*
	 * This class contains the logic to render the Mandelbrot Set Fractal
	 */
	public static class MandelbrotFractal implements FractalLogic{
		private int width_;
		private int height_;
		private int resolution_ = 100;
		private int originX_;
		private int originY_;

		MandelbrotFractal(int width, int height, int resolution, int originX, int originY){
			
		}

		public BufferedImage generate(){

		}
	}

}
