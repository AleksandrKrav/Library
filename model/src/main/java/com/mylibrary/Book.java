package com.mylibrary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq_id", allocationSize = 0)
    @GeneratedValue(generator = "book_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @Column(name = "description")
    private String description;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "data")
    private byte[] data;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books", cascade = CascadeType.ALL)
    private List<User> users;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (fileName != null ? !fileName.equals(book.fileName) : book.fileName != null) return false;
        return Arrays.equals(data, book.data);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
