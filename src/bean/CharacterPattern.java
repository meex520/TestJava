package bean;

import java.util.regex.Pattern;

public class CharacterPattern {

	// ��������ƥ����ʽ
	private Pattern contentPat;
	// ����������ʽ
	private Pattern titlePat;
	// ����������ʽ
	private Pattern bookNamePat;
	// ��һ��������ʽ
	private Pattern nextPat;
	
	public Pattern getContentPat() {
		return contentPat;
	}
	public void setContentPat(Pattern contentPat) {
		this.contentPat = contentPat;
	}
	public Pattern getTitlePat() {
		return titlePat;
	}
	public void setTitlePat(Pattern titlePat) {
		this.titlePat = titlePat;
	}
	public Pattern getBookNamePat() {
		return bookNamePat;
	}
	public void setBookNamePat(Pattern bookNamePat) {
		this.bookNamePat = bookNamePat;
	}
	public Pattern getNextPat() {
		return nextPat;
	}
	public void setNextPat(Pattern nextPat) {
		this.nextPat = nextPat;
	}
}
