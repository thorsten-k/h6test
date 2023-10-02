package de.kisner.test.hibernate.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="SecurityMenu",uniqueConstraints=@UniqueConstraint(columnNames={"view_id"}))
public class SecurityMenu implements Serializable
{
	public static final long serialVersionUID=1;


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}

	@ManyToOne
	private SecurityMenu parent;
	public SecurityMenu getParent() {return parent;}
	public void setParent(SecurityMenu parent) {this.parent = parent;}

	@NotNull @ManyToOne
	private SecurityView view;
	public SecurityView getView() {return view;}
	public void setView(SecurityView view) {this.view = view;}


	@Override public boolean equals(Object object){return (object instanceof SecurityMenu) ? id == ((SecurityMenu) object).getId() : (object == this);}
	@Override public int hashCode() {return new HashCodeBuilder(17, 53).append(id).toHashCode();}

	@Override public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(id).append("]");
		return sb.toString();
	}
}