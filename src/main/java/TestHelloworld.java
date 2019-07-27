
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.apache.shiro.authz.UnauthorizedException;


public class TestHelloworld {
    @Test
   public void  test(){
        //获SecurityManager工厂，这里我们使用配置文件初始化SecutityManage
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //得到SecurityManager实例，并且绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //我们拿到Subject 然后创建用户名和密码验证Token
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken("zhang","123");

        try {
            //进行验证
            subject.login(token);

        }catch (AuthenticationException e){
            //如果验证失败就报错 然后我们打印
            System.out.println("报错了");

        }
        //进行断言
        Assert.assertEquals(true, subject.isAuthenticated());
        System.out.println("SUCCESS");
        //退出登录
        subject.logout();
    }
//    @Test
//    public void testHasRole(){
//        login("classpath:shiro-role.ini","zhang","123");
//
//              }
    @Test
    public void ss(){
        String str ="Helloworld";
        String salt="123";
        String md5=new Md5Hash(str,salt).toString();
        System.out.println(md5);

    }


}
