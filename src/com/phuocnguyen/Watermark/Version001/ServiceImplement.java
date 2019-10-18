package com.phuocnguyen.Watermark.Version001;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ServiceImplement implements Service {

	@Override
	public void addTextWatermark(String content, File initialImage, File destinationImage) {
		int width;
		int height;

		try {
			BufferedImage bufferedImage = ImageIO.read(initialImage);
			Graphics2D graphics2d = (Graphics2D) bufferedImage.getGraphics();
			/*
			 * initializes necessary graphic properties
			 */
			AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
			graphics2d.setComposite(alphaComposite);
			graphics2d.setColor(Color.BLUE);
			graphics2d.setFont(new Font("Times New Roman", Font.BOLD, 40));
			FontMetrics fontMetrics = graphics2d.getFontMetrics();
			Rectangle2D rectangle2d = fontMetrics.getStringBounds(content, graphics2d);
			/*
			 * calculates the coordinate where the String is painted
			 */
			width = (bufferedImage.getWidth() - (int) rectangle2d.getWidth()) / 2;
			height = bufferedImage.getHeight() / 2;
			/*
			 * paints the textual water-mark
			 */
			graphics2d.drawString(content, width, height);
			ImageIO.write(bufferedImage, "png", destinationImage);
			graphics2d.dispose();
			System.out.println("done!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void callService(String content, String input, String output) {
		File initial = new File(input);
		File destinational = new File(output);
		addTextWatermark(content, initial, destinational);
	}

}
