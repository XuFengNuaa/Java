/**
 * 
 */
package com.nuaa.sys.util.base;

import javax.servlet.http.HttpSession;

import com.nuaa.sys.util.Logger;

/**
 * @author mahao
 *
 */
public class SessionKeys {
	public final static String USER_TREE_MODEL = "user.tree.model";

	public final static String SYSTEM_TREE_MODEL = "system.tree.model";

	public final static String UP2M_TREE_MODEL = "up2m.tree.model";

	public final static String MANAGESCOPE_MODEL = "managescope.tree.model";

	public final static String USER_ID = "user.id";

	public final static String USER_NAME = "user.name";

	public final static String REQUEST_ERROR_MSG = "request.error.msg.attr";

	public final static String CURRENT_MODEL_ID = "current.model.id";

	public final static String COPY_MODEL_ID = "copy.model.id";

	public static void clearSession(HttpSession session) {
		Logger.debug("session invalidate ...");
		session.invalidate();
	}
}
