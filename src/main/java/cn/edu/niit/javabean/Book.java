package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String author;
    private String sort;
    private String description;
    private boolean store;
    private boolean isborrow;
    public Book(String id, String name, String author, String sort,
                String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
    }
}
