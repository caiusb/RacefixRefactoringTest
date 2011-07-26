package racefix.refactoring;

import static org.junit.Assert.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.log.Logger;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RefactoringElementTest {

  @Before
  public void setUp() {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = workspace.getRoot();
  }
 
  @Test
  public void testCreateFound() {
    RefactoringElement element = new RefactoringElement("dummy.Dummy.x", null);
    assertNotNull(element.getField());
  }
  
  @Test
  public void testCreateFail() {
    RefactoringElement element = new RefactoringElement("NonExistentClass.field", null);
    assertNull(element.getField());
  }
}
