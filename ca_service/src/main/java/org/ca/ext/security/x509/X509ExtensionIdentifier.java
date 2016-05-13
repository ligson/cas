package org.ca.ext.security.x509;

import java.io.IOException;

import org.ca.ext.security.util.ObjectIdentifier;

public class X509ExtensionIdentifier extends ObjectIdentifier{

	private static final long serialVersionUID = 6968468124239753527L;

	public X509ExtensionIdentifier(String identifier) throws IOException {
		super(identifier);
	}

}
