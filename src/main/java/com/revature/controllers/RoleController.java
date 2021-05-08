package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Role;
import com.revature.services.RoleService;

public class RoleController {
	
	private RoleService rService = new RoleService();
	private ObjectMapper om = new ObjectMapper();

	public void getAllRoles(HttpServletResponse resp) throws IOException{
		
		List<Role> roleList = rService.getAllRoles();
		
		String json = om.writeValueAsString(roleList);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		//set as 200 bc OK
		
	}
	
	public void getRoleByRoleId(HttpServletResponse resp, int roleId) throws IOException{
		
		Role role = rService.findByRoleId(roleId);
		
		String json = om.writeValueAsString(role);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}
	
	public void getRoleByRoleName(HttpServletResponse resp, String roleName) throws IOException{
		
		Role role = rService.findByRole(roleName);
		
		String json = om.writeValueAsString(role);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}
}
