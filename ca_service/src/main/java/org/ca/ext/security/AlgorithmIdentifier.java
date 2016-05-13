package org.ca.ext.security;

import java.io.IOException;

import org.ca.ext.security.util.ObjectIdentifier;

public class AlgorithmIdentifier extends ObjectIdentifier {
	private static final long serialVersionUID = 1313425998596932025L;
	
	public AlgorithmIdentifier(String identifier) throws IOException {
		super(identifier);
	}
}
