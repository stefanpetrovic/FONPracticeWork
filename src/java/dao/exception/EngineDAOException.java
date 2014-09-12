/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.exception;

/**
 *
 * @author Djole
 */
public class EngineDAOException extends Exception {

    public static final String ENTITY_NOT_FOUND = "{0} not found";
    public static final String ERROR_GETTING_OPERATOR_BY_URL = "Error getting operator by url {0}";
    public static final String ERROR_GETTING_OPERATOR_BY_URL_AND_LANGUAGE = "Error getting operator by url {0} and language {1}";
    public static final String ERROR_PAGE_0_NOT_AVAILABLE = "Page ( {0} ), not available, current max pages = {1}";
    public static final String INVALID_PAGE_NUMBER = "Invalid page number, must be >= 1";
    public static final String ERROR_NULL_PAGE_ITEMS_NUMBER = "Null page items number";
    public static final String ERROR_EXECUTING_EMAIL_SEARCH_SERVICE = "Error executing email search service";

    public EngineDAOException(final String pMessage) {
        super(pMessage);
    }

    public EngineDAOException(final String pMessage, final Throwable throwable) {
        super(pMessage, throwable);
    }

    public EngineDAOException(final Throwable throwable) {
        super(throwable);
    }
}
