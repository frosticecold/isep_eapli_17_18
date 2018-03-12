package eapli.framework.presentation.console;

import eapli.framework.visitor.Visitor;

/**
 * an abstract generic List UI to avoid code duplication since most of the List
 * UIs are quite similar.
 *
 * Created by Paulo Gandra Sousa
 */
public abstract class AbstractListUI<T> extends AbstractUI {

    /**
     * the source to list
     *
     * @return
     */
    protected abstract Iterable<T> elements();

    /**
     * the visitor that prints the content of each element
     *
     * @return
     */
    protected abstract Visitor<T> elementPrinter();

    /**
     * the name of the type of elements to list
     *
     * @return
     */
    protected abstract String elementName();

    /**
     * the name of the type of elements to list
     *
     * @return
     */
    protected abstract String listHeader();

    /**
     * general listing of elements.
     *
     * template method (GoF)
     */
    @Override
    protected boolean doShow() {
	final Iterable<T> elems = elements();
	if (!elems.iterator().hasNext()) {
	    System.out.println("There is no registered " + elementName());
	} else {
	    new ListWidget<>(listHeader(), elems, elementPrinter()).show();
	}
	return true;
    }

    @Override
    public String headline() {
	return "List " + elementName() + "s";
    }
}
