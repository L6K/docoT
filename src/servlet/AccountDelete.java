package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDeleteLogic;
import model.AllTweetCleanLogic;
import model.User;

/**
 * Servlet implementation class AccountDelete
 */
@WebServlet("/AccountDelete")
public class AccountDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/delete.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User usr = new User();
		usr = (User) session.getAttribute("loginUser");
		String name = usr.getName();
		String pass = usr.getPass();
		String passCheck = request.getParameter("passCheck");
		if(pass.equals(passCheck)){
			User a = new User(name,pass);
			AccountDeleteLogic adl = new AccountDeleteLogic();
			boolean result = adl.execute(a);
			if(result){
				AllTweetCleanLogic atcl = new AllTweetCleanLogic();
				boolean result2 = atcl.execute(a);
				if(result2){
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/deleteResult.jsp");
		   			rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/deleteFailure.jsp");
		   			rd.forward(request, response);
				}

			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/deleteFailure.jsp");
	   			rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/deleteFailure.jsp");
   			rd.forward(request, response);
		}

	}

}
