package modelo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AbstractBean implements Serializable{

	private static final long serialVersionUID = 1623458772090020636L;

	public AbstractBean() {
		super();
	}

	public String toString() {		
		return ToStringBuilder.reflectionToString(this);
	}             

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);		
	}

}