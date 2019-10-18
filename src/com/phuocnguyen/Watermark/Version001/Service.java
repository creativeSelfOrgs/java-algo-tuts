package com.phuocnguyen.Watermark.Version001;

import java.io.File;

public interface Service {

	public void addTextWatermark(String content, File initialImage, File destinationImage);

	public void callService(String content, String input, String output);
}
