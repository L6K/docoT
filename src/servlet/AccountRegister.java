package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountRegisterLogic;
import model.User;

/**
 * Servlet implementation class AccountRegister
 */
@WebServlet("/AccountRegister")
public class AccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		Account a= new Account(pass,name);
		AccountRegisterLogic arl = new AccountRegisterLogic();
		boolean result = arl.execute(a);

		if (result) {
			HttpSession session = request.getSession();
			User user = new User(name, pass);
			session.setAttribute("loginUser",user);
			//session.setAttribute("name", name);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("/docoTsubu/");
		}

	}

}
