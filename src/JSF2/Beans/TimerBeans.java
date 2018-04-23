package JSF2.Beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author Administrator
 */
@Named(value = "timerBean")
@RequestScoped
public class TimerBeans {
    public String getTime1() {

        Student s = new Student();

        return new java.util.Date().toString();
    }
    
}
