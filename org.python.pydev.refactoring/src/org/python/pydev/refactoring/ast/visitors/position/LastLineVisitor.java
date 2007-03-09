package org.python.pydev.refactoring.ast.visitors.position;

import org.python.pydev.parser.jython.SimpleNode;
import org.python.pydev.parser.jython.ast.VisitorBase;

/**
 * A node's last body statement isn't always the last line. We have to traverse the statement's AST node in many cases: e.g. a nested class,
 * any control statement, etc.
 * 
 * @author Ueli Kistler
 * 
 */
public class LastLineVisitor extends VisitorBase {

	private int lastLine;

	public LastLineVisitor() {
		lastLine = 0;
	}

	@Override
	public void traverse(SimpleNode node) throws Exception {
		if (node != null)
			node.traverse(this);
	}

	@Override
	protected Object unhandled_node(SimpleNode node) throws Exception {
		if (node.beginLine > lastLine)
			lastLine = node.beginLine;
		return null;
	}

	public int getLastLine() {
		return lastLine;
	}

}