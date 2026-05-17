package pages;

import com.microsoft.playwright.*;

public class LinkPage {

    Page page;

    Locator generateLink;
    Locator getLink;
    Locator settings;
    Locator emailGate;
    Locator passwordGate;
    Locator password;
    Locator saveButton;
    Locator closeButton;

    public LinkPage(Page page){

        this.page = page;

        generateLink = page.locator("//*[@id='app-body']/div/div[1]/div[2]/div[1]/div/div/div/button[3]");
        getLink = page.locator("//span[@data-testid='generateLink.getLink']");
        settings = page.locator("//i[@class='paperflite-settings-wheel']");
        emailGate = page.locator("//span[text()='Validate work email']");
        passwordGate = page.locator("//input[@type='checkbox' and @id='password']");
        password = page.locator("//input[@type='password']");
        saveButton = page.locator("(//span[text()='Save'])[1]");
        closeButton = page.locator("//i[contains(@class,'generateLink_closeIcon')]");
    }

    public void generateLink(String password) {

        passwordGate.fill(password);

        generateLink.click();

    }



    public void enableGating(String pass){

        settings.click();

        emailGate.click();

        passwordGate.check();

        password.fill(pass);

        saveButton.click();

    }

    public void closeGenerateSideBar(){

        closeButton.click();

    }

}