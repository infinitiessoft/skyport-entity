package com.infinities.skyport.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infinities.skyport.compute.entity.adapter.ISO8601DateAdapter;
import com.infinities.skyport.compute.entity.serializer.ShortSelfRecursiveSerializer;
import com.infinities.skyport.entity.TaskEventLog.Status;
import com.infinities.skyport.view.Views;

/**
 * TaskEvent generated by hbm2java
 */
@Entity
@Table(name = "TASKEVENT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TaskEvent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@XmlTransient
	private Long id;
	@JsonView(Views.Basic.class)
	@JsonSerialize(using = ShortSelfRecursiveSerializer.class)
	private User user;
	@JsonView(Views.Short.class)
	private String cmd;
	@JsonView(Views.ExtendedShort.class)
	@XmlJavaTypeAdapter(value = ISO8601DateAdapter.class)
	private Date registerdate;
	@XmlTransient
	private String config;
	@JsonView(Views.Full.class)
	private String para;
	@XmlTransient
	private Set<TaskEventLog> taskEventLogs = new HashSet<TaskEventLog>(0);
	@XmlTransient
	private int version;


	public TaskEvent() {
	}

	public TaskEvent(User user, String cmd, Date registerdate, String config, String para) {
		this.user = user;
		this.cmd = cmd;
		this.registerdate = registerdate;
		this.config = config;
		this.para = para;
		// this.status = status;
	}

	@XmlTransient
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CMD", length = 80)
	public String getCmd() {
		return this.cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTERDATE", length = 30)
	public Date getRegisterdate() {
		return this.registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	@XmlTransient
	@Column(name = "CONFIG", length = 40)
	public String getConfig() {
		return this.config;
	}

	// TODO should be replace by config
	@Transient
	public String getPool() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	// @Column(name = "STATUS", length = 20)
	@Transient
	public Status getStatus() {
		return this.getTaskEventLogs().isEmpty() ? Status.Initiazing : this.getTaskEventLogs().iterator().next().getStatus();
	}

	// public void setStatus(Status status) {
	// this.status = status;
	// }

	@Column(name = "PARA", length = 256)
	public String getPara() {
		return this.para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taskEvent", cascade = CascadeType.REMOVE)
	@OrderBy("id DESC")
	public Set<TaskEventLog> getTaskEventLogs() {
		return this.taskEventLogs;
	}

	@XmlTransient
	public void setTaskEventLogs(Set<TaskEventLog> taskEventLogs) {
		this.taskEventLogs = taskEventLogs;
	}

	@JsonView(Views.Short.class)
	@XmlElement(name = "eventid")
	@Transient
	public int getEventid() {
		return getId().intValue();
	}

	@XmlTransient
	@Version
	@Column(name = "OPTLOCK")
	public int getVersion() {
		return version;
	}

	@XmlTransient
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaskEvent other = (TaskEvent) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	// @Override
	// public String toString() {
	// return "TaskEvent [id=" + id + ", cmd=" + cmd + ", registerdate=" +
	// registerdate + ", config=" + config
	// + ", status=" + status + ", para=" + para + ", version=" + version + "]";
	// }

	@XmlTransient
	public static final TaskEvent getInitializedEvent(User user, String msg, String config) {
		TaskEvent event = new TaskEvent(user, msg, new Date(), config, "");
		return event;
	}

}
