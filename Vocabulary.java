package wordFriend;

public class Vocabulary {
	private String word ;
	private Integer history;
	private Integer t;
	private Integer num;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Integer getHistory() {
		return history;
	}
	public void setHistory(Integer history) {
		this.history = history;
	}
	public Integer getT() {
		return t;
	}
	public void setT(Integer t) {
		this.t = t;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Vocabulary(String word, Integer history, Integer t) {
		super();
		this.word = word;
		this.history = history;
		this.t = t;
	}
	
	public Vocabulary(String word, Integer history, Integer t, Integer num) {
		super();
		this.word = word;
		this.history = history;
		this.t = t;
		this.num = num;
	}
	public void numInc() {
		this.num++;
	}
}
