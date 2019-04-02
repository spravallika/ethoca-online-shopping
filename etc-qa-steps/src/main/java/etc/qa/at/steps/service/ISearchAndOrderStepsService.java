package etc.qa.at.steps.service;

public interface ISearchAndOrderStepsService {
    void launchSite();

    void selectSummerDressesFromWomen();

    void addProductToCartWithSizeFromQuickView(String dressType,String size);

    void continueShoppingFromQuickView();

    void checkoutToSummaryFromCart();
}
