package com.phuocnguyen.LexicalAnalyzer.Version004;

public class Token {

	public final int id;
	public final String image;
	public final Location begin;
	public final Location end;

	public Token(String image, int id, Location begin, Location end) {
		this.id = id;
		this.image = image;
		this.begin = begin;
		this.end = end;
	}

	public String toString() {
		return "['" + image + "' id=" + id + " " + begin + ".." + end + "]";
	}

}
