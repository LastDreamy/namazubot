package Utils;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManipulation {
	public static BufferedImage img(){
		try {
			final String path = "C:\\Users\\Marcelo\\Documents\\RightHandMan Stuff\\Guides\\";
			final String name = "Gilga";
			final String version = "2";
			
			BufferedImage bi1 = ImageIO.read(new File(path + name + "1.png"));
			BufferedImage bi2 = ImageIO.read(new File(path + name + "2.png"));
			BufferedImage bi3 = ImageIO.read(new File(path + name + "3.png"));
			
		    File outputfile = new File(path + name + "V" + version + ".png");
		    ImageIO.write(merger(bi1, bi2, bi3), "png", outputfile);
		} catch (Exception e) {
			return null;
		} 
		return null;
	}
	
	public static BufferedImage merger(BufferedImage... images) throws IOException{ //.getGraphics().copyArea(5, 177, 577-5, 930-177, 577, 930);
		for(int i = 0; i < images.length; i++)
			images[i] = images[i].getSubimage(5, 177, 577-5, 930-177);
		BufferedImage res = new BufferedImage((577-5)*images.length, 930-177, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		int x = 0;
		for(int i = 0; i < images.length; i++){
	        g.drawImage(images[i], x, 0, null);
	        x += images[i].getWidth();
		}
		return res;
	}
}
