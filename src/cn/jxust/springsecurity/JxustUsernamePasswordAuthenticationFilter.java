package cn.jxust.springsecurity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.jxust.base.model.User;
import cn.jxust.base.service.UserService;
import cn.jxust.utils.SpringContextUtils;

public class JxustUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
	{
		if (!request.getMethod().equals("POST")) { throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod()); }

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		// MD5加密
		// String password = processMd5(obtainPassword(request));

		// 验证用户账号与密码是否对�?
		username = username.trim();
		//System.out.println("========request.username:" + username + "----->request.password:" + password);

		UserService userService = (UserService) SpringContextUtils.getBean("userService");
		User users = userService.getUserByName(username);  // 用户登录时输入电话号码，通过电话号进行登录。

		try {
			if (users == null)
			{
				response.sendRedirect(request.getContextPath()+"/login.php?msg=0");  
				return null;
			}
			else if (!users.getPassword().equals(password))
			{
				response.sendRedirect(request.getContextPath()+"/login.php?msg=1");  
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println("========USER.NAME:" + users.getUserName() +"------>USER.PASSWORD:" + users.getPassword());

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// 允许子类设置详细属�?
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request)
	{
		String obj = request.getParameter(USERNAME);
		try
		{
			obj = new String(obj.getBytes("iso-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request)
	{
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	public String processMd5(String rawPass)
	{

		// 直接指定待采用的加密算法（MD5）
		Md5PasswordEncoder mdpeMd5 = new Md5PasswordEncoder();
		// 生成32位的Hex版, 这也是encodeHashAsBase64的默认值
		mdpeMd5.setEncodeHashAsBase64(true);
		return mdpeMd5.encodePassword(rawPass, null);
	}

}
