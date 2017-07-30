package Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private StringProperty uName = new SimpleStringProperty(this, "uName", "");
    private StringProperty Pass = new SimpleStringProperty(this, "Pass", "");
    private StringProperty Name = new SimpleStringProperty(this, "Name", "");

    public String getuName() {
        return uName.get();
    }

    public StringProperty uNameProperty() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName.set(uName);
    }

    public String getPass() {
        return Pass.get();
    }

    public StringProperty passProperty() {
        return Pass;
    }

    public void setPass(String pass) {
        this.Pass.set(pass);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }
}

