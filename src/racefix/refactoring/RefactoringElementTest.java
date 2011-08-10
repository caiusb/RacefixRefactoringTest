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
  
  @Test
  public void testFoundInAnnonymousInnerClass() {
  	RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.Particle.coordX"), null);
    assertNotNull(element.getField());
  }

  @Test
  public void testThreadLocalTransformation() throws Exception {
    RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.x"),
        new ThreadPrivateRefactoring(RefactoringElement.findField(decorateName("dummy.Dummy.x"))));
    element.apply();
    System.out.println(Thread.currentThread().getId());
    assertFinalAs("testThreadLocalTransformation_final.java");
  }
  
  @Test
  public void testFindLocalVariable() throws Exception {
  	RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.m().x"), null);
    assertNotNull(element.getField());
  }
  
  @Test
  public void testDubiousRefactoring() throws Exception {
  	RefactoringElement element = new RefactoringElement(decorateName("dummy.Dummy.shared"), null);
  	assertNotNull(element.getField());
  }
}
