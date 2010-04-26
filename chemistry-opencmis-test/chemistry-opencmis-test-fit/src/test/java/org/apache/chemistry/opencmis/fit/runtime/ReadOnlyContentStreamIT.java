/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.chemistry.opencmis.fit.runtime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.commons.api.ContentStream;
import org.junit.Test;

public class ReadOnlyContentStreamIT extends AbstractSessionTest {

    @Test
    public void readContentStream() throws IOException {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME + "/" + FixtureData.DOCUMENT1_NAME.toString();
        Document document = (Document) this.session.getObjectByPath(path);
        Assert.assertNotNull("document not found: " + path, document);

        ContentStream s = document.getContentStream();

        Assert.assertNotNull(s.getMimeType());
        s.getLength();
        s.getFileName();

        InputStream is = s.getStream();
        Assert.assertNotNull(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(is.read());
        byte[] b = baos.toByteArray();

        Assert.assertNotNull(b);
        Assert.assertTrue(b.length > 0);
    }

}