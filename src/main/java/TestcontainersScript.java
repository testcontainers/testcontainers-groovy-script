import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;

abstract public class TestcontainersScript extends groovy.lang.Script {

    static AtomicBoolean lock = new AtomicBoolean();

    public TestcontainersScript() {
        // JUnit will create a new instance of this class, but we need to run this code only once
        if (lock.compareAndSet(false, true)) {
            JUnitCore junit = new JUnitCore();
            junit.addListener(new TextListener(System.out));
            System.exit(junit.run(getClass()).wasSuccessful() ? 0 : 1);
        }
    }
}