package scoremanager.main;

import javax.servlet.http.HttpSession;

import bean.Teacher;

public class TestRegistExcuteAction extends Action {
	public void execute(HttpServlet req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);
		Teacher teacher = (teacher)session.getAttribute("user");
		School school = teacher.getSchool();
	}
}