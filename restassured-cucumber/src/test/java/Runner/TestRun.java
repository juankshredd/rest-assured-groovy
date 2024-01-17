package Runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "scr/test/resources/features",
    glue = "Steps",
    tags = "@API"
)
public class TestRun {
}
