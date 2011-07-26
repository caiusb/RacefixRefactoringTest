package racefix.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class BasicTest {
  
  @Rule
  public TestName testName = new TestName();

  //@Before
  public void setUp() {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject project = root.getProject("Dummy");
    try {
      project.create(null);
      project.open(null);

      IProjectDescription description;
      description = project.getDescription();
      description.setNatureIds(new String[] { JavaCore.NATURE_ID });
      project.setDescription(description, null);
      
      IJavaProject javaProject = JavaCore.create(project);
      
      IFolder binFolder = project.getFolder("bin"); // it does not need creating...
      javaProject.setOutputLocation(binFolder.getFullPath(), null);
      
      List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
      IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
      LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
      for (LibraryLocation element : locations) {
       entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
      }
      
      IFolder sourceFolder = project.getFolder("src");
      sourceFolder.create(false, true, null);
      IFolder packageRootFolder = sourceFolder.getFolder("dummy");
      packageRootFolder.create(false, true, null);
      
      IPackageFragmentRoot rootPackage = javaProject.getPackageFragmentRoot(packageRootFolder);
      
      IPackageFragment pack = rootPackage.createPackageFragment("", true, null);
      
      ICompilationUnit cu = pack.createCompilationUnit("Dummy.java", "package dummy;\n\npublic class Dummy{\n\tprivate String x;\n}\n", true, null);
      
      IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
      IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
      System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
      newEntries[oldEntries.length] = JavaCore.newSourceEntry(sourceFolder.getFullPath());
      
      project.build(IncrementalProjectBuilder.FULL_BUILD, null);
      
    } catch (Throwable e) {
    }

  }
  
  @After
  public void tearDown() {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject project = root.getProject("Dummy");
    try {
      project.delete(true, null);
    } catch (CoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public String decorateName(String name) {
    return "src." + name;
  }
}
