package pages;

import com.microsoft.playwright.*;

public class CollectionPage {

    Page page;

    Locator collectionMenu;
    Locator createCollection;
    Locator collectionName;
    Locator nextButton;
    Locator confirmButton;


    public CollectionPage(Page page){

        this.page = page;

//        collectionMenu = page.locator("//a[@id='collections']");
        createCollection = page.locator("//span[text()='Create new collection']");
        collectionName = page.locator("//input[@placeholder='Add a collection name']");
        nextButton = page.locator("//span[contains(text(),'Next')]");
        confirmButton = page.locator("//span[contains(text(),'Confirm')]");

    }


    public void createCollection(String name){

//
        page.locator("#search .lottie-icon").hover();

        // Move mouse to body (0,0)
        page.mouse().move(0,0);

        page.locator("#collections > .menu_iconLabel__c-RAX").click();

        createCollection.click();

        collectionName.fill(name);

        nextButton.click();

        nextButton.click();



        confirmButton.click();
    }


}