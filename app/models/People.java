package models;

import play.data.DynamicForm;
import play.db.ebean.Model;
import utils.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "people")
public class People extends Model {

    @Id
    public String id;

    @ManyToOne
    public NeighborProfile profile;

    public boolean isAdult;
    public int age;
    public String name;
    public String email;
    public String cellNumber;

    public static Finder<String, People> find = new Finder<String, People>(
            String.class, People.class
    );

    public People() {

    }

    public People(String id, NeighborProfile profile, boolean isAdult, int age, String name, String email, String cellNumber) {
        this.id = id;
        this.profile = profile;
        this.isAdult = isAdult;
        this.age = age;
        this.name = name;
        this.email = email;
        this.cellNumber = cellNumber;
    }

    public static void addFamilyMembers(DynamicForm requestedData, String username) {
        for (int i = 1; i < 7; i++) {
            if (requestedData.get("name_" + i) == null || requestedData.get("name_" + i).trim().equals("")) {
                continue;
            }
            if (requestedData.get("age_" + i) == null || requestedData.get("age_" + i).trim().equals("")) {
                continue;
            }
            if (requestedData.get("email_" + i) == null || requestedData.get("email_" + i).trim().equals("")) {
                continue;
            }
            if (requestedData.get("cell_" + i) == null || requestedData.get("cell_" + i).trim().equals("")) {
                continue;
            }

            int age = Integer.parseInt(requestedData.get("age_" + i));
            boolean isAdult = age > 18;
            new People(
                    IdGenerator.generateID(),
                    Neighbor.findByUsername(username).profile,
                    isAdult,
                    age,
                    requestedData.get("name_" + i),
                    requestedData.get("email_" + i),
                    requestedData.get("cell_" + i)
            ).save();
        }

    }

    public static List<People> findAll() {
        return find.all();
    }

    public static List<People> findMembers(String username) {
        return find.where().eq("profile.profileId", username).findList();
    }

}
