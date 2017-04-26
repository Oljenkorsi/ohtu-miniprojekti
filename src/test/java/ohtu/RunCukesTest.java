package ohtu;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/features"})
public class RunCukesTest {
	@ClassRule
	public static ServerRule server = new ServerRule();
}