package social.tochka.android.login.data;

import social.tochka.android.login.data.model.LoggedInUser;

import java.io.IOException;
import java.util.UUID;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> signUp(String username, String password) {
        // TODO: handle loggedInUser registration
        try {
            LoggedInUser fakeUser = LoggedInUser.builder()
                    .displayName("Test User")
                    .userId(UUID.randomUUID().toString())
                    .build();

            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error sign up", e));
        }
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
