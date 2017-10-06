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

import drm.controller.model.DrmControllerResponseRights;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for the DRM Controller Resource
 */
@RequestMapping(value = "/v1/drmcontroller")
public interface DrmControllerResourceInterface {

    String RIGHTS_URL = "/postlicense";


    @ApiOperation(value = "returns the rights from a specific odrl file in a .json format")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succesfully returns rights"),
            @ApiResponse(code = 500, message = "an internal error occured")
    })
    @RequestMapping(value = RIGHTS_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    DrmControllerResponseRights postLicense(@RequestParam("file") MultipartFile file);
}
