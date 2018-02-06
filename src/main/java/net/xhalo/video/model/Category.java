package net.xhalo.video.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
	@NotNull
	private Long id;
	@NotNull
	@Size(min = 1)
	private String name;

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
}
