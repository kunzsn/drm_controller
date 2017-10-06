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

package drm.controller.components;

import drm.controller.data.OdrlBase;
import drm.controller.model.DrmControllerResponseRights;
import drm.controller.model.exceptions.InvalidIdException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Class to generate Rights Responses
 */
@Component
public class RightsGenerator {

    private static final Logger LOG = getLogger(RightsGenerator.class);

    public RightsGenerator() {
    }

    /**
     * Generates the rights from the given ODRL
     * @param odrlBase
     * @param csarId
     * @return DrmControllerResponseRights
     */
    public DrmControllerResponseRights generateRights(OdrlBase odrlBase, String csarId) {
        LOG.debug("generating rights");
        DrmControllerResponseRights drmControllerResponseRights = new DrmControllerResponseRights();

        //checks the csar ID in the license and the recieved csar ID
        if (!licenceAndCsarMatcher(odrlBase, csarId)) throw new InvalidIdException();
        LOG.debug("CSAR ID and License CSAR ID matched - success");
        drmControllerResponseRights.setOdrlBase(odrlBase);
        return drmControllerResponseRights;
    }

    /**
     * Checks if the licence and the uploaded csar are matching
     * @param odrlBase
     * @param csarId
     * @return boolean
     */
    private boolean licenceAndCsarMatcher(OdrlBase odrlBase, String csarId) {
        if (odrlBase.getOexAgreement().getOexAsset().getOexContext().getUid() != null) {
            return odrlBase.getOexAgreement().getOexAsset().getOexContext().getUid().equals(csarId);
        } else {
            return false;
        }
    }
}
