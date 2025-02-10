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

package org.eclipse.edc.identityhub.spi.issuance.credentials.process.retry;

import org.eclipse.edc.identityhub.spi.issuance.credentials.model.IssuanceProcess;
import org.eclipse.edc.runtime.metamodel.annotation.ExtensionPoint;
import org.eclipse.edc.spi.retry.WaitStrategy;

/**
 * Implements a wait strategy for the {@link IssuanceProcess}.
 * <p>
 * Implementations may choose to enforce an incremental backoff period when successive errors are encountered.
 */
@ExtensionPoint
public interface IssuanceProcessRetryStrategy extends WaitStrategy {
}
