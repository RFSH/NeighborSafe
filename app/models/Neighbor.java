package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "neighbors")
public class Neighbor extends Model {

    @Id
    public String username;

    public String password;

    @OneToOne(mappedBy = "neighbor")
    public NeighborProfile profile;

    public Neighbor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Finder<String, Neighbor> find = new Finder<String, Neighbor>(
            String.class, Neighbor.class
    );

    public static Neighbor authenticate(String username, String password) {
        return find.where().eq("username", username).eq("password", password).findUnique();
    }

    public static Neighbor findByUsername(String username) {
        return find.where().eq("username", username).findUnique();
    }

    public static void updatePass(String password, String username) {
        String s = "update neighbors set password= :password where username = :username";
        SqlUpdate update = Ebean.createSqlUpdate(s);
        update.setParameter("password", password);
        update.setParameter("username", username);
        update.execute();
    }
}
