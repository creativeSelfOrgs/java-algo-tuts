package com.phuocnguyen.Watermark.Version001;

public class Main {

	public static void main(String[] args) {
		/*
		 * example: input = "/home/david/Pictures/Wallpapers/001.png"; output =
		 * "/home/david/Pictures/Wallpapers/New-001.png";
		 */
		String pathSource = "/home/david/Pictures/Wallpapers/001.png";
		String pathDestination = "/home/david/Pictures/Wallpapers/New-001.png";
		ServiceImplement serviceImplement = new ServiceImplement();
		serviceImplement.callService("@phuocnguyenit97", pathSource, pathDestination);
	}

}
