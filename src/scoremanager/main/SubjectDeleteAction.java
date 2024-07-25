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

        HttpSession session = req.getSession();// セッション

        String cd = req.getParameter("cd");// 科目コードの取得
        Teacher teacher = (Teacher)session.getAttribute("user");
        System.out.println(cd);

        SubjectDao sDao = new SubjectDao();// インスタンス化
        Subject sub = sDao.get(cd, teacher.getSchool());// 削除対象の科目詳細データを取得

        req.setAttribute("sub_date", sub);
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}
