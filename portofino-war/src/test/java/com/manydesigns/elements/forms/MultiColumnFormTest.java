/*
 * Copyright (C) 2005-2010 ManyDesigns srl.  All rights reserved.
 * http://www.manydesigns.com/
 *
 * Unless you have purchased a commercial license agreement from ManyDesigns srl,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as published by
 * the Free Software Foundation.
 *
 * There are special exceptions to the terms and conditions of the GPL
 * as it is applied to this software. View the full text of the
 * exception in file OPEN-SOURCE-LICENSE.txt in the directory of this
 * software distribution.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307  USA
 *
 */

package com.manydesigns.elements.forms;

import com.manydesigns.elements.AbstractElementsTest;

/*
* @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
* @author Angelo Lupo          - angelo.lupo@manydesigns.com
* @author Giampiero Granatella - giampiero.granatella@manydesigns.com
*/
public class MultiColumnFormTest extends AbstractElementsTest {
    public static final String copyright =
            "Copyright (c) 2005-2010, ManyDesigns srl";

    public FormBuilder formBuilder1;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        formBuilder1 = new FormBuilder(AnnotatedBean2.class);
    }

    public void testDefaultOneColumn() {
        Form form = formBuilder1.build();

        String text = elementToString(form);
        assertEquals("<fieldset class=\"nolegend\">" +
                "<table class=\"details\">" +
                "<tr>" +
                "<th><label for=\"s1\" class=\"field\">S1:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s1\" name=\"s1\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s2\" class=\"field\">S2:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s2\" name=\"s2\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s3\" class=\"field\">S3:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s3\" name=\"s3\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s4\" class=\"field\">S4:</label></th>" +
                "<td colspan=\"3\"><input type=\"text\" class=\"text\" id=\"s4\" name=\"s4\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s5\" class=\"field\">S5:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s5\" name=\"s5\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s6\" class=\"field\">S6:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s6\" name=\"s6\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s7\" class=\"field\">S7:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s7\" name=\"s7\"></input></td>" +
                "</tr>" +
                "</table>" +
                "</fieldset>", text);
    }

    public void testTwoColumns() {
        Form form = formBuilder1.configNColumns(2).build();

        String text = elementToString(form);
        assertEquals("<fieldset class=\"nolegend\">" +
                "<table class=\"details\">" +
                "<tr>" +
                "<th><label for=\"s1\" class=\"field\">S1:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s1\" name=\"s1\"></input></td>" +
                "<th><label for=\"s2\" class=\"field\">S2:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s2\" name=\"s2\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s3\" class=\"field\">S3:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s3\" name=\"s3\"></input></td>" +
                "<td colspan=\"2\"></td>" +
                "</tr><tr>" +
                "<th><label for=\"s4\" class=\"field\">S4:</label></th>" +
                "<td colspan=\"3\"><input type=\"text\" class=\"text\" id=\"s4\" name=\"s4\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s5\" class=\"field\">S5:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s5\" name=\"s5\"></input></td>" +
                "<th><label for=\"s6\" class=\"field\">S6:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s6\" name=\"s6\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s7\" class=\"field\">S7:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s7\" name=\"s7\"></input></td>" +
                "<td colspan=\"2\"></td>" +
                "</tr>" +
                "</table>" +
                "</fieldset>", text);
    }

    public void testThreeColumns() {
        Form form = formBuilder1.configNColumns(3).build();

        String text = elementToString(form);
        assertEquals("<fieldset class=\"nolegend\">" +
                "<table class=\"details\">" +
                "<tr>" +
                "<th><label for=\"s1\" class=\"field\">S1:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s1\" name=\"s1\"></input></td>" +
                "<th><label for=\"s2\" class=\"field\">S2:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s2\" name=\"s2\"></input></td>" +
                "<th><label for=\"s3\" class=\"field\">S3:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s3\" name=\"s3\"></input></td>" +
                "</tr><tr>" +
                "<th><label for=\"s4\" class=\"field\">S4:</label></th>" +
                "<td colspan=\"3\"><input type=\"text\" class=\"text\" id=\"s4\" name=\"s4\"></input></td>" +
                "<td colspan=\"2\"></td>" +
                "</tr><tr>" +
                "<th><label for=\"s5\" class=\"field\">S5:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s5\" name=\"s5\"></input></td>" +
                "<th><label for=\"s6\" class=\"field\">S6:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s6\" name=\"s6\"></input></td>" +
                "<th><label for=\"s7\" class=\"field\">S7:</label></th>" +
                "<td><input type=\"text\" class=\"text\" id=\"s7\" name=\"s7\"></input></td>" +
                "</tr>" +
                "</table>" +
                "</fieldset>", text);
    }
}
