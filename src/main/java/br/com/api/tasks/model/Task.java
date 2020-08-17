package br.com.api.tasks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "task")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "description", nullable = false)
    private String description;

	@Column(name = "finished", nullable = false)
    private Boolean finished;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tasklist_id")
	@BatchSize(size = 1)
	private TaskList tasklist;

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

	public Boolean isFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public TaskList getTasklist() {
		return tasklist;
	}
	public void setTasklist(TaskList tasklist) {
		this.tasklist = tasklist;
	}
}
