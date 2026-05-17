package pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.*;

public class AssetPage {

    Page page;

    Locator addAsset;
    Locator confirm;
    Locator saveChanges;

    private String fileUploadInput = "input[type='file']";


    public AssetPage(Page page){

        this.page = page;

        addAsset = page.locator("(//span[contains(text(),'Add Assets')])[1]");
        confirm = page.locator("//span[contains(text(),'Confirm')]");
        saveChanges = page.locator("(//span[text()='Save Changes'])[1]");
    }


    public void uploadAssets(String[] files) {

        addAsset.click();

        Path[] filePaths = Arrays.stream(files)
                .map(Paths::get)
                .toArray(Path[]::new);



        confirm.click();
        saveChanges.click();
    }
    public void uploadAssets2(){
        page.locator(".menu_show__eShmd .actionbar_buttonItem__F6C9C").click();

        // Upload file
        page.setInputFiles(
                ".add_addAsset__oJ\\+6O > input",
                Paths.get("D:\\AutoSelf'"));
        confirm.click();
        saveChanges.click();
    }

}