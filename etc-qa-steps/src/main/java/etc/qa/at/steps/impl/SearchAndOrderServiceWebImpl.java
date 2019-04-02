package etc.qa.at.steps.impl;

import com.google.inject.Inject;
import etc.qa.at.steps.service.ISearchAndOrderStepsService;
import etc.qa.at.ui.pages.HomePage;
import etc.qa.at.ui.pages.QuickViewModal;
import etc.qa.at.ui.pages.SummerDresses;

public class SearchAndOrderServiceWebImpl implements ISearchAndOrderStepsService {
    @Inject
    HomePage homePage;

    @Inject
    SummerDresses summerDresses;

    @Inject
    QuickViewModal quickViewModal;

    @Override
    public void launchSite() {
        homePage.launchURL();
    }

    @Override
    public void selectSummerDressesFromWomen() {
        homePage.goToWomenMenu();
        homePage.selectSummerDresses();
    }

    @Override
    public void addProductToCartWithSizeFromQuickView(String dressType, String size) {
        summerDresses.clickQuickViewDressType(dressType);
        quickViewModal.selectSizeForProduct(size);
        quickViewModal.addProductToCart();
    }

    @Override
    public void continueShoppingFromQuickView() {
        quickViewModal.continueShopping();
    }

    @Override
    public void checkoutToSummaryFromCart() {
        quickViewModal.viewCartAndCheckout();
    }
}
