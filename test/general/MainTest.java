/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package general;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mihael
 */
public class MainTest {
    
    public MainTest() {
    }

    @Test
    public void backgroundThread() throws Exception {
        Thread lp = new Thread(new Main.LoginPrompt());
            lp.start();
            String threadName = lp.getName();
            assertFalse(Thread.currentThread().getName().compareTo(threadName) == 0);
            lp.interrupt();
    }
    
}
