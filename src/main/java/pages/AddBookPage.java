package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import utilities.JsonUtil;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Pattern;

public class AddBookPage {

    private final Page page;

    // ===================== LOCATORS =====================

    private final Locator addBookButton;
    private final Locator bookNameField;
    private final Locator isbnField;
    private final Locator supplementIsbnField;
    private final Locator receivedDateField;
    private final Locator copyEditReceivedField;

    private final Locator categoryDropdown;
    private final Locator difficultyDropdown;
    private final Locator clusterDropdown;
    private final Locator stagesDropdown;
    private final Locator priorityDropdown;

    private final Locator authorNameField;
    private final Locator authorEmailField;

    private final Locator assignButton;
    private final Locator generateButton;
    private final Locator saveButton;
    private final Locator selectPublisher;
    private final Locator solutionBook;

    // ===================== CONSTRUCTOR =====================

    public AddBookPage(Page page) {

        this.page = page;

        // ===================== BUTTONS =====================

        addBookButton =
                page.getByRole(
                        AriaRole.LINK,
                        new Page.GetByRoleOptions().setName("ADD")
                );

        assignButton =
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Assign")
                                .setExact(true)
                );

        generateButton =
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Generate")
                );

        saveButton =
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Add")
                                .setExact(true)
                );

        // ===================== TEXT FIELDS =====================

        bookNameField =
                page.getByPlaceholder("Book Name...");

        isbnField =
                page.getByPlaceholder(
                        "ISBN",
                        new Page.GetByPlaceholderOptions().setExact(true)
                );

        supplementIsbnField =
                page.getByPlaceholder("Supplement ISBN");

        authorNameField =
                page.getByPlaceholder("Author Name...");

        authorEmailField =
                page.getByPlaceholder("Author Email ID...");

        // ===================== DATE FIELDS =====================

        receivedDateField =
                page.locator("input[name='receivedOn']");

        copyEditReceivedField =
                page.locator("input[name='copyEditReceived']");

        // ===================== DROPDOWNS =====================

        categoryDropdown =
                page.locator("select[name='category']");

        difficultyDropdown =
                page.locator("select[name='difficulty']");

        clusterDropdown =
                page.locator("select[name='cluster']");

        stagesDropdown =
                page.locator("select[name='stages']");

        priorityDropdown =
                page.locator("select[name='priority']");

        selectPublisher =page.locator("select[name='Publisher']");

        solutionBook =page.locator("select[name='solutionBook']");
    }

    // ===================== METHODS =====================

    public void clickAddBookButton() {

        addBookButton.click();
    }

    public void addBookDetails() {

        // ===================== DYNAMIC DATA =====================

        String uniqueBookName = generateBookName();

        String uniqueISBN = generateISBN();

        // ===================== STORE GENERATED DATA =====================

        JsonUtil.writeData(
                "book",
                "generatedBookName",
                uniqueBookName
        );

        JsonUtil.writeData(
                "book",
                "generatedISBN",
                uniqueISBN
        );

        // ===================== DATE =====================

        LocalDate today = LocalDate.now();

        String receivedOnDate =
                today.plusDays(1)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String copyEditDate =
                today.plusDays(7)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // ===================== BOOK DETAILS =====================

        bookNameField.fill(uniqueBookName);

        isbnField.fill(uniqueISBN);

        supplementIsbnField.fill("");

        receivedDateField.fill(receivedOnDate);

        copyEditReceivedField.fill(copyEditDate);

        // ===================== DROPDOWNS =====================

        categoryDropdown.selectOption(
                JsonUtil.get("book", "category"));

        difficultyDropdown.selectOption(
                JsonUtil.get("book", "difficulty"));

        clusterDropdown.selectOption(
                JsonUtil.get("book", "cluster"));

        stagesDropdown.selectOption(
                JsonUtil.get("book", "stage"));

        priorityDropdown.selectOption(
                JsonUtil.get("book", "priority"));

        selectPublisher.selectOption(JsonUtil.get("book","publisher"));

        solutionBook.selectOption(JsonUtil.get("book","solution"));

        // ===================== MASTER COPIER =====================

        selectMasterCopier();

        // ===================== AUTHOR DETAILS =====================

        authorNameField.fill(
                JsonUtil.get("book", "author"));

        authorEmailField.fill(
                JsonUtil.get("book", "authorEmail"));

        // ===================== WORKFLOW =====================

        assignWorkflow();

        // ===================== FILE UPLOAD =====================

        uploadSourceFile();

        uploadInstructionFile();

        // ===================== CHAPTER =====================

        selectChapter();

        generateChapters();

        enterChapterNames();
//
//        enterPageRange();

        // ===================== SAVE =====================

        saveButton.click();

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("OK")
        ).click();
    }

    // ===================== REUSABLE METHODS =====================

    private void selectMasterCopier() {

        page.getByRole(
                AriaRole.CELL,
                new Page.GetByRoleOptions()
                        .setName("Master Copiers")
        ).getByRole(AriaRole.TEXTBOX).click();

        page.locator("li")
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(
                                        JsonUtil.get(
                                                "book",
                                                "masterCopier"
                                        )
                                )
                )
                .getByRole(AriaRole.CHECKBOX)
                .check();
    }

    private void assignWorkflow() {

        page.getByRole(
                AriaRole.CELL,
                new Page.GetByRoleOptions()
                        .setName("Assign Workflow")
        ).locator("div").click();

        page.getByRole(
                AriaRole.IMG,
                new Page.GetByRoleOptions()
                        .setName(
                                JsonUtil.get(
                                        "book",
                                        "workflow"
                                )
                        )
        ).click();

        assignButton.click();
    }

    private void uploadSourceFile() {

        String sourceFilePath =
                JsonUtil.get(
                        "book",
                        "sourceFilePath"
                );

        page.locator("#fileInput")
                .setInputFiles(
                        Paths.get(sourceFilePath)
                );
    }

    private void uploadInstructionFile() {

        String instructionFilePath =
                JsonUtil.get(
                        "book",
                        "generalInstructionFilePath"
                );

        page.locator("#fileInputForGeneral")
                .setInputFiles(
                        Paths.get(instructionFilePath)
                );
    }


    private void selectChapter() {

        page.getByRole(
                AriaRole.ROW,
                new Page.GetByRoleOptions()
                        .setName("Chapters Please Select Chapters")
                        .setExact(true)
        ).getByRole(AriaRole.CHECKBOX).check();
    }

    private void generateChapters() {

        page.getByRole(
                AriaRole.SPINBUTTON,
                new Page.GetByRoleOptions()
                        .setName("Enter Value")
        ).fill("2");

        generateButton.click();
    }

    private void enterChapterNames() {

        page.getByRole(
                AriaRole.ROW,
                new Page.GetByRoleOptions()
                        .setName("Chapters1 BYM-CH-1 add-asset-icon")
        ).getByRole(AriaRole.TEXTBOX).fill("C1");

        page.getByRole(
                AriaRole.ROW,
                new Page.GetByRoleOptions()
                        .setName("Chapters2 BYM-CH-2 add-asset-icon")
        ).getByRole(AriaRole.TEXTBOX).fill("C2");
    }


//
//    private void enterPageRange() {
//
//        page.getByRole(AriaRole.SPINBUTTON)
//                .nth(1)
//                .fill("11");
//
//        page.getByRole(AriaRole.SPINBUTTON)
//                .nth(4)
//                .fill("1");
//    }

    // ===================== DYNAMIC DATA =====================

    private String generateBookName() {

        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder value = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {

            int index =
                    random.nextInt(characters.length());

            value.append(characters.charAt(index));
        }

        return "VTest_" + value;

    }

    private String generateISBN() {

        Random random = new Random();

        long number =
                100000000L +
                        (Math.abs(random.nextLong())
                                % 900000000L);

        return "777" + number;
    }
}