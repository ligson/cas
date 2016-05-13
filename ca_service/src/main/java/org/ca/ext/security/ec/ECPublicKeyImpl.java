package org.ca.ext.security.ec;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.KeyRep;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.InvalidParameterSpecException;

import org.ca.ext.security.x509.AlgorithmId;
import org.ca.ext.security.x509.X509Key;

/**
 * Key implementation for EC public keys.
 *
 */
public final class ECPublicKeyImpl extends X509Key implements ECPublicKey {

    private static final long serialVersionUID = -2462037275160462289L;

    private ECPoint w;
    private ECParameterSpec params;

    /**
     * Construct a key from its components. Used by the
     * ECKeyFactory and SunPKCS11.
     */
    public ECPublicKeyImpl(ECPoint w, ECParameterSpec params)
            throws InvalidKeyException {
        this.w = w;
        this.params = params;
        // generate the encoding
        algid = new AlgorithmId
            (AlgorithmId.EC_oid, ECParameters.getAlgorithmParameters(params));
        key = ECParameters.encodePoint(w, params.getCurve());
    }

    /**
     * Construct a key from its encoding. Used by RSAKeyFactory.
     */
    public ECPublicKeyImpl(byte[] encoded) throws InvalidKeyException {
        decode(encoded);
    }

    // see JCA doc
    public String getAlgorithm() {
        return "EC";
    }

    // see JCA doc
    public ECPoint getW() {
        return w;
    }

    // see JCA doc
    public ECParameterSpec getParams() {
        return params;
    }

    // Internal API to get the encoded point. Currently used by SunPKCS11.
    // This may change/go away depending on what we do with the public API.
    public byte[] getEncodedPublicValue() {
        return key.clone();
    }

    /**
     * Parse the key. Called by X509Key.
     */
    protected void parseKeyBits() throws InvalidKeyException {
        try {
            AlgorithmParameters algParams = this.algid.getParameters();
            params = algParams.getParameterSpec(ECParameterSpec.class);
            w = ECParameters.decodePoint(key, params.getCurve());
        } catch (IOException e) {
            throw new InvalidKeyException("Invalid EC key", e);
        } catch (InvalidParameterSpecException e) {
            throw new InvalidKeyException("Invalid EC key", e);
        }
    }

    // return a string representation of this key for debugging
    public String toString() {
        return "Sun EC public key, " + params.getCurve().getField().getFieldSize()
            + " bits\n  public x coord: " + w.getAffineX()
            + "\n  public y coord: " + w.getAffineY()
            + "\n  parameters: " + params;
    }

    protected Object writeReplace() throws java.io.ObjectStreamException {
        return new KeyRep(KeyRep.Type.PUBLIC,
                        getAlgorithm(),
                        getFormat(),
                        getEncoded());
    }
}
