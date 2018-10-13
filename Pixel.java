import java.awt.Color;

public class Pixel{

	public Picture pic;

	public int x;

	public int y;

	public Pixel(Picture pic, int x, int y){
		this.pic = pic;
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getAlpha(){
		return ( (pic.getPixel(x, y) >> 24) & 0xff);
	}

	public int getRed(){
		return ( (pic.getPixel(x, y) >> 16) & 0xff );
	}

	public int getGreen(){
		return ( (pic.getPixel(x, y) >> 8) & 0xff);
	}

	public int getBlue(){
		return (pic.getPixel(x, y) & 0xff);
	}

	private int boundsCheck(int value){
		if(value<0){
			value = 0;
		}else if(value>255){
			value = 255;
		}

		return value;
	}

	public void setAlpha(int newAlpha){
		int alpha = boundsCheck(newAlpha);
		int rgb = (alpha << 24) + (getRed() << 16) 
					+ (getGreen() << 8) + getBlue();
		pic.setPixel(x, y, rgb);
	}

	public void setRed(int newRed){
		int red = boundsCheck(newRed);
		int rgb = (getAlpha() << 24) + (red << 16) 
					+ (getGreen() << 8) + getBlue();
		pic.setPixel(x, y, rgb);
	}

	public void setGreen(int newGreen){
		int green = boundsCheck(newGreen);
		int rgb = (getAlpha() << 24) + (getRed() << 16) 
					+ (green << 8) + getBlue();
		pic.setPixel(x, y, rgb);
	}

	public void setBlue(int newBlue){
		int blue = boundsCheck(newBlue);
		int rgb = (getAlpha() << 24) + (getRed() << 16) 
					+ (getGreen() << 8) + blue;
		pic.setPixel(x, y, rgb);
	}

	public Color getColor(){
		return newColor(getRed(), getGreen(), getBlue());
	}

	public void setColor(Color color){
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int rgb = (getAlpha() << 24) + (red << 16) 
					+ (green << 8) + blue;
		pic.setPixel(x, y, rgb);
	}

	public String toString(){
		return "Pixel at " + pic.title + "'s (" + x + ", " + y + "); " + 
				"ARGB is (" + getAlpha() + ", " + getRed() + ", " +
				getGreen() + ", " + getBlue() + ").";
	}
}