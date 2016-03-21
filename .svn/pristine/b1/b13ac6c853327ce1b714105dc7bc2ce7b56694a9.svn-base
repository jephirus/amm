package cn.jxust.springsecurity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.jxust.base.model.Authority;
import cn.jxust.base.model.Role;
import cn.jxust.base.service.UserService;
import cn.jxust.utils.SpringContextUtils;

/**
 * 该类的主要作用是为Spring Security提供�?��经过用户认证后的UserDetails�? 该UserDetails包括用户名�?密码、是否可用�?是否过期等信息�?
 */
@SuppressWarnings("deprecation")
public class JxustUserDetailServiceImpl implements UserDetailsService
{
	// 取出数据库中username的数�?封装成spring security的user(UserDetails),并返�?
	// User model的设计：user对象只需提供用户名密�?以及从数据库中取出的users�?��有的权限即可,其他根据实际�?��定义
	// 用户密码验证使用拦截器MyUsernamePasswordAuthenticationFilter
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserService userService = (UserService) SpringContextUtils.getBean("userService");
		cn.jxust.base.model.User users = userService.getUserByName(username);
		// 从数据库取出users�?��有的权限
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		// 封装成spring security的user
		User userdetail = new User(users.getUserName(), users.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		return userdetail;
	}

	/**/
	// 取得用户的权限Set<GrantedAuthority>(即封装了用户user所有角色role的类的集合)
	private Set<GrantedAuthority> obtionGrantedAuthorities(cn.jxust.base.model.User user)
	{
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Role> userRole = user.getUserRole();

		// 角色---------->权限: 用户具有的角色roles可以访问哪些资源resource,访问这些资源的权限authSet
		for (Role tmpUserRole : userRole)
		{
			if (tmpUserRole != null)
			{
				List<Authority> tmpAuth = tmpUserRole.getResources();
				for (Authority auth : tmpAuth)
				{
					if (auth != null)
					{
						authSet.add(new GrantedAuthorityImpl(auth.getAuthority()));
						System.out.println("=====User Authority:====" + auth.getAuthority());
					}

				}
			}
		}

		// 授予用户基本权限
		authSet.add(new GrantedAuthorityImpl("ROLE_LOGIN"));

		return authSet;
	}

}
