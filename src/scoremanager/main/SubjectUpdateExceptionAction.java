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

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        Map<String, String> errors = new HashMap<>();// エラーメッセージ

        //変更内容の保存
        String cd = req.getParameter("cd");// 科目コードの取得
        String name = req.getParameter("name");// 科目名の取得
        SubjectDao sDao = new SubjectDao();// 科目Dao
        Subject subjects = sDao.get(cd, teacher.getSchool());

        // 保存するデータをSubjectにセット
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
            // 変更内容の保存
            sDao.save(sub);

            req.getRequestDispatcher("subject_update_done.jsp").forward(req,res);
        }
    }
}
