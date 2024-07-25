package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	// 現在のセッションを取得
    	HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        School school = teacher.getSchool();


        String cd = req.getParameter("cd");

        SubjectDao sDao = new SubjectDao();

        Subject sub = sDao.get(cd, school);

        req.setAttribute("cd", sub.getCd());
        req.setAttribute("name", sub.getName());

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}