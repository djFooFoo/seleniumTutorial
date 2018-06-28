package examples.pages.springio;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpringDownload {
    @FindBy(id = "groupId")
    private WebElement group;

    @FindBy(id = "autocomplete")
    private WebElement dependencies;

    @FindBy(id = "artifactId")
    private WebElement artifact;

    @FindBy(name = "generate-project")
    private WebElement generateProject;

    @FindBy(css = "div.row.tofullversion p a")
    private WebElement toFullVersion;

    @FindAll(@FindBy(css = "input[type='checkbox']"))
    private List<WebElement> options;

    public SpringDownload withGroup(String text) {
        group.clear();
        group.sendKeys(text);
        return this;
    }

    public SpringDownload addDependency(String text) {
        dependencies.sendKeys(text, Keys.TAB);
        return this;
    }

    public SpringDownload withArtifact(String text) {
        artifact.clear();
        artifact.sendKeys(text);
        return this;
    }

    public SpringDownload withFullVersion() {
        toFullVersion.click();
        return this;
    }

    public SpringDownload withAllOptions() {
        options.forEach(WebElement::click);
        return this;
    }

    public void generateProject() {
        generateProject.click();
    }
}
