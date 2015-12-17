package com.infinities.skyport.entity;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.Strings;
import com.infinities.skyport.compute.entity.AbstractVirtEntity;

public class Connection extends AbstractVirtEntity {

	private static final long serialVersionUID = 1L;

	private String info;
	private String instanceid;
	private String ip;
	private String port;
	private String ticket;
	private String token;
	private String type;
	private boolean ssl;
	private String key;


	public Connection() {
	}

	public Connection(String info, String ticket, String id, String token, String type, boolean ssl, String key)
			throws MalformedURLException {
		setConnection(info);
		this.ticket = ticket;
		this.instanceid = id;
		this.type = type;
		this.setToken(token);
		this.ssl = ssl;
		this.key = key;
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@XmlElement(name = "ticket")
	public String getTicket() {
		return ticket;
	}

	@XmlElement(name = "Ticket")
	public void putTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@XmlElement(name = "info")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlElement(name = "Info")
	public void setConnection(String info) throws MalformedURLException {
		this.info = info;

		if (!Strings.isNullOrEmpty(info) && info.contains("https")) {
			URL url = new URL(info);
			this.setIp(url.getHost());
			// this.setIp(getInfo());
			this.setPort(String.valueOf("443"));
		} else if (!Strings.isNullOrEmpty(info) && info.contains("http")) {
			URL url = new URL(info);
			this.setIp(url.getHost());
			this.setPort(String.valueOf(url.getPort()));
		} else if (getInfo() != null) {
			String[] infos = getInfo().split(":");
			if (infos.length > 1) {
				this.setIp(infos[0]);
				this.setPort(infos[1]);
			}
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "Type")
	public void putType(String type) {
		this.type = type;
	}

	@XmlElement(name = "ssl")
	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

}
