package models;


import org.sql2o.Connection;

import java.util.Objects;

public abstract class Wildlife {

    public int id;
    public String name;
    public String type;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wildlife wildlife = (Wildlife) o;
        return id == wildlife.id &&
                Objects.equals(name, wildlife.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void save(){
        try (Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals(name) VALUES(:name)";
           this.id = (int) con.createQuery(sql, true)
                    .addParameter("name",this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
}
