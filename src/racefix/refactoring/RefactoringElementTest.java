package racefix.refactoring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import edu.uiuc.threadprivaterefactoring.ThreadPrivateRefactoring;


public class RefactoringElementTest extends BasicTest {

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void testFound() {
    RefactoringElement element = new RefactoringElement(decorateName("x"), null);
    assertNotNull(element.getField());
  }

  @Test
  public void testNotFound() {
    RefactoringElement element = new RefactoringElement(decorateName("notExistent"), null);
    assertNull(element.getField());
  }

  @Test
  public void testFoundInNamedInnerClass() {
    RefactoringElement element = new RefactoringElement(decorateName("Inner.x"), null);
    assertNotNull(element.getField());
  }
  
  @Test
  public void testFoundInAnnonymousInnerClass() {
  	RefactoringElement element = new RefactoringElement(decorateName("Particle.coordX"), null);
    assertNotNull(element.getField());
  }

  @Test
  public void testThreadLocalTransformation() throws Exception {
    RefactoringElement element = new RefactoringElement(decorateName("x"),
        new ThreadPrivateRefactoring(RefactoringElement.findField(decorateName("x"))));
    element.apply();
    System.out.println(Thread.currentThread().getId());
    assertFinalAs();
  }
  
  @Test
  public void testDubiousRefactoring() throws Exception {
  	RefactoringElement element = new RefactoringElement(decorateName("shared"), null);
  	assertNotNull(element.getField());
  }
}
