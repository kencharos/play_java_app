package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ユーザーエンティティ
 */
@Entity
@Table(name = "T_USER")
public class User extends Model{

    @Id
    public String id;

    @Column
    public String hashPassword;

    public User() {
    }

    public User(String id, String hashPassword) {
        this.id = id;
        this.hashPassword = hashPassword;
    }

    public static Finder<String, User> find = new Finder<>(User.class);

    public static User findIdAndPassword(String id, String hashPassword) {
        return find.where().eq("id", id).eq("hashPassword", hashPassword).findUnique();
    }
}
