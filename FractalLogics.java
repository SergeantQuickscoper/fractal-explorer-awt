import java.awt.Color;
import java.awt.event.*;
import java.awt.image.*;
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
		* This function is just used to get a reference to the rendering canvas, so we can repaint it when mouse is clicked.
		*/
		public void setCanvas(FractalCanvas canvasRef);
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
	public static class MandelbrotFractal implements FractalLogic, MouseListener{
		/* TODO: Isues currently in this class:
		* TODO: i have changed originX and originY from integer which made sense intuitively as they represent pixels
		* TODO: to doubles, which allow for further depth until the fractal fragemnts, but I don't fully understand yet why using 
		* TODO: origin as integer compeletly blanks the screen as a relatively lower depth, I suspect its because of the mapping operations during zooming
		* TODO: which also in turn could be improved. My current solution works great and reaches the maximum depth possible that double can handle, and the
		* TODO: only bug is that the axes have some error in thicknesses. 
		*/ 
		private int width_;
		private int height_;
		private double resolution_ = 150;
		private double originX_;
		private double originY_;
		private int MAX_ITERATIONS = 300;
		private double MAX_MAG_SQUARED = 8;
		private FractalCanvas canvasRef_;
		private double zoomMultiplier = 1.25;
		final private Color baseGradColor = new Color(0, 0, 0);
		final private Color bgColor = new Color(10, 10, 200);
		final private Color axesColor = new Color(255, 180, 200);
		
		// TODO: overload this later to allow for custom initial resolutions 
		MandelbrotFractal(int width, int height, int originX, int originY){
			width_ = width;
			height_ = height;
			originX_ = originX;
			originY_ = originY;
		}
		
		/*
		 * This function is just used to get a reference to the rendering canvas, so we can repaint it when mouse is clicked.
		 */
		public void setCanvas(FractalCanvas canvasRef){
			this.canvasRef_ = canvasRef;
		}

		/*
		 * This function calculates the new values of origin, and updates the resolution on a mouse press event.
		 */
		@Override
		public void mousePressed(MouseEvent event){
			int newXCenter = event.getX();
			int newYCenter = event.getY();
			double centerReal = (newXCenter - originX_)/resolution_;
			double centerImag = (originY_ - newYCenter)/resolution_;
			// TODO: fix this naive approach to a more accurate one, his causes artifacts to generate occassionally
			switch(event.getButton()){
				case MouseEvent.BUTTON1:
					resolution_ = resolution_* zoomMultiplier;
					break;
				case MouseEvent.BUTTON3:
					resolution_ = resolution_ / zoomMultiplier;
					break;
			}
			originX_ = width_/2  - centerReal * resolution_;
			originY_ = height_/2 + centerImag * resolution_;
			canvasRef_.regenerateFractal();
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
			double tempReal;
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
					// TODO: using a round to to int, to keep axes, reconsider keeping originX and originY as int.
					double realCoordinate = (Math.round((i - originX_)))/resolution_;
					double imagCoordinate = (Math.round((originY_ - j)))/resolution_;
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

		/*
		 * Below are some unused functions that I have to define (as empty in this case), due to implementing from MouseListener.
		 */
		@Override
		public void mouseClicked(MouseEvent event){}
		@Override
		public void mouseEntered(MouseEvent event){}
		@Override
		public void mouseReleased(MouseEvent event){}
		@Override
		public void mouseExited(MouseEvent event){}

	}

}
