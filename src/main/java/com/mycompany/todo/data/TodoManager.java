/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.data;

import java.util.List;

/**
 * Interface for basic operations with data. For now just load and save.
 * @author indra43
 */
public interface TodoManager {
    /**
     * Returns all todos from storage.
     * @return List of Todo
     */
    public List<Todo> getAllToDoes();
    /**
     * Saves todos from list to storage.
     * @param todos list of Todo
     */
    public void saveTodo(List<Todo> todos);
}
