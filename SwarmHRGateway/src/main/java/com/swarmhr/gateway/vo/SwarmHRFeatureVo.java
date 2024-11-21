package com.swarmhr.gateway.vo;

public class SwarmHRFeatureVo {

	private String featureID;
	private String featureName;
	private String url;
	private String iconClass;
	private String accessLevel;
	private String routerLink;
	private int priorty = 99;
	
	public SwarmHRFeatureVo(){
		
	}

	public String getFeatureID() {
		return featureID;
	}

	public void setFeatureID(String featureID) {
		this.featureID = featureID;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public int getPriorty() {
		return priorty;
	}

	public void setPriorty(int priorty) {
		this.priorty = priorty;
	}

	public String getRouterLink() {
		return routerLink;
	}

	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}

	@Override
	public String toString() {
		return "SwarmHRFeatureVo [featureID=" + featureID + ", featureName=" + featureName + ", url="
				+ url + ", iconClass=" + iconClass + ", accessLevel=" + accessLevel + ", priorty=" + priorty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureID == null) ? 0 : featureID.hashCode());
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
		SwarmHRFeatureVo other = (SwarmHRFeatureVo) obj;
		if (featureID == null) {
			if (other.featureID != null)
				return false;
		} else if (!featureID.equals(other.featureID))
			return false;
		return true;
	}

}
