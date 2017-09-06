package com.hiwotab.springboot1920.model;

import javax.persistence.*;
import java.util.Set;
@Entity
public class NRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String urole;
    @ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
    private Set<NUser> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public Set<NUser> getUsers() {
        return users;
    }

    public void setUsers(Set<NUser> users) {
        this.users = users;
    }

    public void addUser(NUser user)
    {
        this.users.add(user);
    }
}
