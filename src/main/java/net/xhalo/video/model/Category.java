package net.xhalo.video.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Category implements Serializable {
	private Long id;
	@NotNull
	@Size(min = 1)
	private String name;
	private boolean belongToOther;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBelongToOther() {
		return belongToOther;
	}

	public void setBelongToOther(boolean belongToOther) {
		this.belongToOther = belongToOther;
	}
}
