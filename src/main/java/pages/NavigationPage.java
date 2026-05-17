package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class NavigationPage {

    Page page;

    public NavigationPage(Page page){
        this.page = page; // 🔥 FIX
    }

    public void Sidebar(){
        page.locator("//div[@class='Header_menu-icon__Q44Sk']").hover();
    }
    public void closeSidebar(){
        page.locator("//div[@class='ManageBooks_imageSearch__uRGO9']").hover();
    }

    public void goToManageBooks(){
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Manage Books")).click();
    }
    public void fotom(){
        page.locator("a[href='/manage-books']").click();
    }


    public void clickAddBook(){
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("ADD")).click();
    }
    public void naviagteToCreateBookPage(){
        page.navigate("http://192.168.1.39:3001/create-book");

    }
}