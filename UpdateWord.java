package wordFriend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateWord extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Integer t = Integer.parseInt(request.getParameter("t"));
		 String word = request.getParameter("word");
		 String page = request.getParameter("method");
		 
		 
		 WordService wordService = new WordService();
		 wordService.updateWordT(word,t);
		 if(page.equals("history")) {
			 request.getRequestDispatcher("/History").forward(request, response);
		 }else if(page.equals("shengshu")){
			 request.setAttribute("times", t);
			 request.getRequestDispatcher("/SelectWord").forward(request, response); 
		 }else {
			
			 List <Vocabulary> wordlist  =(List<Vocabulary>) request.getSession().getAttribute("nowWords");
			 if(wordlist!=null) {
				 for(Vocabulary v:wordlist) {
					 if(v.getWord().equals(word)) {
						 v.setT(t);
						 break;
					 }
				 }
			 }
			 request.getSession().setAttribute("nowWords", wordlist);
			 request.getRequestDispatcher("/main.jsp").forward(request, response); 
		 }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
