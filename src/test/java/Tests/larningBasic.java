package Tests;

import BaseTest.BaseClass;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AddBookPage;
import pages.NavigationPage;

import java.awt.geom.Area;

public class larningBasic {
        public static void main(String[] args) {

            Playwright playwright = Playwright.create();

            Browser browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            Page page = browser.newPage();

//            page.navigate("https://www.w3schools.com/");
//
//            page.locator("#search2").click();
//
//            page.locator("#search2").fill("jAVA Tutorial");
//
////             long cut
////            // Select All
////            page.keyboard().down("Control");
////            page.keyboard().press("A");
////            page.keyboard().up("Control");
////
////// Copy
////            page.keyboard().down("Control");
////            page.keyboard().press("C");
////            page.keyboard().up("Control");
//
//            /// short cut
//
//            page.keyboard().press("Control+A");
//            page.keyboard().press("Control+C");
//
//
//            page.locator("#search2").press("Enter");// keyboard action
//
//// Another textbox
//            page.getByPlaceholder("Search...").click();
//
//// Paste
//            page.keyboard().press("Control+V");
//            System.out.println("Run successfully");




               ////Check Box

//            page.navigate("https://the-internet.herokuapp.com/checkboxes");
////
//            page.locator("input[type='checkbox']").first().check(); // 1type
//            page.locator("input[type='checkbox']").nth(1).uncheck();  // 2nd type
//            System.out.println("Run successfully checkbox");


            ///Select single an multiple

            page.navigate("https://demo.mobiscroll.com/jquery/select/multiple-select");
            page.locator("#multiple-select-select").click();
            page.locator("#multiple-select-select").selectOption(new String[]{"Movies, Music & Games","Books","Health & Beauty"});








            browser.close();
            playwright.close();
        }
    }

