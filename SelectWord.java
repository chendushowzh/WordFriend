package wordFriend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Integer t = Integer.parseInt(request.getParameter("times"));
	    WordService wordService = new WordService();
	    List<Vocabulary> wordList = wordService.orderByShengshu(t);
	    request.setAttribute("wordList", wordList);
	    request.setAttribute("times", t);
	    request.getRequestDispatcher("/shengshu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
