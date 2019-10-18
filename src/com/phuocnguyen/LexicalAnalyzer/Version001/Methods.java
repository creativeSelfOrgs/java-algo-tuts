package com.phuocnguyen.LexicalAnalyzer.Version001;

import java.io.IOException;
import java.util.List;

public interface Methods {
	public List<String> readFileText(String fileName) throws IOException;

	public void writeToFileUsingBufferWriter(String name_file, String contents) throws IOException;

	public void streamToken(String path, String output) throws IOException;

	public boolean isMatchesIdentifier(String pattern);

	public boolean isMatchesIntegerConstant(String pattern);

	public boolean isMatchesCharConstant(String pattern);

	public boolean isMatchesStringConstant(String pattern);

	public boolean isMatchesEOF(String pattern);
}
