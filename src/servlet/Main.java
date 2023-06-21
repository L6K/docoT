package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// つぶやきリストをアプリケーションスコープから取得（旧）
//		ServletContext application = this.getServletContext();
//		List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
//
//		// 取得できなかった場合は、つぶやきリストを新規作成して
//		// アプリケーションスコープに保存
//		if(mutterList == null){
//			mutterList = new ArrayList<>();
//			application.setAttribute("mutterList", mutterList);
//		}

		// リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList= getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");

		// ログインしていないなら
		// （厳密にはloginUserインスタンスが
		//   セッションスコープに保存されていないなら）
		if(loginUser == null){

			// リダイレクト
			response.sendRedirect("/docoTsubu/");

		}else{ // ログインしているなら

			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値チェック
		if(text != null && text.length() != 0){
			// アプリケーションスコープに保存されたつぶやきリスト取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");

			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			// つぶやきをつぶやきリストに追加（旧）
//			Mutter mutter = new Mutter(loginUser.getName(), text);
//			PostMutterLogic postMutterLogic = new PostMutterLogic();
//			postMutterLogic.execute(mutter, mutterList);

			// DB連携方針のリストに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

			// アプリケーションスコープにつぶやきリストを保存
			application.setAttribute("mutterList", mutterList);
		}else{

			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");

		}

		// つぶやきリストをリクエストスコープへ格納
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
