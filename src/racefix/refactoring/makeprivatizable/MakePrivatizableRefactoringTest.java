package racefix.refactoring.makeprivatizable;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import racefix.refactoring.BasicTest;
import racefix.refactoring.ClassChangeSet;
import racefix.refactoring.RefactoringElement;

public class MakePrivatizableRefactoringTest extends BasicTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testInterfaceImplementsInclusion() throws Exception {
		ClassChangeSet change = new ClassChangeSet();
		change.clazz = "in." + testName.getMethodName();
		change.privatizedFields = new HashSet<String>();
		change.threadLocal = new HashSet<String>();
		
		RefactoringElement element = new RefactoringElement(
				decorateName("in.testInterfaceImplementsInclusion"),
				new MakePrivatizableRefactoring(change));
		element.apply();
		assertFinalAs();
	}

}
