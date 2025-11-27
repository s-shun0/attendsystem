package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {

            // ex: LoginExecute.action → LoginExecuteAction

            String path = req.getServletPath().substring(1);

            // パッケージ名 Main を付ける

            String name = "Main"+ path.replace(".action", "Action").replace('/', '.');

            // Action クラスをロード

            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            // 実行

            action.execute(req, res);

        } catch (Exception e) {

            e.printStackTrace();

            req.getRequestDispatcher("/error.jsp").forward(req, res);

        }

    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse res)

            throws ServletException, IOException {

        doGet(req, res);

    }

}
