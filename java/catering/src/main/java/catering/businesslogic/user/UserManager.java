package catering.businesslogic.user;

public class UserManager {
    private User currentUser;

    public void fakeLogin(String username) //TODO: bisogna implementare il login vero!
    {
        this.currentUser = User.loadUser(username);
        //System.out.println(this.currentUser);
        //Denisgaia12!
    }

    public User getCurrentUser() {
        //System.out.println(this.currentUser);
        return this.currentUser;
    }
}
