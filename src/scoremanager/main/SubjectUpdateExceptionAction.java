package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectUpdateExceptionAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	// 現在のセッションを取得
    	HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        Map<String, String> errors = new HashMap<>();// エラー

        //変更内容 の保存
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        SubjectDao sDao = new SubjectDao();
        Subject subjects = sDao.get(cd, teacher.getSchool());

        //インスタンスを作成
        Subject sub = new Subject();
        sub.setCd(cd);
        sub.setName(name);
        sub.setSchool(school);

        if (subjects == null) {
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            System.out.println("値がありません");
            errors.put("errors", "科目が存在しません。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        } else {


        	sDao.save(sub);

            req.getRequestDispatcher("subject_update_done.jsp").forward(req,res);

        }
    }
}
