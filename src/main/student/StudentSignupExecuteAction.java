package main.student;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class StudentSignupExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("StudentSignupExecuteActionに入りました！");

		// リクエストパラメーターから学生情報取得
		String id = req.getParameter("sign_id");
		String name = req.getParameter("name");
		String email = req.getParameter("mail");
		String password = req.getParameter("sign_pass");
		String job = req.getParameter("course");
		
		String classNum = "";
		if (isNullOrBlank(req.getParameter("classnum")) == false) {
			// 教室番号が入力されていたら数値変換して保持する
			classNum = req.getParameter("classnum");
		}
		
		// テーブルに登録する前にバリデーションチェック
		Map<String, String> errors = new HashMap<>();

		
		if (isNullOrBlank(id)) {
			errors.put("1", "学籍番号が未入力です");
			req.setAttribute("errors", errors);
		}
		
		if (isNullOrBlank(name)) {
			errors.put("2", "名前が未入力です");
			req.setAttribute("errors", errors);
		}
		
		if (isValidEmail(email) == false) {
			errors.put("3", "メールアドレスの形式が不正です");
			req.setAttribute("errors", errors);
		}
		
		if (isNullOrBlank(password)) {
			errors.put("4", "パスワードが未入力です");
			req.setAttribute("errors", errors);
		}

		// エラーが無い場合のみ登録処理を実施
		if (errors.isEmpty()) {
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPass(password);
			user.setJob(job);
			user.setClassnum(classNum);
			
			// 学生情報をテーブルに登録
			UserDao userDao = new UserDao();
			userDao.insert(user);
		}

		// レスポンス値をセット
		req.setAttribute("sign_id", id);
		req.setAttribute("name", name);
		req.setAttribute("mail", email);
		req.setAttribute("password", password);
		req.setAttribute("course", job);
		req.setAttribute("classNum", classNum);
		
		System.out.println("StudentSignupExecuteAction終わります");
		
		// JSPへフォワード
		if (errors.isEmpty()) {
			// エラーがない場合、学生用サインアップ完了画面に遷移する
			req.getRequestDispatcher("student_signup_done.jsp").forward(req, res);
		} else {
			System.out.println(errors.get("1"));
			System.out.println(errors.get("2"));
			System.out.println(errors.get("3"));
			System.out.println(errors.get("4"));
			// 再度学生用サインアップ画面に遷移する
			req.getRequestDispatcher("StudentSignup.action").forward(req, res);
		}
	}
	
	/**
	 * 空文字およびnullチェック
	 * 
	 * @param value チェック対象の文字列
	 * @return 空文字またはnullだった場合true
	 * 
	 * */
	public static boolean isNullOrBlank(String value) {
	    return value == null || value.trim().isEmpty();
	}
	
	
	/**
     * 引数で指定された文字列が、メールアドレスとして
     * 一般的に正しい形式であるかを判定
     * 実在するメールアドレスかどうかは判定しない
     *
     * @param email チェック対象のメールアドレス
     * @return メールアドレスとして正しい形式の場合true
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        // 一般的なメールアドレス形式の正規表現
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return Pattern.matches(regex, email);
    }

}
