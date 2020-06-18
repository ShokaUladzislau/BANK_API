package com.VladProject.Models;

	public class Role {
		private int roleId; // primary key
		private String role; // not null, unique
		
	
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
		public Role(int roleId, String role) {
			this.roleId = roleId;
			this.role = role;
		}
		
		public Role() {
			this.roleId = 0;
			this.role = null;
		}
		
	}