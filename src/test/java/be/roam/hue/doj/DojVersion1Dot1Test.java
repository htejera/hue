/*
 *  Copyright 2009 kevin.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package be.roam.hue.doj;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.regex.Pattern;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for {@link Doj), API version 1.0.
 * @author Kevin Wetzels
 */
public class DojVersion1Dot1Test {

    private static HtmlPage page;
    private static Doj onPage;

    @Test
    public void withTextMatching() {
        String pattern = ".*block.*";
        assertEquals(1, onPage.get("p").withTextMatching(pattern).size());
        assertEquals(1, onPage.get("p").withTextMatching(Pattern.compile(pattern)).size());
        pattern = ".*Nono.*";
        assertTrue(onPage.get("p").withTextMatching(pattern).isEmpty());
        assertTrue(onPage.get("p").withTextMatching(Pattern.compile(pattern)).isEmpty());
    }

    @Test
    public void withAttributeMatching() {
        String pattern = ".*col\\-\\d.*";
        assertEquals(4, onPage.get("div").withAttributeMatching("class", pattern).size());
        assertEquals(4, onPage.get("div").withAttributeMatching("class", Pattern.compile(pattern)).size());
        pattern = ".*nono.*";
        assertTrue(onPage.get("input").withAttributeMatching("value", pattern).isEmpty());
        assertTrue(onPage.get("input").withAttributeMatching("value", Pattern.compile(pattern)).isEmpty());
    }

    @Test
    public void getByAttributeMatching() {
        String pattern = ".*col\\-\\d.*";
        assertEquals(4, onPage.getByAttributeMatching("class", pattern).size());
        assertEquals(4, onPage.getByAttributeMatching("class", Pattern.compile(pattern)).size());
        pattern = ".*nono.*";
        assertTrue(onPage.getByAttributeMatching("class", pattern).isEmpty());
        assertTrue(onPage.getByAttributeMatching("class", Pattern.compile(pattern)).isEmpty());
    }

    @Test
    public void get_withGroupedSelectors() {
        assertEquals(3, onPage.get("#header  , #sidebar, #footer").size());
        assertEquals(5 + 1 + 1 + 0, onPage.get("div ol  , #sidebar   , blockquote,bdo").size());
    }

    @Test
    public void check() {
        assertTrue(onPage.get("#checker2").isChecked());
        onPage.get("#checker2").check();
        assertTrue(onPage.get("#checker2").isChecked());
        assertFalse(onPage.get("#checker1").isChecked());
    }

    @Test
    public void uncheck() {
        assertTrue(onPage.get("#checker2").isChecked());
        onPage.get("#checker2").uncheck();
    }

    @Test
    public void select() throws Exception {
        assertTrue(onPage.get("#the_plain_select option").withValue("4").isSelected());
        onPage.get("#the_plain_select option").withValue("2").select();
    }

    @Test
    public void deselect() throws Exception {
        assertTrue(onPage.get("#the_plain_select option").withValue("4").isSelected());
        onPage.get("#the_plain_select option").withValue("4").deselect();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        WebClient client = new WebClient(BrowserVersion.BEST_SUPPORTED);
        page = client.getPage(DojVersion1Dot1Test.class.getResource("/test.html"));
        onPage = Doj.on(page);
    }

}
