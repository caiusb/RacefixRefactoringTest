package racefix.refactoring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
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
    assertNotNull(element.getElement());
  }

  @Test
  public void testNotFound() {
    RefactoringElement element = new RefactoringElement(decorateName("notExistent"), null);
    assertNull(element.getElement());
  }

  @Test
  public void testFoundInNamedInnerClass() {
    RefactoringElement element = new RefactoringElement(decorateName("Inner.x"), null);
    assertNotNull(element.getElement());
  }
  
  @Test
  public void testFoundInAnnonymousInnerClass() {
  	RefactoringElement element = new RefactoringElement(decorateName("Particle.coordX"), null);
    assertNotNull(element.getElement());
  }

  @Test
  public void testThreadLocalTransformation() throws Exception {
    RefactoringElement element = new RefactoringElement(decorateName("x"),
        new ThreadPrivateRefactoring((IField) RefactoringElement.findElement(decorateName("x"), IJavaSearchConstants.FIELD)));
    element.apply();
    System.out.println(Thread.currentThread().getId());
    assertFinalAs();
  }
  
  @Test
  public void testDubiousRefactoring() throws Exception {
  	RefactoringElement element = new RefactoringElement(decorateName("shared"), null);
  	assertNotNull(element.getElement());
  }
}
