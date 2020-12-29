import com.zzcedu.blog.dao.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author liu
 * @Date 2020/12/29 15:36
 */
public class TestCase {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao",UserDao.class);

    }
}
