package wordFriend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VocaTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		text=text.toLowerCase();
		StringTokenizer words = new StringTokenizer(text);
		List <Vocabulary> wordlist = new ArrayList<Vocabulary>();
		int num=0;
		
		while(words.hasMoreElements()){
			//1.如果当前数据库没有改单词就插入一条新的
			//2.如果数据库已存，更改其历史次数
			
			String s=words.nextToken();
			StringTokenizer strs = new StringTokenizer(s," ,.!?$@*[]()&%^#<>~/\"0123456789");
			while(strs.hasMoreElements()){
				String ss=strs.nextToken();
			if(ss.equals("$")||ss.equals("*")||ss.equals("'"))
				ss=null;
			boolean passed=false;
			if(wordlist!=null) {
			for(Vocabulary v:wordlist) {
				if(v.getWord().equals(ss)) {
					v.numInc();
					passed=true;
					break;
				}
			}
			}
			
			if(!passed) {
				wordlist.add(new Vocabulary(ss,0,0,1));
				num++;
			}
			
		    }
			
		}
		WordService wordService = new WordService();
		int n=0;
		for (Vocabulary now : wordlist) {
			Vocabulary word=null;
			
			if(now !=null)
				word = wordService.findByName(now.getWord());
			else {break;}
			
			if(word != null) {
				word.setHistory(word.getHistory()+now.getNum());
				wordService.updateWordHistory(word);
				now.setHistory(word.getHistory()-now.getNum());
			}else {
				Vocabulary w = new Vocabulary(now.getWord(),now.getNum(),0);
				wordService.addWord(w);
			}
				
		}
		request.getSession().setAttribute("nowWords", wordlist);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
