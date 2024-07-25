package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExceptionAction extends Action{

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        System.out.println("aaaaa");

        String cd = req.getParameter("cd");

        Subject sub = new Subject();
        sub.setCd(cd);
        System.out.println(cd);
        sub.setSchool(((Teacher)session.getAttribute("user")).getSchool());
        SubjectDao sDao = new SubjectDao();
        boolean flag = sDao.delete(sub);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

    }

}