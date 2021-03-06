package edu.ncsu.csc.itrust;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.old.beans.Email;
import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;

/**
 * Sends email to users. Since we don't want to train spammers in 326, this just inserts into a database. If
 * we put this into an actual system, we would replace this class with stuff from javax.mail
 *  
 * 
 */
public class EmailUtil {
	private DAOFactory factory;

	public EmailUtil(DAOFactory factory) {
		this.factory = factory;
	}

	// DO NOT SEND REAL EMAILS!!!!!
	// Sending emails - even to a throwaway account, is a waste of bandwidth and looks very suspicious.
	// If you want to know how to send emails from Java, just Google it on your own time.
	public void sendEmail(Email email) throws DBException {
		factory.getFakeEmailDAO().sendEmailRecord(email);
	}
}
