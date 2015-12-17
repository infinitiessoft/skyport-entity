package com.infinities.skyport.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Sets;
import com.infinities.skyport.compute.entity.AbstractEntity;
import com.infinities.skyport.compute.entity.adapter.ISO8601DateAdapter;
import com.infinities.skyport.compute.entity.serializer.BasicSelfRecursiveSerializer;
import com.infinities.skyport.view.Views;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "USERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonView(Views.Short.class)
	private String name;
	@JsonView(Views.Basic.class)
	private String loginname;
	@XmlTransient
	private String password;
	@XmlTransient
	private String salt;
	@XmlTransient
	private String key;
	@XmlTransient
	private String secret;
	@JsonView(Views.Basic.class)
	@XmlJavaTypeAdapter(value = ISO8601DateAdapter.class)
	private Date lastlogin;
	@XmlTransient
	private Boolean enable = true;
	@XmlTransient
	private Organization organization;
	@XmlTransient
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	// private Set<UserUserGroup> userUserGroups = new
	// HashSet<UserUserGroup>(0);
	@XmlTransient
	private Set<TaskEvent> taskEvents = new HashSet<TaskEvent>(0);
	@XmlTransient
	private Set<Session> sessions = new HashSet<Session>(0);


	public User() {
	}

	public User(String name, String loginname, String password, String desc, String salt, String secret, String key,
			Date lastlogin, Boolean enable, Organization organization) {
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		setDesc(desc);
		this.salt = salt;
		this.lastlogin = lastlogin;
		this.enable = enable;
		this.secret = secret;
		this.key = key;
		this.setOrganization(organization);
	}

	@XmlElement(name = "userid")
	@Transient
	public int getUserId() {
		return getId().intValue();
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOGINNAME", length = 40)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@XmlTransient
	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	@XmlTransient
	public void setPassword(String password) {
		this.password = password;
	}

	@XmlTransient
	@Column(name = "SALT", length = 100)
	public String getSalt() {
		return this.salt;
	}

	@XmlTransient
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@XmlTransient
	@Column(name = "SECRET", length = 100)
	public String getSecret() {
		return this.secret;
	}

	@XmlTransient
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@XmlTransient
	@Column(name = "KEY", length = 100)
	public String getKey() {
		return this.key;
	}

	@XmlTransient
	public void setKey(String key) {
		this.key = key;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTLOGIN", length = 26)
	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	@XmlTransient
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	public Set<TaskEvent> getTaskEvents() {
		return this.taskEvents;
	}

	@XmlTransient
	public void setTaskEvents(Set<TaskEvent> taskEvents) {
		this.taskEvents = taskEvents;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	public Set<Session> getSessions() {
		return this.sessions;
	}

	@XmlTransient
	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	// @OneToMany(fetch = FetchType.LAZY, mappedBy =
	// "user",cascade=CascadeType.REMOVE)
	// public Set<UserUserGroup> getUserUserGroups() {
	// return userUserGroups;
	// }
	//
	// public void setUserUserGroups(Set<UserUserGroup> userUserGroups) {
	// this.userUserGroups = userUserGroups;
	// }

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

	@XmlElement(name = "groupid")
	@JsonView(Views.Full.class)
	@Transient
	public int getOrganizationId() {
		return organization.getId().intValue();
	}

	@JsonSerialize(using = BasicSelfRecursiveSerializer.class)
	@JsonView(Views.Full.class)
	@XmlElement(name = "role")
	@Transient
	public Set<Role> getRole() {
		Set<Role> roles = Sets.newHashSet();
		for (UserRole userRole : userRoles) {
			roles.add(userRole.getRole());
		}

		return roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enable == null) ? 0 : enable.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((lastlogin == null) ? 0 : lastlogin.hashCode());
		result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
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
		User other = (User) obj;
		if (enable == null) {
			if (other.enable != null) {
				return false;
			}
		} else if (!enable.equals(other.enable)) {
			return false;
		}
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (lastlogin == null) {
			if (other.lastlogin != null) {
				return false;
			}
		} else if (!lastlogin.equals(other.lastlogin)) {
			return false;
		}
		if (loginname == null) {
			if (other.loginname != null) {
				return false;
			}
		} else if (!loginname.equals(other.loginname)) {
			return false;
		}
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
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (salt == null) {
			if (other.salt != null) {
				return false;
			}
		} else if (!salt.equals(other.salt)) {
			return false;
		}
		if (secret == null) {
			if (other.secret != null) {
				return false;
			}
		} else if (!secret.equals(other.secret)) {
			return false;
		}
		return true;
	}

}
