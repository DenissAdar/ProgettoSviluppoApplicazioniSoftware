package catering.businesslogic.user;

public class UserManager {
    private User currentUser;

    public void fakeLogin(String username) //TODO: bisogna implementare il login vero!
    {
        //this.currentUser = User.loadUser(username);
        this.currentUser = new User();

    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
