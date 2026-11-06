package steps;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import pages.LoginPage;
import prepare.User;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class AuthSteps {

    private final LoginPage loginPage;

    public void login(User user) {
        loginPage.open();
        loginPage.checkOpened();
        loginPage.login(user);
    }
}
