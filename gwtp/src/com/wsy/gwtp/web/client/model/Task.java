package com.wsy.gwtp.web.client.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class Task {

	private int id;
	private String project;
	private int taskId;
	private String description;
	private double estimate;
	private double rate;
	private String due;// date?

	public Task(int id, String project, int taskId, String desc,
			double estimate, double rate, String due) {
		setId(id);
		setProject(project);
		setTaskId(taskId);
		setDescription(desc);
		setEstimate(estimate);
		setRate(rate);
		setDue(due);
	}

	public String getDescription() {
		return description;
	}

	public String getDue() {
		return due;
	}

	public double getEstimate() {
		return estimate;
	}

	public int getId() {
		return id;
	}

	public String getProject() {
		return project;
	}

	public double getRate() {
		return rate;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public interface TaskProperties extends PropertyAccess<Task> {

		@Path("id")
		ModelKeyProvider<Task> key();

		ValueProvider<Task, String> description();

		ValueProvider<Task, String> project();

		ValueProvider<Task, String> due();

		ValueProvider<Task, Double> estimate();

		ValueProvider<Task, Double> rate();
	}
}