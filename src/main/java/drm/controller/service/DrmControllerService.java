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

package drm.controller.service;

import drm.controller.components.RightsGenerator;
import drm.controller.data.OdrlBase;
import drm.controller.model.DrmControllerResponseRights;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class DrmControllerService {

    private static final Logger LOG = getLogger(DrmControllerService.class);

    private RightsGenerator rightsGenerator;
    private RestTemplate restTemplate;
    private String MOCK_URL = "http://localhost:8080/v1/drm/opentosca/mock/getcsar?csarid=";

    @Autowired
    public DrmControllerService(RightsGenerator rightsGenerator, RestTemplate restTemplate) {
        this.rightsGenerator = rightsGenerator;
        this.restTemplate = restTemplate;
    }

    public DrmControllerResponseRights returnRights(OdrlBase odrlBase) {
        LOG.debug("Started Service");
        String licenseCsarId = null;

        // searching for the CsarID in the ODRL License
        if (odrlBase.getOexAgreement() != null) {
            licenseCsarId = odrlBase.getOexAgreement().getOexAsset().getOexContext().getUid();
        } else if (odrlBase.getOexOffer() != null) {
            licenseCsarId = odrlBase.getOexOffer().getOexAsset().getOexContext().getUid();
        }
        LOG.debug(String.format("Found license CSAR ID: %s", licenseCsarId));
        // setting up the request
        String url = MOCK_URL + licenseCsarId;
        LOG.debug(String.format("Set up request url: %s", url));
        String csarId = restTemplate.getForObject(url, String.class);
        LOG.debug("Recieved CSAR with CSAR ID: " + csarId);
        return rightsGenerator.generateRights(odrlBase, csarId);
    }
}
