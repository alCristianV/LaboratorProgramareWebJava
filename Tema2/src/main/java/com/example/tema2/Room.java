package com.example.tema2;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="number", nullable = false)
    private int number;

    @Column(name="capacity", nullable = false)
    private int capacity;

    @OneToMany(mappedBy="room", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Track> tracks;

    public Room() {
    }

    public Room(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void update(Room room) {
        this.number = room.getNumber();
        this.capacity = room.getCapacity();
    }

}
