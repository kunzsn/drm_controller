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

package drm.controller.rest.resource;

import drm.controller.data.OdrlBase;
import drm.controller.model.DrmControllerResponseRights;
import drm.controller.service.DrmControllerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * RESTController class
 */
@Api(value = "DRM Controller", description = "Endpoint for DRM controlled content processing")
@RestController
public class DrmControllerResourceImpl implements DrmControllerResourceInterface {

    private static final Logger LOG = getLogger(DrmControllerResourceImpl.class);

    private DrmControllerService drmController;

    @Autowired
    public DrmControllerResourceImpl(DrmControllerService drmController) {
        this.drmController = drmController;
    }


    @Override
    public DrmControllerResponseRights postLicense(@RequestParam("file") MultipartFile file) {
        LOG.debug("Received POST Request on /postlicense");
        LOG.debug("received ODRL = {}", file);

        StringBuilder sb = null;
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String str;
            sb = new StringBuilder(8192);
            while ((str = r.readLine()) != null) {
                sb.append(str);
            }
            LOG.debug(String.format("Extracted Licence content from file: %s", file));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        OdrlBase odrlBase = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OdrlBase.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(sb.toString());
            LOG.debug("Mapping received file into ODRL Model");
            odrlBase = (OdrlBase) unmarshaller.unmarshal(reader);
            LOG.debug("Successfully mapped file into ODRL Model");
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return drmController.returnRights(odrlBase);
    }


}
