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
import com.infinities.skyport.compute.entity.AbstractEntity;
import com.infinities.skyport.compute.entity.serializer.BasicSelfRecursiveSerializer;
import com.infinities.skyport.view.Views;

/**
 * RolePermission generated by hbm2java
 */
@Entity
@Table(name = "PERMISSION_OPERATION")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PermissionOperation extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "name")
	private String alias;
	@XmlTransient
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);
	@XmlTransient
	private PermissionOperation parent;
	private Set<PermissionOperation> permissionOperations = new HashSet<PermissionOperation>(0);


	public PermissionOperation() {
	}

	public PermissionOperation(String desc, String alias, PermissionOperation parent) {
		setDesc(desc);
		this.alias = alias;
		this.parent = parent;
	}

	@XmlElement(name = "permissionid")
	@Transient
	public int getPermissionId() {
		return getId().intValue();
	}

	@Column(name = "ALIAS", length = 256)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionOperation", cascade = CascadeType.REMOVE)
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	@XmlTransient
	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTID")
	public PermissionOperation getParent() {
		return parent;
	}

	@XmlTransient
	public void setParent(PermissionOperation parent) {
		this.parent = parent;
	}

	@JsonSerialize(using = BasicSelfRecursiveSerializer.class)
	@JsonView(Views.Full.class)
	@XmlElement(name = "permission")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.REMOVE)
	public Set<PermissionOperation> getPermissionOperations() {
		return permissionOperations;
	}

	public void setPermissionOperations(Set<PermissionOperation> permissionOperations) {
		this.permissionOperations = permissionOperations;
	}

	@XmlElement(name = "parentid")
	@Transient
	public int getParentid() {
		if (parent != null) {
			return parent.getId().intValue();
		} else {
			return -1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		PermissionOperation other = (PermissionOperation) obj;
		if (alias == null) {
			if (other.alias != null) {
				return false;
			}
		} else if (!alias.equals(other.alias)) {
			return false;
		}
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		return true;
	}

}