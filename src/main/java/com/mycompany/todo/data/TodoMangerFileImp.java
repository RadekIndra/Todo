/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of manager interface to save data in file. Singleton.
 *
 * @author indra43
 */
public class TodoMangerFileImp implements TodoManager {

    /**
     * File path to storage file.
     */
    private final static String FILE_PATH = "todoList";
    /**
     * only instance of this class
     */
    private static TodoMangerFileImp instance;
    /**
     * Object mapper to transfer data to and from JSON
     */
    private final ObjectMapper mapper = new ObjectMapper();
    /**
     * File reference
     */
    private static final File file = new File(FILE_PATH);

    private TodoMangerFileImp() {

    }

    /**
     * Get instance method to make sure only one instance will be made.
     *
     * @return instance of TodoMangerFileImp
     */
    public static TodoMangerFileImp getInstance() {
        if (instance == null) {
            instance = new TodoMangerFileImp();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(TodoMangerFileImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Todo> getAllToDoes() {
        List<Todo> todos = new ArrayList<>(10);
        try (InputStream is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);) {
            String line;
            while ((line = reader.readLine()) != null) {
                todos.add(mapper.readValue(line, Todo.class));
            }
        } catch (IOException ex) {
            Logger.getLogger(TodoMangerFileImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return todos;
    }

    @Override
    public void saveTodo(List<Todo> todos) {
        try (OutputStream os = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter writer = new BufferedWriter(osw);) {
            for (Todo todo : todos) {
                writer.write(mapper.writeValueAsString(todo));
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(TodoMangerFileImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
