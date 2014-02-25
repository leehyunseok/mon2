package kr.co.hdable.service.impl; 

import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import kr.co.hdable.service.UserService;
import kr.co.hdable.vo.ADUserVO;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public HashMap<String, String> getUserInfo() {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("department", "인프라");
		map.put("displanName", "이현석");
		map.put("title", "과장");
		map.put("mail", "shellsn@youfirst.co.kr");
		map.put("telehoneNumber", "010-8946-1066");
		map.put("mobile", "0386");

		return map;

	}

	@Override
	public HashMap<String, String> getUserInfo(ADUserVO vo)  {

		/*
		 * HashMap<String,String> map = new HashMap<String,String>() ;
		 * map.put("department","인프라"); map.put("displanName","이현석");
		 * map.put("title","과장"); map.put("mail","shellsn@youfirst.co.kr");
		 * map.put("telehoneNumber","010-8946-1066"); map.put("mobile","0386");
		 * return map;
		 */
		HashMap<String, String> map = new HashMap<String, String>();

		Hashtable<String, String> env = new Hashtable<String, String>(5, 0.75f);

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://128.16.248.33:389");// 운영2
		// env.put(Context.PROVIDER_URL, "ldap://128.16.248.32:389");//운영1
		env.put(Context.SECURITY_PRINCIPAL,"uid=S.username,ou=임직원,ou=People,dc=hds,dc=net,dc=com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "hds\\" + vo.getUser());
		env.put(Context.SECURITY_CREDENTIALS, vo.getPass());
		env.put(Context.REFERRAL, "follow");

		LdapContext ctx = null;
		// String returnedAtts[]
		// ={"cn","badPasswordTime","badPwdCount","company","customLevelCode","department","description","displayName","employeeID","LastLogon","logonCount","mail","mailNickName","title"};
		String returnedAtts[] = { "department", "displayName", "employeeID","mail", "title", "mobile", "telephoneNumber" };

		// String searchFilter = "(&(objectClass=user)(objectClass=person))";
		String searchFilter = "cn=" + vo.getUser();
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		constraints.setReturningAttributes(returnedAtts);
		NamingEnumeration<SearchResult> results = null;		
		
		try {
			ctx = new InitialLdapContext(env, null);

			results = ctx.search("ou=People,dc=hds,dc=net", searchFilter,constraints);

			while (results.hasMore()) {
				SearchResult sr = results.next();
				Attributes attrs = sr.getAttributes();
				NamingEnumeration<String> ids = attrs.getIDs();
				while (ids.hasMore()) {
					String id = ids.next();
					Attribute idattr = attrs.get(id);
					map.put(id, (String) idattr.get(0));
				}
			}

			map.put("message", "success");

			vo.setUser("");
			vo.setPass("");			

		} catch (Exception e) {
			map.put("message", "fail");

		} finally {
			try {
				ctx.close();
			} catch (Exception e) {
				System.out.println("AD Login FAIL");
			}
		}

		
		return map;
	}

}
