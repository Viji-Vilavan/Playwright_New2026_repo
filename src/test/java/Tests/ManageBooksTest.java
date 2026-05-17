package Tests;

import BaseTest.BaseClass;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AddBookPage;
import pages.NavigationPage;
import utilities.JsonUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageBooksTest extends BaseClass {

    LoginPage loginPage;
    AddBookPage addBookPage;
    NavigationPage navigationPage;

    @Test
    public void addBook() {
        LoginPage loginPage=new LoginPage(page);
        loginPage.PMloginFromJson();
        NavigationPage navigationPage=new NavigationPage(page);
        navigationPage.Sidebar();
        navigationPage.goToManageBooks();


        AddBookPage addBookPage =
                new AddBookPage(page);
        navigationPage.naviagteToCreateBookPage();



        addBookPage.addBookDetails();
//        page.pause();
    }}

