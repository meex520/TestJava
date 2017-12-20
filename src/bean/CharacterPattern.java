package bean;

import java.util.regex.Pattern;

public class CharacterPattern {

	// 正文正则匹配表达式
	private Pattern contentPat;
	// 标题正则表达式
	private Pattern titlePat;
	// 书名正则表达式
	private Pattern bookNamePat;
	// 下一章正则表达式
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
