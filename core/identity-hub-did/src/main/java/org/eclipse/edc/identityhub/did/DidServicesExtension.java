/*
 *  Copyright (c) 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package org.eclipse.edc.identityhub.did;

import org.eclipse.edc.identityhub.spi.did.DidDocumentPublisherRegistry;
import org.eclipse.edc.identityhub.spi.did.DidDocumentService;
import org.eclipse.edc.identityhub.spi.did.store.DidResourceStore;
import org.eclipse.edc.identityhub.spi.keypair.events.KeyPairActivated;
import org.eclipse.edc.identityhub.spi.keypair.events.KeyPairRevoked;
import org.eclipse.edc.identityhub.spi.participantcontext.events.ParticipantContextUpdated;
import org.eclipse.edc.identityhub.spi.participantcontext.store.ParticipantContextStore;
import org.eclipse.edc.keys.spi.KeyParserRegistry;
import org.eclipse.edc.runtime.metamodel.annotation.Extension;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.runtime.metamodel.annotation.Provider;
import org.eclipse.edc.spi.event.EventRouter;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.transaction.spi.TransactionContext;

import static org.eclipse.edc.identityhub.did.DidServicesExtension.NAME;

@Extension(value = NAME)
public class DidServicesExtension implements ServiceExtension {
    public static final String NAME = "DID Service Extension";
    @Inject
    private TransactionContext transactionContext;
    @Inject
    private DidResourceStore didResourceStore;
    @Inject
    private EventRouter eventRouter;
    @Inject
    private KeyParserRegistry keyParserRegistry;
    @Inject
    private ParticipantContextStore participantContextStore;
    private DidDocumentPublisherRegistry didPublisherRegistry;

    @Override
    public String name() {
        return NAME;
    }

    @Provider
    public DidDocumentPublisherRegistry getDidPublisherRegistry() {
        if (didPublisherRegistry == null) {
            didPublisherRegistry = new DidDocumentPublisherRegistryImpl();
        }
        return didPublisherRegistry;
    }

    @Provider
    public DidDocumentService createDidDocumentService(ServiceExtensionContext context) {
        var service = new DidDocumentServiceImpl(transactionContext, didResourceStore,
                getDidPublisherRegistry(), participantContextStore, context.getMonitor().withPrefix("DidDocumentService"), keyParserRegistry);
        eventRouter.registerSync(ParticipantContextUpdated.class, service);
        eventRouter.registerSync(KeyPairRevoked.class, service);
        eventRouter.registerSync(KeyPairActivated.class, service);
        return service;
    }
}
