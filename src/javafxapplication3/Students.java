
package javafxapplication3;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class Students {

    
    private final SimpleStringProperty column_name;
    private final SimpleStringProperty column_phone;
    private final SimpleStringProperty column_Id;

    

    public Students(String column_Id, String column_name, String column_phone) {
        this.column_Id = new SimpleStringProperty(column_Id);
        this.column_name = new SimpleStringProperty(column_name);
        this.column_phone = new SimpleStringProperty(column_phone);
        
    }
    
public String getColumn_Id() {
        return column_Id.get();
    }

    public void setColumn_Id(String value) {
        column_Id.set(value);
    }

    public StringProperty column_IdProperty() {
        return column_Id;
    }
    public String getColumn_phone() {
        return column_phone.get();
    }

    public void setColumn_phone(String value) {
        column_phone.set(value);
    }

    public SimpleStringProperty column_phoneProperty() {
        return column_phone;
    }

    public String getColumn_name() {
        return column_name.get();
    }

    public void setColumn_name(String value) {
        column_name.set(value);
    }

    public SimpleStringProperty column_nameProperty() {
        return column_name;
    }


}
