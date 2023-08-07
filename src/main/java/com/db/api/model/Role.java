package com.db.api.model;

import com.db.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {
    private Long id;
    private String name;
    private List<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable=false, unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection(targetClass=User.class)
    @ManyToMany(targetEntity = User.class, mappedBy="role", fetch = FetchType.EAGER)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}