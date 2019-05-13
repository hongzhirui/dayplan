package my;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import af.swing.image.AfImageView;

public class ThumbButton extends AfImageView
{
	public Image image;
	
	public ThumbButton(File file) {
		try {
			image = ImageIO.read(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.setImage(image);
		this.setScaleType(AfImageView.FIT_CENTER);
	}

}
