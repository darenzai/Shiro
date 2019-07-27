import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class TestRealm implements Realm {
    public String getName() {
        return "TestRealm";
    }


    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username=(String) authenticationToken.getPrincipal();
        String passwd=new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(passwd)){
            throw new IncorrectCredentialsException();
        }

        //如果身份验证成功，返回一个Authentication
        return new SimpleAuthenticationInfo(username,passwd,getName());
    }
}
