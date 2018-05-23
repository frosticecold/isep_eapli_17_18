package eapli.ecafeteria.app.user.console.presentation.authz;

import eapli.framework.util.Console;

/**
 * TODO move to console.common to allow reuse from both backoffice and UtenteApp
 *
 * widget for reading user data Jorge Santos ajs@isp.ipp.pt
 */
class UserDataWidget {

    private String userType;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public void show() {
        this.userType = Console.readLine("User Type (Student or Employee)");
        this.username = Console.readLine("Username");
        this.password = Console.readLine("Password");
        this.firstName = Console.readLine("First Name");
        this.lastName = Console.readLine("Last Name");
        this.email = Console.readLine("E-Mail");
    }
    
    public String userType() {
        return this.userType;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String email() {
        return this.email;
    }
}
