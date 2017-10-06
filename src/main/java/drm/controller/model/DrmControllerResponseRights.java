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

package drm.controller.model;

import drm.controller.data.OdrlBase;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "DrmControllerResponseRights", description = "Represents the rights from a specific ODRL license")
public class DrmControllerResponseRights {

    private OdrlBase odrlLicense;

    public DrmControllerResponseRights() {
        //default
    }

    public OdrlBase getOdrlBase() {
        return odrlLicense;
    }

    public void setOdrlBase(OdrlBase odrlBase) {
        this.odrlLicense = odrlBase;
    }
}
