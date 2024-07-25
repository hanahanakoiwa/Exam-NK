package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	// セッションを取得
        HttpSession session = req.getSession();

        String cd = req.getParameter("cd");
        Teacher teacher = (Teacher)session.getAttribute("user");
        System.out.println(cd);

        SubjectDao sDao = new SubjectDao();
        Subject sub = sDao.get(cd, teacher.getSchool());

        req.setAttribute("sub_date", sub);
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}
