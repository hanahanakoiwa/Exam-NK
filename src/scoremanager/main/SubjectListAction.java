package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();// セッション
        Teacher teacher = (Teacher)session.getAttribute("user");

        SubjectDao subDao = new SubjectDao();// 科目Dao

        // DBからデータ取得３
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得

        List<Subject> subjects = subDao.filter(teacher.getSchool());

        // リクエストにデータをセット
        req.setAttribute("subjects", subjects);

        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}
