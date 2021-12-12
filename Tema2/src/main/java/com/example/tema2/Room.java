package com.example.tema2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", nullable = false)
    private String code;

    public Room() {
    }

    public Room(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void update(Room room) {
        this.code = room.getCode();
    }

}
