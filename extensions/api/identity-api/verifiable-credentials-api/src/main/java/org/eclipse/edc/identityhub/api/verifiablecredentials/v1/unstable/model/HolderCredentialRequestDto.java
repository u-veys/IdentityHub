/*
 *  Copyright (c) 2025 Cofinity-X
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Cofinity-X - initial API and implementation
 *
 */

package org.eclipse.edc.identityhub.api.verifiablecredentials.v1.unstable.model;

import java.util.Collection;
import java.util.Map;

/**
 * Represents a credential request of the holder.
 *
 * @param issuerDid       The DID of the issuer, to whom the request was originally sent.
 * @param holderPid       the request ID assigned by the holder
 * @param issuerPid       the process ID returned from the issuer
 * @param status          REQUESTED, ISSUED, etc.
 * @param credentialIds   after the credentials are issued, their IDs are stored here
 * @param typesAndFormats list of credential types/formats that were originally requested
 */
public record HolderCredentialRequestDto(String issuerDid, String holderPid, String issuerPid, String status,
                                         Collection<String> credentialIds,
                                         Map<String, String> typesAndFormats) {
}
