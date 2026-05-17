package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import utilities.JsonUtil;

public class LoginPage {

    Page page;

    Locator userID;
    Locator passWord;
    Locator loginButton;


    public LoginPage(Page page) {

        this.page = page;

        userID = page.getByPlaceholder("Employee ID");
        passWord = page.getByPlaceholder("Password");
        loginButton = page.getByText("Log In");



    }

    public void login(String user, String pass) {
        userID.fill(user);
        passWord.fill(pass);
        loginButton.click();

    }
    public void PMloginFromJson() {
        login(
                JsonUtil.get("PMLogin", "username"),
                JsonUtil.get("PMLogin", "password")
        );
    }

}