package JSF2.Beans;



import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by 聪聪 on 2018/4/20 0020.
 */
@Named(value = "courseName")
@ApplicationScoped
public class CourseNameBean {
    private PreparedStatement studentStatement = null;
    private String choice;
    private String[] titles;


    public CourseNameBean() {
        initializeJdbc();
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.80.137/javabook", "scott", "tiger");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title FROM Course");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList titleList = new ArrayList();
            while (resultSet.next()) {
                titleList.add(resultSet.getString(1));
            }

            titles = new String[titleList.size()];
            titleList.toArray(titles);

            studentStatement = connection.prepareStatement("SELECT Student.ssn, " +
                    "Student.firstName, Student.mi, Student.lastName, " +
                    "Student.phone, Student.birthDate, Student.street, " +
                    "FROM Student, Enrollment, Course " +
                    "WHERE Course.title = ? " +
                    "AND Student.ssn = Enrollment.ssn " +
                    "AND Enrollment.courseId = Course.courseId;" +
                    "");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getTitles() {
        return titles;
    }
    public String getChoice() {
        return choice;
    }
    public void setChoice(String choice) {
        this.choice = choice;
    }
    public ResultSet getStudents() throws SQLException {
        if (choice == null)
            if (titles == null)
                return null;
            else studentStatement.setString(1, titles[0]);
        else studentStatement.setString(1, choice);
        return studentStatement.executeQuery();
    }

    public static void main(String[] args) {
        new CourseNameBean();
    }


}
