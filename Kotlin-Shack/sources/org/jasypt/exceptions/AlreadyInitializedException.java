package org.jasypt.exceptions;

/* loaded from: classes.dex */
public final class AlreadyInitializedException extends RuntimeException {
    private static final long serialVersionUID = 4592515503937873874L;

    public AlreadyInitializedException() {
        super("Encryption entity already initialized");
    }
}
