/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.exception;

/**
 *
 * @author Claus
 */
public class WebPageCreationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of
     * <code>WebPageCreationException</code> without detail message.
     */
    public WebPageCreationException() {
    }

    /**
     * Constructs an instance of
     * <code>WebPageCreationException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public WebPageCreationException(String msg) {
        super(msg);
    }
    public WebPageCreationException(Exception e) {
        super(e);
    }
}
