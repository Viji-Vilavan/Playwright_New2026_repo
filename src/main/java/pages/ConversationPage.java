package pages;

import com.microsoft.playwright.*;

public class ConversationPage {

    Page page;

    Locator conversationMenu;
    Locator searchBox;
    Locator deleteButton;
    Locator confirmDelete;


    public ConversationPage(Page page){

        this.page = page;

        conversationMenu = page.locator("//span[text()='Conversations']");
        searchBox = page.locator("input[placeholder='Search']");
        deleteButton = page.locator("text=Delete");
        confirmDelete = page.locator("text=Confirm");
    }

    public void searchConversation(String collectionName){

        conversationMenu.click();

        searchBox.fill(collectionName);
        page.locator("input").press("Enter");
        page.locator("//span[text()='Go to content hub']").click();
        searchBox.click();
    }


    public void deleteConversation(){

        deleteButton.click();

        confirmDelete.click();
    }

}