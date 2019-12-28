package com.lyc.shiro;
import java.util.Date;

import com.lyc.shiro.entity.*;
import com.lyc.shiro.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ShiroApplicationTests {

	@Autowired
	private SysUsersService sysUsersService;

	@Autowired
	private SysPermissionsService sysPermissionsService;

	@Autowired
	private SysUsersRolesService sysUsersRolesService;

	@Autowired
	private SysRolesPermissionsService sysRolesPermissionsService;

	@Autowired
	private SysRolesService sysRolesService;


	@Test
	void contextLoads() {
	}

	@Test
	void testUserList() {
		log.info("user list:{}",sysUsersService.list());
	}

	@Test
	void testSaveUser() {


		//保存用户
		SysUsers sysUsers = new SysUsers();
		sysUsers.setUsername("admin");
		sysUsers.setPassword("123");
		sysUsers.setSalt("");
		sysUsers.setRoleId("");
		sysUsers.setLocked(0);
		sysUsers.setRegIp("");
		sysUsers.setLastLoginIp("");
		sysUsers.setLastLoginTime(new Date());
		sysUsers.setLoginCount(0);
		sysUsers.setCreateTime(new Date());
		sysUsers.setUpdateTime(new Date());
		sysUsers.setVersion(0);
		sysUsersService.save(sysUsers);

		// 保存角色
		SysRoles sysRoles = new SysRoles();
		sysRoles.setRole("管理员");
		sysRoles.setDescription("管理员");
		sysRoles.setPid(0L);
		sysRoles.setAvailable(0);
		sysRoles.setCreateTime(new Date());
		sysRoles.setUpdateTime(new Date());
		sysRolesService.save(sysRoles);

		// 保存权限
		SysPermissions sysPermissions = new SysPermissions();
		sysPermissions.setPermission("permissions:list");
		sysPermissions.setDescription("权限列表");
		sysPermissions.setRid(0L);
		sysPermissions.setType("1");
		sysPermissions.setUrl("/permissions/list");
		sysPermissions.setSort(0);
		sysPermissions.setExternal(0);
		sysPermissions.setParentId(0L);
		sysPermissions.setAvailable(0);
		sysPermissions.setIcon("");
		sysPermissions.setCreateTime(new Date());
		sysPermissions.setUpdateTime(new Date());
		sysPermissionsService.save(sysPermissions);

		// 角色 与 用户
		SysUsersRoles sysUsersRoles = new SysUsersRoles();
		sysUsersRoles.setUserId(sysUsers.getId());
		sysUsersRoles.setRoleId(sysRoles.getId());
		sysUsersRoles.setCreateTime(new Date());
		sysUsersRoles.setUpdateTime(new Date());
		sysUsersRolesService.save(sysUsersRoles);

		// 角色 与权限
		SysRolesPermissions sysRolesPermissions = new SysRolesPermissions();
		sysRolesPermissions.setRoleId(sysRoles.getId());
		sysRolesPermissions.setPermissionId(sysPermissions.getId());
		sysRolesPermissions.setCreateTime(new Date());
		sysRolesPermissions.setUpdateTime(new Date());


		sysRolesPermissionsService.save(sysRolesPermissions);
	}

}
