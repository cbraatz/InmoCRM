/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.exception;

/**
 *
 * @author Claus
 */
public class CRMException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of
     * <code>WebPageCreationException</code> without detail message.
     */
    public CRMException() {
    }

    /**
     * Constructs an instance of
     * <code>WebPageCreationException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CRMException(String msg) {
        super(msg);
    }
    public CRMException(Exception e) {
        super(e);
    }
}
