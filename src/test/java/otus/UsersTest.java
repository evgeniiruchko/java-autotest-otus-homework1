package otus;

import com.google.inject.Inject;
import components.UserCardComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import prepare.User;
import steps.AuthSteps;
import steps.UserSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Order(2)
@ExtendWith(UIExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersTest {

    @Inject
    private AuthSteps authSteps;

    @Inject
    private UserSteps userSteps;

    @Inject
    private User user;

    @Test
    @Order(1)
    public void waitLoadUsersListTest() {
        authSteps.login(user);

        userSteps.openUsers();

        assertThat(userSteps.getUsersCount()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void highlightSecondUserAndOpenWishlistTest() {
        authSteps.login(user);
        userSteps.openUsers();

        UserCardComponent userCard = userSteps.getSecondUser();
        assertThat(userCard.getUsername()).isNotBlank();

        userSteps.highlight(userCard);
        assertThat(userSteps.isHighlighted(userCard)).isTrue();

        userSteps.resetHighlight();
        assertThat(userSteps.isHighlighted(userCard)).isFalse();

        userSteps.openWishlists(userCard);
    }
}
