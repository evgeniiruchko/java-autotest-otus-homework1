package steps;

import com.google.inject.Inject;
import components.HeaderComponent;
import lombok.RequiredArgsConstructor;
import models.Gift;
import pages.WishlistPage;
import pages.WishlistsPage;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WishlistSteps {

    private final HeaderComponent header;
    private final WishlistsPage wishlistsPage;
    private final WishlistPage wishlistPage;

    public List<Gift> openWishlist(String title) {
        header.openMyWishlists();
        wishlistsPage.waitUntilLoaded();
        wishlistsPage.openWishlist(title);
        wishlistPage.waitUntilLoaded();
        return wishlistPage.getGifts();
    }

    public List<Gift> filterByPrice(List<Gift> gifts) {
        return gifts.stream()
                .filter(gift -> gift.price().compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }

    public Gift getMostExpensive(List<Gift> gifts) {
        return gifts.stream()
                .max(Comparator.comparing(gift -> gift.price()))
                .orElseThrow(() -> new IllegalStateException("Список подарков пуст"));
    }

    public Gift getCheapest(List<Gift> gifts) {
        return gifts.stream()
                .min(Comparator.comparing(gift -> gift.price()))
                .orElseThrow(() -> new IllegalStateException("Список подарков пуст"));
    }
}
