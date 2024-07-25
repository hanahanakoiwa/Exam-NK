package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action{
  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{

    HttpSession session = req.getSession();
    Teacher teacher = (Teacher)session.getAttribute("user");

    ClassNumDao class_num = new ClassNumDao();
    List<String> class_list = class_num.filter(teacher.getSchool());

    LocalDate todaysDate = LocalDate.now();
    int year = todaysDate.getYear();

    List<Integer> entYearSet = new ArrayList<>();

    for(int i = year - 10; i < year + 11; i++){
      entYearSet.add(i);
    }

    req.setAttribute("ent_year_set",entYearSet);

    req.setAttribute("class_select",class_list);

    req.getRequestDispatcher("student_create.jsp").forward(req,res);
  }
}