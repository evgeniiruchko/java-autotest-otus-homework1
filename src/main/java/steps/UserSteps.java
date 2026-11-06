package steps;

import com.google.inject.Inject;
import components.HeaderComponent;
import components.UserCardComponent;
import listeners.HighlightListener;
import lombok.RequiredArgsConstructor;
import pages.UserWishlistsPage;
import pages.UsersPage;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserSteps {

    private final HeaderComponent header;
    private final UsersPage usersPage;
    private final UserWishlistsPage userWishlistsPage;
    private final HighlightListener highlightListener;

    public void openUsers() {
        header.openUsers();
        usersPage.waitUntilLoaded();
    }

    public int getUsersCount() {
        return usersPage.getUsersCount();
    }

    public UserCardComponent getSecondUser() {
        return usersPage.getSecondUser();
    }

    public void highlight(UserCardComponent card) {
        highlightListener.highlight(card.getRoot());
    }

    public boolean isHighlighted(UserCardComponent card) {
        return highlightListener.isHighlighted(card.getRoot());
    }

    public void resetHighlight() {
        highlightListener.resetAll();
    }

    public void openWishlists(UserCardComponent card) {
        card.openWishlists();
        userWishlistsPage.checkOpened();
    }
}
