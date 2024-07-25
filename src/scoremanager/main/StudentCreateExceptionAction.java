package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExceptionAction extends Action{

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();

        int ent_year = Integer.parseInt(req.getParameter("ent_year"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String class_num = req.getParameter("class_num");

        if(ent_year == 0){
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", class_num);

            req.getRequestDispatcher("StudentCreate.action").forward(req, res);

        }

        Student stu = new Student();
        stu.setEntYear(ent_year);
        stu.setNo(no);
        stu.setName(name);
        stu.setClassNum(class_num);
        stu.setAttend(true);
        stu.setSchool(((Teacher)session.getAttribute("user")).getSchool());
        StudentDao sDao = new StudentDao();
        boolean flag = sDao.save(stu);

        req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
        
    }
    
}