package JSF2.Beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 聪聪 on 2018/4/21 0021.
 */
@Named(value = "addressRegistration")
@SessionScoped
public class AddressRegistrationJSFBean implements Serializable{
    private String lastName;
    private String firstName;
    private String mi;
    private String telephone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String status = "Nothing stored";

    private PreparedStatement preparedStatement;

    public AddressRegistrationJSFBean() {
        initializeJdbc();
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.80.137/javabook", "scott", "tiger");
            preparedStatement = connection.prepareStatement("INSERT INTO Address (lastName, " +
                    "firstName, mi, telephone, email, street, city, state,zip) VALUES (?,?,?,?,?,?,?,?,?)");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRquiredFieldsFilled() {
        return !(lastName == null || firstName == null
        ||lastName.trim().length() == 0
        ||firstName.trim().length() == 0);
    }

    public String processSubmit() {
        if (isRquiredFieldsFilled())
            return "";
        else
            return "Last Name and FirstName are required";
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getInput() {
        return
                "<p style=\"color:red\">You entered <br />" +
                        "lastName = " + lastName + "<br/>" +
                        "firstName = " + firstName + "<br/>"+
                        "mi = " + mi + "<br/>"+
                        "telephone = " + telephone + "<br/>"+
                        "email = " + email + "<br/>" +
                        "street = " + street + "<br/>"+
                        "city = " + city + "<br/>"+
                        "state = " + state + "<br/>"+
                        "zip = " + zip + "<br/>" +
                        "status = " + status + "<br/>";

    }

    public String storeStudent() {
        try {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, mi);
            preparedStatement.setString(4, telephone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, street);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, state);
            preparedStatement.setString(9, zip);
            preparedStatement.executeUpdate();
            status = firstName + lastName + "is in database";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "addressStoredStatus";
    }
}
