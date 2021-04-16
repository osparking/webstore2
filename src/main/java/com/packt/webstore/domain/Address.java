package com.packt.webstore.domain;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929271024169069089L;
	private Long id;
	private String zipCode; // �����ȣ
	private String wideCiDo; // �����õ�
	private String ciGoonGu; // �ñ���
	private String streetName; // ���θ�
	private String buildingNo; // �ǹ���ȣ
	private String unitNo; // ��ȣ��(���ּ�)

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getWideCiDo() {
		return wideCiDo;
	}
	public void setWideCiDo(String wideCiDo) {
		this.wideCiDo = wideCiDo;
	}
	public String getCiGoonGu() {
		return ciGoonGu;
	}
	public void setCiGoonGu(String ciGoonGu) {
		this.ciGoonGu = ciGoonGu;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
