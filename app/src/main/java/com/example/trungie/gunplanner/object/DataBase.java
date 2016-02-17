package com.example.trungie.gunplanner.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Serializable{
    public List<Project> projectList;

    public DataBase() {
        projectList = new ArrayList<Project>();
    }

    public void createProject(String name) {
        Project project = new Project(name);
        projectList.add(project);
    }

    public void updateProject(Project project) {
        for(int i = 0; i < projectList.size(); i++) {
            if(projectList.get(i).getName().equals(project.getName())) {
                projectList.set(i, project);
            }
        }
    }

    public void deleteProject(int index) {
        if(index < 0 || index >= projectList.size()) {
            return ;
        }
        projectList.remove(index);
    }

    public void set(int indexProject, int indexTask, int indexSubTask, boolean b) {
        Project project = projectList.get(indexProject);
        Task task = projectList.get(indexProject).tasks.get(indexTask);
        //SubTask subTask = task.subTasks.get(indexSubTask);
        task.set(indexSubTask, b);
        project.set(task);
        updateProject(project);
    }

    public void set(int indexProject, int indexTask, boolean b) {
        Project project = projectList.get(indexProject);
        Task task = projectList.get(indexProject).tasks.get(indexTask);
        task.setIsCompleted(b);
        project.set(task);
        updateProject(project);
    }
}
