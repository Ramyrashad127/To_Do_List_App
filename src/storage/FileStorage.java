package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import models.TodoList;

public class FileStorage {
    String filename;
    Gson gson;

    public FileStorage(String filename) {
        this.filename = filename;
        this.gson = new Gson();
    }

    public void save(List<TodoList> data) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            String json = gson.toJson(data);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TodoList> load() {
        try (FileReader reader = new FileReader(filename)) {
            Type todoListType = new TypeToken<List<TodoList>>() {}.getType();
            return gson.fromJson(reader, todoListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
