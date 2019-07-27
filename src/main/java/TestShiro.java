import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class TestShiro {

    public static void main(String[] args) {
        User zhang3 = new User();
        zhang3.setName("zhang");
        zhang3.setPassword("123");

        User li4 = new User();
        li4.setName("lisi");
        li4.setPassword("abcde");

        User wang5 = new User();
        wang5.setName("wang5");
        wang5.setPassword("wrongpassword");

        List<User> users = new ArrayList();

        users.add(zhang3);

        users.add(wang5);
        String roleAdmin="admin";
        String roleProductManger="productManager";

        List<String> roles= new ArrayList<String>();

        for (User user:users){
            if(login(user)){
                System.out.println("登陆成功"+user.getName());
            }else{
                System.out.println("登录失败"+user.getName());
            }
        }

    }
    private static boolean login(User user) {
        Subject subject=getSubject(user);
        //如果已经登录过了，退出
        if(subject.isAuthenticated())
            subject.logout();

        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
        } catch (AuthenticationException e) {
            //验证错误
            return false;
        }

        return subject.isAuthenticated();
    }
    private static Subject getSubject(User user) {
        //加载配置文件，并获取工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取安全管理者实例
        SecurityManager sm = factory.getInstance();
        //将安全管理者放入全局对象
        SecurityUtils.setSecurityManager(sm);
        //全局对象通过安全管理者生成Subject对象
        Subject subject = SecurityUtils.getSubject();

        return subject;
    }

    private static boolean hasRole(User user, String role) {
        Subject subject = getSubject(user);
        return subject.hasRole(role);
    }

    private static boolean isPermitted(User user, String permit) {
        Subject subject = getSubject(user);
        return subject.isPermitted(permit);
    }


}
