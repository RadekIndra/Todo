/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.data;

/**
 *
 * @author indra43
 */
public class Todo {

    public Todo() {
    }

    
    public Todo(String task) {
        this.task = task;
    }
    private String task;
    private Long completionTime;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }

    
}
