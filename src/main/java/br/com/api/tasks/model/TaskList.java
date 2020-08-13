package br.com.api.tasks.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taskslist")
public class TaskList {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<Task>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
