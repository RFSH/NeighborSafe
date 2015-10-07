package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.SqlUpdate;
import controllers.NeighborQuery;
import controllers.ProfileForm;
import controllers.SignupForm;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "neighborprofiles")
public class NeighborProfile extends Model {

    @Id
    public String profileId;

    @OneToOne
    @JoinColumn(name = "profile_id")
    public Neighbor neighbor;

    public String homeNumber;

    public String zipCode;

    public String address;

    public String medicalCond;

    //Utility Stuff:
    public String utilityGaz;
    public String utilityElectric;
    public String utilityWater;

    //Ability Stuff:
    public boolean cpr;
    public boolean firstAid;
    public String otherMedTrainings;

    //Supplies Stuff:
    public boolean haveGenerator;
    public boolean haveFood;
    public String otherMedSupplies;

    public String languages = "";

    public String other;

    @OneToMany(mappedBy = "profile")
    public List<People> peopleList = new LinkedList<>();

    public static Model.Finder<String, NeighborProfile> find = new Finder<String, NeighborProfile>(String.class, NeighborProfile.class);

    /**
     * creates user and userprofile
     */
    public static void create(SignupForm signupForm) {
        new Neighbor(signupForm.getUsername(), signupForm.getPassword()).save();
        String query;
        query = String.format("insert into neighborprofiles " +
                        "(profile_id, home_number, zip_code, address, medical_cond, utility_gaz," +
                        " utility_electric, utility_water, cpr, first_aid, other_med_trainings," +
                        "have_generator, have_food, other_med_supplies, languages, other) " +
                        "values (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',%b,%b,\'%s\',%b,%b,\'%s\','%s', \'%s\')",
                signupForm.getUsername(), signupForm.getHomeNumber(), signupForm.getZipCode(), signupForm.getAddress(), signupForm.getMedicalCond(), signupForm.getUtilityGaz(), signupForm.getUtilityElectric(), signupForm.getUtilityWater(), signupForm.isCpr(), signupForm.isFirstAid(), signupForm.getOtherMedTrainings(), signupForm.isHaveGenerator(), signupForm.isHaveFood(), signupForm.getOtherMedSupplies(), signupForm.getLanguages(), signupForm.getOther());

        SqlUpdate insertSQL = Ebean.createSqlUpdate(query);
        insertSQL.execute();
    }


    public static void update(ProfileForm profileForm, String NeighborId) {
        String query;
        query = String.format("update neighborprofiles set " +
                        "home_number='%s', zip_code='%s', address='%s', medical_cond='%s', utility_gaz='%s'," +
                        " utility_electric='%s', utility_water='%s', cpr=%b, first_aid=%b, other_med_trainings='%s'," +
                        "have_generator=%b, have_food=%b, other_med_supplies='%s', languages='%s', other='%s' " +
                        "where profile_id=\'%s\'",
                profileForm.getHomeNumber(), profileForm.getZipCode(), profileForm.getAddress(), profileForm.getMedicalCond(), profileForm.getUtilityGaz(), profileForm.getUtilityElectric(), profileForm.getUtilityWater(), profileForm.isCpr(), profileForm.isFirstAid(), profileForm.getOtherMedTrainings(), profileForm.isHaveGenerator(), profileForm.isHaveFood(), profileForm.getOtherMedSupplies(), profileForm.getLanguages(), profileForm.getOther(), NeighborId);

        SqlUpdate updateQuery = Ebean.createSqlUpdate(query);
        updateQuery.execute();
    }

    public void addPeople(People people) {
        if (peopleList == null) peopleList = new LinkedList<>();
        peopleList.add(people);
        this.update(this.profileId);
    }

    /**
     * Finds the associated profile with the neighbor.
     *
     * @param n Neighbor
     * @return NeighborProfile
     */
    public static NeighborProfile findByNeighbor(Neighbor n) {
        return find.where().eq("profile_id", n.username).findUnique();
    }

    public static NeighborProfile findByUsername(String username) {
        return find.where().eq("profile_id", username).findUnique();
    }

    public static List<NeighborProfile> findAll() {
        return find.all();
    }

    /**
     * Search inside neighbor
     *
     * @param neighborQuery Query to search
     * @return List of neighbor profiles
     */
    public static List<NeighborProfile> findNeighbors(NeighborQuery neighborQuery, Neighbor thisNeighbor) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(String.format(" where profile_id != '%s' ", thisNeighbor.username));
        queryBuilder.append(String.format(" and zip_code = '%s' ", thisNeighbor.profile.zipCode));

        if (neighborQuery != null) {
            if (neighborQuery.getAddress() != null && neighborQuery.getAddress().trim().length() > 0) {
                queryBuilder.append(" and upper(address) like '%").append(neighborQuery.getAddress().toUpperCase()).append("%' ");
            }
            if (neighborQuery.knowsCpr()) {
                queryBuilder.append(" and cpr = true ");
            }
            if (neighborQuery.hasFirstAid()) {
                queryBuilder.append(" and first_aid = true ");
            }
            if (neighborQuery.getOtherMedTrain() != null && neighborQuery.getOtherMedTrain().trim().length() > 0) {
                queryBuilder.append(" and upper(other_med_trainings) like '%").append(neighborQuery.getOtherMedTrain().toUpperCase()).append("%' ");
            }
            if (neighborQuery.haveGenerator()) {
                queryBuilder.append(" and have_generator = true ");
            }
            if (neighborQuery.haveFood()) {
                queryBuilder.append(" and have_food = true ");
            }
            if (neighborQuery.getOtherMedSupply() != null && neighborQuery.getOtherMedSupply().trim().length() > 0) {
                queryBuilder.append(" and upper(other_med_supplies) like '%").append(neighborQuery.getOtherMedSupply().toUpperCase()).append("%' ");
            }
            if (neighborQuery.getLanguages() != null && neighborQuery.getLanguages().trim().length() > 0) {
                queryBuilder.append(" and upper(languages) like '%").append(neighborQuery.getLanguages().toUpperCase()).append("%' ");
            }
        }
        String findQuery = "select profile_id from neighborprofiles " + queryBuilder.toString();


        return Ebean.find(NeighborProfile.class).setRawSql(RawSqlBuilder.parse(findQuery).create()).findList();


    }
}
