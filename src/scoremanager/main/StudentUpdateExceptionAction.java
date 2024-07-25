package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;


public class StudentUpdateExceptionAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        //変更内容の保存
        int ent_year = Integer.parseInt(req.getParameter("ent_year"));// 入学年度の取得
        String no = req.getParameter("no");// 学生番号の取得
        String name = req.getParameter("name");// 氏名の取得
        String class_num = req.getParameter("class_num");// クラスの取得
        String si_attend = req.getParameter("si_attend");// 在学フラグ
        boolean attend = true;

        // 在学フラグがnullの場合
        if(si_attend == null) {
            attend = false;
        }

        // 保存するデータをStudentにセット
        Student stu = new Student();
        stu.setEntYear(ent_year);
        stu.setNo(no);
        stu.setName(name);
        stu.setClassNum(class_num);
        stu.setAttend(attend);

        // 変更内容の保存
        StudentDao sDao = new StudentDao();
        sDao.save(stu);

        req.getRequestDispatcher("student_update_done.jsp").forward(req,res);

    }
}
