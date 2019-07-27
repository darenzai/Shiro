import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class TestVideo {

    log.getClass("");
    public static void main(String[] args) {
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager=factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);


        Subject currentUser=SecurityUtils.getSubject();
        Session session=currentUser.getSession();

        session.setAttribute("name","darenzai");

        String value=(String)session.getAttribute("name");

        if(value!=null){
            System.out.println("我么获取到了"+value);
        }
    }






}
