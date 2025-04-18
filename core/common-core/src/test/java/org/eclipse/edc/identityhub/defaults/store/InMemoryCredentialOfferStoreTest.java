/*
 *  Copyright (c) 2025 Metaform Systems Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems Inc. - initial API and implementation
 *
 */

package org.eclipse.edc.identityhub.defaults.store;

import org.eclipse.edc.identityhub.spi.verifiablecredentials.store.CredentialOfferStore;
import org.eclipse.edc.identityhub.verifiablecredentials.store.CredentialOfferStoreTestBase;
import org.eclipse.edc.query.CriterionOperatorRegistryImpl;

import java.time.Duration;

class InMemoryCredentialOfferStoreTest extends CredentialOfferStoreTestBase {

    private final InMemoryCredentialOfferStore store = new InMemoryCredentialOfferStore(RUNTIME_ID, clock, CriterionOperatorRegistryImpl.ofDefaults());

    @Override
    protected CredentialOfferStore getStore() {
        return store;
    }

    @Override
    protected boolean isLeasedBy(String issuanceId, String owner) {
        return store.isLeasedBy(issuanceId, owner);
    }

    @Override
    protected void leaseEntity(String holderPid, String owner, Duration duration) {
        store.acquireLease(holderPid, owner, duration);
    }
}