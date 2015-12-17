package com.infinities.skyport.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Sets;
import com.infinities.skyport.compute.entity.AbstractEntity;
import com.infinities.skyport.compute.entity.serializer.BasicSelfRecursiveSerializer;
import com.infinities.skyport.view.Views;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "ROLES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Role extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	@XmlTransient
	private Boolean enable;
	@XmlTransient
	private Organization organization;
	@XmlTransient
	private Scope scope;
	@XmlTransient
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);
	@XmlTransient
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);


	public Role() {
	}

	public Role(String name, String desc, Boolean enable, Organization organization, Scope scope) {
		this.name = name;
		setDesc(desc);
		this.enable = enable;
		this.organization = organization;
		this.scope = scope;
	}

	@XmlElement(name = "roleid")
	@Transient
	public int getRoleid() {
		return getId().intValue();
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return this.enable;
	}

	@XmlTransient
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	@XmlTransient
	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	@XmlTransient
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATIONID")
	public Organization getOrganization() {
		return organization;
	}

	@XmlTransient
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCOPEID")
	public Scope getScope() {
		return this.scope;
	}

	@XmlTransient
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@XmlElement(name = "scopeid")
	@JsonView(Views.Full.class)
	@Transient
	public int getScopeId() {
		return scope.getId().intValue();
	}

	@XmlElement(name = "groupid")
	@JsonView(Views.Full.class)
	@Transient
	public int getOrganizationId() {
		return organization.getId().intValue();
	}

	@JsonSerialize(using = BasicSelfRecursiveSerializer.class)
	@JsonView(Views.Full.class)
	@XmlElement(name = "user")
	@Transient
	public Set<User> getUser() {
		Set<User> users = Sets.newHashSet();
		for (UserRole userRole : userRoles) {
			users.add(userRole.getUser());
		}

		return users;
	}

	@JsonSerialize(using = BasicSelfRecursiveSerializer.class)
	@JsonView(Views.Full.class)
	@XmlElement(name = "permission")
	@Transient
	public Set<PermissionOperation> getPermission() {
		Set<PermissionOperation> permissionOperations = Sets.newHashSet();
		for (RolePermission rolePermission : rolePermissions) {
			permissionOperations.add(rolePermission.getPermissionOperation());
		}

		return permissionOperations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (organization == null) {
			if (other.organization != null) {
				return false;
			}
		} else if (!organization.equals(other.organization)) {
			return false;
		}
		if (scope == null) {
			if (other.scope != null) {
				return false;
			}
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		return true;
	}

}