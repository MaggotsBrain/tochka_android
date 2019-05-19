package social.tochka.android.login.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUser {

    private String token;
    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }
}
