/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * a generic Action interface (the Command pattern)
 *
 * @author Paulo Gandra Sousa
 */
@FunctionalInterface
public interface Action {
    /**
     *
     * @return true if this "scope" should end or to signal OK; false otherwise,
     *         e.g., signal an error
     */
    boolean execute() throws DataConcurrencyException, DataIntegrityViolationException;
}
