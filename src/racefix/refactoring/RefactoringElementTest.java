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
  public void testFound() {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.x"), null);
    assertNotNull(element.getField());
  }
  
  @Test
  public void testNotFound() {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.notExistent"), null);
    assertNull(element.getField());
  }
  
  @Test
  public void testFoundInNamedInnerClass() {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.Inner.x"), null);
    assertNotNull(element.getField());
  }
}
