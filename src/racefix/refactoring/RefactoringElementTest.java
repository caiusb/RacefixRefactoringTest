package racefix.refactoring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class RefactoringElementTest extends BasicTest {
  
  @Before
  public void setUp() {
    super.setUp();
  }
  
  @Test
  public void testCreateFound() {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.x"), null);
    assertNotNull(element.getField());
  }
  
  @Test
  public void testCreateFail() {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.notExistent"), null);
    assertNull(element.getField());
  }
}
