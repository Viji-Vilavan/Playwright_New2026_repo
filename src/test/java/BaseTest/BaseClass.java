package BaseTest;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utilities.ConfigReader;

import java.nio.file.Paths;

public class BaseClass {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setup() {

        // Initialize ConfigReader
        configReader = new ConfigReader();

        // Create Playwright
        playwright = Playwright.create();

        // Launch Browser
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        // Create Browser Context
        context = browser.newContext();

        // Clear Permissions
        context.clearPermissions();

        // Start Tracing
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        // Create New Page
        page = context.newPage();

        // Navigate to URL
        page.navigate(configReader.getBaseURL());
    }

    @AfterMethod
    public void tearDown() {

        // Stop Tracing and Save Trace File
        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("trace.zip"))
        );

        // Close Browser
        browser.close();

        // Close Playwright
        playwright.close();
    }
}