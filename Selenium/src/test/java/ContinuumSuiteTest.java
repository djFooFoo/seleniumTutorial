import examples.tests.Google;
import examples.tests.SpringIo;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        Google.class,
        SpringIo.class
})
public class ContinuumSuiteTest {
}
