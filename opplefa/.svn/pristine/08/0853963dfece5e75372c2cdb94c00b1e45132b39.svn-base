package com.opple.fa.utils;

import com.opple.security.entity.User;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapUtil {
    private static String ldapUri = "ldap://opple.com:389";
    private static String usersContainer = "DC=opple,DC=com";

    public static boolean LdapAuth(User user) throws Exception {
        boolean ispass = false;

        //username = "opple\\mdsadmin";
        //password = "mdsadmin";

        //user.setUserName();

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUri);
        env.put(Context.REFERRAL, "follow");
        env.put(Context.SECURITY_PRINCIPAL, "opple\\" + user.getUserCode());
        env.put(Context.SECURITY_CREDENTIALS, user.getPwd());
        DirContext ctx = null;
        try {
            ctx = new InitialDirContext(env);
           /* SearchControls ctls = new SearchControls();
	        String[] attrIDs = {"cn"};
	        ctls.setReturningAttributes(attrIDs);
	        ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

	        NamingEnumeration answer = ctx.search( usersContainer, "(objectclass=group)",ctls );
	        while(answer.hasMore()) {
	            SearchResult rslt = (SearchResult)answer.next();
	            Attributes attrs = rslt.getAttributes();
	            System.out.println(attrs.get("cn"));
	        }
	*/
            ctx.close();
            ispass = true;
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }
        return ispass;
    }

}
