import com.zzcedu.blog.dao.ShareDao;
import com.zzcedu.blog.dao.UserDao;
import com.zzcedu.blog.entity.Share;
import com.zzcedu.blog.service.NoteServiceImpl;
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
    @Test
    public void test2(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        NoteServiceImpl noteServiceImpl = ac.getBean("noteServiceImpl", NoteServiceImpl.class);
        System.out.println(noteServiceImpl.searchShareNote("", 1));

    }
}
