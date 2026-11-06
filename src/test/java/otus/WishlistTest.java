package otus;

import com.google.inject.Inject;
import extensions.UIExtension;
import models.Gift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import prepare.User;
import steps.AuthSteps;
import steps.WishlistSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Order(1)
@ExtendWith(UIExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistTest {

    private static final String WISHLIST_NAME = "Мой список";

    @Inject
    private AuthSteps authSteps;

    @Inject
    private WishlistSteps wishlistSteps;

    @Inject
    private User user;

    private List<Gift> availableGifts;

    @BeforeEach
    public void openWishlist() {
        authSteps.login(user);
        availableGifts = wishlistSteps.filterByPrice(wishlistSteps.openWishlist(WISHLIST_NAME));
    }

    @Test
    @Order(1)
    public void findMostExpensiveGiftTest() {
        Gift mostExpensive = wishlistSteps.getMostExpensive(availableGifts);

        assertThat(mostExpensive.name()).isEqualTo("Подарок XXXL");
        assertThat(mostExpensive.price()).isEqualByComparingTo("50000");
    }

    @Test
    @Order(2)
    public void findCheapestGiftTest() {
        Gift cheapest = wishlistSteps.getCheapest(availableGifts);

        assertThat(cheapest.name()).isEqualTo("Подарок XS");
        assertThat(cheapest.price()).isEqualByComparingTo("999");
    }
}
