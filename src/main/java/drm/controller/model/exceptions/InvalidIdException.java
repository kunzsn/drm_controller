/**
 * Copyright (c) 2017 University of Stuttgart.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * and the Apache License 2.0 which both accompany this distribution,
 * and are available at http://www.eclipse.org/legal/epl-v20.html
 * and http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Sebastian Kunz - initial implementation
 */

package drm.controller.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle invalid licence for specific csar
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Hinterlegte CSAR ID in der Lizenz und CSAR ID stimmen nicht überein")
public class InvalidIdException extends RuntimeException {
}
