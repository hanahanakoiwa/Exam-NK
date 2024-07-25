package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action{
  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
	// セッションを 取得
    HttpSession session = req.getSession();
    Teacher teacher = (Teacher)session.getAttribute("user");

    req.getRequestDispatcher("subject_create.jsp").forward(req,res);
  }
}