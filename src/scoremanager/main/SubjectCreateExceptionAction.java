package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExceptionAction extends Action{
	// セッションを取得
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        SubjectDao sDao = new SubjectDao();
        // エラー メッセージ
        Map<String, String> errors = new HashMap<>();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        errors.get(cd);

        Subject subjects = sDao.get(cd, teacher.getSchool());

        if (cd.length() != 3) {
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            System.out.println("３文字で入力");
            errors.put("errors", "科目コードは３文字で入力してください。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);

        } else if (subjects != null) {
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            System.out.println("値が重複");
            errors.put("errors", "科目コードが重複しています。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);

        } else {
            System.out.println("異常なし");
            Subject sub = new Subject();
            sub.setCd(cd);
            sub.setName(name);
            sub.setSchool(((Teacher)session.getAttribute("user")).getSchool());
            boolean flag = sDao.save(sub);
            //フォワード
            req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
        }

    }

}