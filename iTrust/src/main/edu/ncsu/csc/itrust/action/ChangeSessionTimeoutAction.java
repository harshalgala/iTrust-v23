package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.mysql.AccessDAO;

/**
 * Used to change the session timeout, sessionTimeout.jsp. Note that a change to this timeout only gets
 * reflected on new sessions.
 * 
 */
public class ChangeSessionTimeoutAction {
	private AccessDAO accessDAO;

	/**
	 * Sets up defualts.
	 * 
	 * @param factory
	 */
	public ChangeSessionTimeoutAction(DAOFactory factory) {
		this.accessDAO = factory.getAccessDAO();
	}

	/**
	 * Changes the session timeout, the complicated logic of this is somewhat regrettably in the DAO,
	 * {@link AccessDAO}
	 * 
	 * @param minuteString
	 *            Pass the number of minutes in the form of a string, greater than 0.
	 * @throws FormValidationException
	 * @throws DBException
	 */
	public void changeSessionTimeout(String minuteString) throws FormValidationException, DBException {
		try {
			Integer minutes = Integer.valueOf(minuteString);
			if (minutes < 1)
				throw new FormValidationException("Must be a number greater than 0");
			accessDAO.setSessionTimeoutMins(minutes);
		} catch (NumberFormatException e) {
			throw new FormValidationException("That is not a number");
		}
	}

	/**
	 * Returns the current session timeout in minutes, as reflected in the database
	 * 
	 * @return the number of minutes it would take for an inactive session to timeout
	 * @throws DBException
	 */
	public int getSessionTimeout() throws DBException {
		return accessDAO.getSessionTimeoutMins();
	}
}
