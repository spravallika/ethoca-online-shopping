package etc.qa.glue;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.And;
import etc.qa.at.steps.service.ISearchAndOrderStepsService;

@Singleton
public class AddToCartGlue {
    ISearchAndOrderStepsService iSearchAndOrderStepsService;

    @Inject
    AddToCartGlue(ISearchAndOrderStepsService iSearchAndOrderStepsService) {
        this.iSearchAndOrderStepsService = iSearchAndOrderStepsService;
    }

    @And("Eve quick views '(.*)' and adds it to cart with size '(.*)'")
    public void addProductToCart(String productName, String size) {
        iSearchAndOrderStepsService.addProductToCartWithSizeFromQuickView(productName, size);
    }

    @And("Eve wants to continue shopping and checkout to cart summary")
    public void continueAndCheckout(){
        iSearchAndOrderStepsService.continueShoppingFromQuickView();
        iSearchAndOrderStepsService.checkoutToSummaryFromCart();
    }
}
