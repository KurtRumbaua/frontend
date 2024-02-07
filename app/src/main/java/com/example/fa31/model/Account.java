package com.example.fa31.model;

import java.util.Objects;

public class Account {
    private long id;
    private String username;
    private String password;
    private String privilege;
    public Account(long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }

    public Account(){
        this.id = id;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int hashCode(){
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if(!Objects.equals(this.username, other.username)){
            return false;
        }
        if(!Objects.equals(this.password, other.password)){
            return false;
        }
        if(!Objects.equals(this.privilege, other.privilege)){
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append(('\''));
        sb.append(", password=").append(password);
        sb.append(", privilege=").append(privilege);
        sb.append('}');
        return sb.toString();
    }

}
