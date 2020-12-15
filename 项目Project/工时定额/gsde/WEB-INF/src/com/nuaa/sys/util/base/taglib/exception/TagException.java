/**
 * 
 */
package com.nuaa.sys.util.base.taglib.exception;

/**
 * @author chao
 *
 */
public class TagException extends RuntimeException 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274322509284604032L;

	public TagException(String reason) 
	{
		super(reason);
		this.TagState = null;
		printStackTrace();
    }
    
    public TagException(String reason, String tagState) 
	{
		super(reason);
		this.TagState = tagState;
		printStackTrace();
    }
    
    private String reason;
    
    private String TagState;

	/**
	 * Returns the reason.
	 * @return String
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Returns the tagState.
	 * @return String
	 */
	public String getTagState() {
		return TagState;
	}

	/**
	 * Sets the reason.
	 * @param reason The reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Sets the tagState.
	 * @param tagState The tagState to set
	 */
	public void setTagState(String tagState) {
		TagState = tagState;
	}

}

