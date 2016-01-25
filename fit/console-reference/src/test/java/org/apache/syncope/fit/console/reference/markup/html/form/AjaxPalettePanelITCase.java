/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.fit.console.reference.markup.html.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.syncope.client.console.wicket.markup.html.form.AjaxPalettePanel;
import org.apache.syncope.fit.console.reference.AbstractITCase;
import org.apache.syncope.fit.console.reference.commons.TestPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AjaxPalettePanelITCase extends AbstractITCase {

    private static final IModel<List<String>> SELECTED = new ListModel<>(new ArrayList<>(Arrays.asList("A", "D")));

    private static final ListModel<String> ALL = new ListModel<>(new ArrayList<>(Arrays.asList("A", "B", "C", "D")));

    @Test
    public void isRendered() {
        TestPage<String, AjaxPalettePanel<String>> testPage =
                new TestPage.Builder<String, AjaxPalettePanel<String>>().build(
                        new AjaxPalettePanel.Builder<String>().setAllowOrder(true).build(
                        TestPage.FIELD, SELECTED, ALL));
        wicketTester.startPage(testPage);

        FormTester formTester = wicketTester.newFormTester(testPage.getForm().getId());
        formTester.submit();

        Collection<String> list = testPage.getFieldPanel().getModelCollection();
        assertEquals(2, list.size());
        Iterator<String> iterator = list.iterator();
        assertEquals("A", iterator.next());
        assertEquals("D", iterator.next());
    }
}
