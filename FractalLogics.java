import java.awt.image.*;
import java.awt.Color;

/*
 * This class acts a container for adding all sorts of fractals.
 * Includes the FractalLogic interface too for now, giving it its own file seemed overkill
 * TODO: Reduce the naming overhead by making this a packagge later.
 */
public class FractalLogics{
	/*
	 * This interface is intended to be able to represent any general fractical image.
	 * It is deliberately left vague as I think being restrictive could make some kinds of fractals harder to make.
	 * Though as a  TODO: Eventually get more restrictive to ensure that zooming would work correctly across all fractals
 	*/
	public interface FractalLogic{
		/*
		* This function should generate a final buffered image of the fractal
		* @param width - the width of the image
		* @param height - the height of the image
		* @returns a BufferedImage containing the rendered fractal, to be passed into FractalCanvas
		*/
		public BufferedImage generateImage();
	}

	/*
	 * This class contains the logic to render the Mandelbrot Set Fractal
	 */
	public static class MandelbrotFractal implements FractalLogic{
		private int width_;
		private int height_;
		private double resolution_ = 100;
		private int originX_;
		private int originY_;
		private int MAX_ITERATIONS = 100;
		private double MAX_MAG_SQUARED = 4;
		final private Color baseGradColor = new Color(0, 0, 0);
		final private Color bgColor = new Color(2, 8, 104);
		final private Color axesColor = new Color(255, 180, 200);
		// TODO: overload this later to allow for custom initial resolutions 
		MandelbrotFractal(int width, int height, int originX, int originY){
			width_ = width;
			height_ = height;
			originX_ = originX;
			originY_ = originY;
		}
		
		/*
		 * This function simply returns true if the point belongs to the Mandelbrot set and false otherwise.
		 * It checks until the MAX_ITERATIONS if the square of the magnitude goes above a certain value.
		 * @params real - real component of the number (x axis)
		 * @params imag - imaginary component of the number (y axis)
		 * @returns - true if in mandelbrot otherwise false
		 */
		private boolean isMandelbrot(double real, double imag){
			double realCurr = 0;
			double imagCurr = 0;
			double magSquared = 0;
			double tempReal = 0;
			int currIterations = 0;
			while(magSquared <= MAX_MAG_SQUARED){
				if(currIterations >= MAX_ITERATIONS) return true;
				tempReal = realCurr * realCurr - imagCurr * imagCurr + real;
				imagCurr = 2 * realCurr * imagCurr + imag;
				realCurr = tempReal;
				magSquared = realCurr * realCurr + imagCurr * imagCurr;
				currIterations++;
			}

			return false;
		}
		
		/*
		 * This function iterates through the viewport and assigns color for the axes and based on Mandelbrot condition
		 * @returns a buffered image to be passed into the FractalCanvas
		 */
		public BufferedImage generateImage(){
			BufferedImage fractalImage = new BufferedImage(width_, height_, BufferedImage.TYPE_INT_RGB);
			for(int i = 0; i < width_; i++){
				for(int j = 0; j < height_; j++){
					double realCoordinate = ((double)(i - originX_))/resolution_;
					double imagCoordinate = ((double)(originY_ - j))/resolution_;
					if(realCoordinate == 0 || imagCoordinate == 0){
						fractalImage.setRGB(i, j, axesColor.getRGB());
					}
					else if(isMandelbrot(realCoordinate, imagCoordinate)){
						fractalImage.setRGB(i, j, baseGradColor.getRGB());
					}
					else{
						fractalImage.setRGB(i, j, bgColor.getRGB());
					}
				}
			}
			return fractalImage;
		}
	}

}
