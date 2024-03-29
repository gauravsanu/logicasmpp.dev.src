/*
 * Copyright (c) 1996-2001
 * Logica Mobile Networks Limited
 * All rights reserved.
 *
 * This software is distributed under Logica Open Source License Version 1.0
 * ("Licence Agreement"). You shall use it and distribute only in accordance
 * with the terms of the License Agreement.
 *
 */
package com.logica.smpp.pdu;

import com.logica.smpp.Data;
import com.logica.smpp.util.*;
import com.logica.smpp.pdu.Response;
import com.logica.smpp.pdu.tlv.*;

/**
 * @author Logica Mobile Networks SMPP Open Source Team
 * @version 1.0, 11 Jun 2001
 */

public class SubmitSMResp extends Response
{
	
    private String messageId = Data.DFLT_MSGID;

	public SubmitSMResp()
	{
	    super(Data.SUBMIT_SM_RESP);
	}

	public void setBody(ByteBuffer buffer)
	throws NotEnoughDataInByteBufferException,
	       TerminatingZeroNotFoundException,
	       WrongLengthOfStringException
	{
            if(this.getCommandStatus()==0)
            {
                setMessageId(buffer.removeCString());
            }else
            {
                messageId="";
            }
	}
	
	public ByteBuffer getBody()
	{
	    ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
	    return buffer;
	}
	
    public void setMessageId(String value)
    throws WrongLengthOfStringException {
        checkString(value, Data.SM_MSGID_LEN);
        messageId = value;
    }
    
    public String getMessageId() { return messageId; }

    public String debugString()
    {
        String dbgs = "(submit_resp: ";
        dbgs += super.debugString();
        dbgs += getMessageId(); dbgs += " ";
        dbgs += debugStringOptional();
        dbgs += ") ";
        return dbgs;
    }
    
}