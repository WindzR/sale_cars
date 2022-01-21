package ru.bodin.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "link")
    private String link;

    public Image() {
    }

    public Image(String link) {
        this.link = link;
    }

    public static Image of(String link) {
        Image image = new Image();
        image.link = link;
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return id == image.id && Objects.equals(link, image.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Image{"
                + "id=" + id
                + ", link='" + link + '\''
                + '}';
    }
}
