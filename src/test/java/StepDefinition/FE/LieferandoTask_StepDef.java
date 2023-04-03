package StepDefinition.FE;

import PageObjectModel.FE.LieferandoHomePage_POM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class LieferandoTask_StepDef
{
    LieferandoHomePage_POM lieferandoHomePage_pom = new LieferandoHomePage_POM();

    @Given("Lieferando search URL")
    public void redirectingToLieferandoHomePage() throws InterruptedException {
        lieferandoHomePage_pom.navigateToSearchURL();
        //  lieferandoHomePage_pom.acceptCookies();
    }

    @When("Tester Enter address")
    public void testerEnterAddress() {
        lieferandoHomePage_pom.enterAddress("");

    }

    @And("Choose the first suggested address option")
    public void chooseTheFirstSuggestedAddressOption() {
        lieferandoHomePage_pom.chooseFirstSuggestedOption();
    }
}