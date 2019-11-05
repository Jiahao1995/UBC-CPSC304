package delegates;

public interface LoginDelegate {
    void signIn(String username, String password);
    void signUp();
}
