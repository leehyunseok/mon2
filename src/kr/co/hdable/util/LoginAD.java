package kr.co.hdable.util;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LoginAD {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		 Hashtable<String, String> env = new Hashtable<String, String>(5, 0.75f);
        
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    env.put(Context.PROVIDER_URL, "ldap://128.16.248.33:389");//운영2
//	    env.put(Context.PROVIDER_URL, "ldap://128.16.248.32:389");//운영1
        env.put(Context.SECURITY_PRINCIPAL,"uid=S.username,ou=임직원,ou=People,dc=hds,dc=net,dc=com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
	    env.put(Context.SECURITY_PRINCIPAL, "hds\\"+"20281979");
	    env.put(Context.SECURITY_CREDENTIALS, "7179sek!");
	    env.put(Context.REFERRAL, "follow");

/*
	    System.out.println("SECURITY_PRINCIPAL  Hash::"+env.get(Context.SECURITY_PRINCIPAL));
        System.out.println("Hash::"+env.get(Context.PROVIDER_URL));
        System.out.println("SECURITY_PRINCIPAL::"+env.get(Context.SECURITY_PRINCIPAL));
        System.out.println("SECURITY_CREDENTIALS::"+env.get(Context.SECURITY_CREDENTIALS));
*/
        
        
	    LdapContext ctx = null;
//	    String returnedAtts[] ={"cn","badPasswordTime","badPwdCount","company","customLevelCode","department","description","displayName","employeeID","LastLogon","logonCount","mail","mailNickName","title"};
	    String returnedAtts[] ={"department","displayName","employeeID","mail","title","mobile","telephoneNumber"};	    
	    
//	    String searchFilter = "(&(objectClass=user)(objectClass=person))";
	    String searchFilter = "cn="+ "20281979" ;

     	SearchControls constraints = new SearchControls();

			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			constraints.setReturningAttributes(returnedAtts);
			
			NamingEnumeration<SearchResult> results = null;	    
	    
	    

		try {
			ctx = new InitialLdapContext(env, null);
			results = ctx.search("ou=People,dc=hds,dc=net", searchFilter, constraints);
			while (results.hasMore()) {
				SearchResult sr = results.next();
				Attributes attrs = sr.getAttributes();
				NamingEnumeration<String> ids = attrs.getIDs();				

				while (ids.hasMore()) {
					String id = ids.next();
					Attribute idattr = attrs.get(id);
					System.out.println("#########################################");
					System.out.println("AttributeName : ("+id + ") Attribute Size (" + idattr.size() + ")");
					System.out.println("-----------------------------------------");
					if( idattr.size() > 1){
						for (int ix = 0; ix < idattr.size(); ++ix) {
							System.out.print(ix + "/" + idattr.get(ix) + ", ");
						}	
					} else {
						System.out.print( idattr.get(0) );
					}
					System.out.println();
				}
			}
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     	


			
			
			
/*			if(results != null  && results.hasMoreElements()) {				
				System.out.println("11111111111");
			} else {
				System.out.println("222222222");
			}*/
						
			 
			
	}

}


