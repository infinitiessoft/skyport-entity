/*******************************************************************************
 * Copyright 2015 InfinitiesSoft Solutions Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.infinities.skyport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "JOB")
public class Job implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private boolean start;
	private String cmd;
	private Serializable args;
	private String executorKey;
	private String distributedKey;
	private String uuid;
	private long eventid;
	private long createdAt;
	@XmlTransient
	private int version;


	public Job() {
	}

	public Job(String id, String cmd, Serializable args, String executorKey, String distributedKey, long eventid,
			String uuid, long createdAt) {
		this.id = id;
		this.cmd = cmd;
		this.args = args;
		this.executorKey = executorKey;
		this.distributedKey = distributedKey;
		this.eventid = eventid;
		this.uuid = uuid;
		this.createdAt = createdAt;
	}

	public Job(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID", length = 40, unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "START")
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	@Column(name = "CMD", length = 40)
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Lob
	@Column(name = "ARGS", length = 65535)
	public Serializable getArgs() {
		return args;
	}

	public void setArgs(Serializable args) {
		this.args = args;
	}

	@Column(name = "KEY", length = 256)
	public String getExecutorKey() {
		return executorKey;
	}

	public void setExecutorKey(String executorKey) {
		this.executorKey = executorKey;
	}

	@Column(name = "UUID", length = 40)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "EVENTID", length = 256)
	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	@Column(name = "CREATEDATE")
	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "DISTRIBUTEDKEY", length = 256)
	public String getDistributedKey() {
		return distributedKey;
	}

	public void setDistributedKey(String distributedKey) {
		this.distributedKey = distributedKey;
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
	public String toString() {
		return "Job [id=" + id + ", start=" + start + ", cmd=" + cmd + ", args=" + args + ", executorKey=" + executorKey
				+ ", distributedKey=" + distributedKey + ", uuid=" + uuid + ", eventid=" + eventid + ", createdAt="
				+ createdAt + ", version=" + version + "]";
	}

}
