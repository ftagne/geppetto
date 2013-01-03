/**
 * Copyright (c) 2012 Cloudsmith Inc. and other contributors, as listed below.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Cloudsmith
 * 
 */
package org.cloudsmith.geppetto.forge.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.cloudsmith.geppetto.forge.v2.service.ReleaseService;
import org.junit.Test;

/**
 * @author thhal
 * 
 */
public class ReleaseTestDelete extends ForgeAPITestBase {
	@Test
	public void testDeleteRelease() throws IOException {
		ReleaseService service = getBobUserForge().createReleaseService();
		service.delete(ForgeTests.BOB_USER, ForgeTests.TEST_MODULE, ForgeTests.TEST_RELEASE_VERSION);
		try {
			service.get(ForgeTests.BOB_USER, ForgeTests.TEST_MODULE, ForgeTests.TEST_RELEASE_VERSION);
			fail("Expected 404");
		}
		catch(HttpResponseException e) {
			assertEquals("Wrong response code", 404, e.getStatusCode());
		}
	}
}