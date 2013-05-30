/*
 * Copyright (C) 2005-2013 ManyDesigns srl.  All rights reserved.
 * http://www.manydesigns.com/
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.manydesigns.elements.fields;

import com.manydesigns.elements.FormElement;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.composites.AbstractCompositeElement;
import com.manydesigns.elements.xml.XhtmlBuffer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
 * @author Angelo Lupo          - angelo.lupo@manydesigns.com
 * @author Giampiero Granatella - giampiero.granatella@manydesigns.com
 * @author Alessio Stalla       - alessio.stalla@manydesigns.com
 */
public class FieldGroup extends AbstractCompositeElement<Field> implements FormElement {
    public static final String copyright =
            "Copyright (c) 2005-2013, ManyDesigns srl";

    protected String label;
    protected String help;
    protected Mode mode;
    protected boolean forceNewRow;
    protected int colSpan;

    public FieldGroup(String label) {
        this.label = label;
    }

    public void toXhtml(@NotNull XhtmlBuffer xb) {
        if(isEmpty()) {
            return;
        }
        if(mode.isBulk()) {
            for(Field field : this) {
                field.toXhtml(xb);
            }
        } else {
            if(!mode.isBulk()) {
                xb.openElement("th");
                xb.writeLabel(label, get(0).getId(), "control-label");
                xb.closeElement("th");
                xb.openElement("td");
                xb.openElement("table");
                xb.addAttribute("class", "mde-field-group-table");
                xb.openElement("tr");
            }
            for(Field field : this) {
                field.toXhtml(xb);
            }
            if(!mode.isBulk()) {
                xb.closeElement("tr");
                xb.closeElement("table");
                xb.closeElement("td");
            }
        }
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isForceNewRow() {
        return forceNewRow;
    }

    public void setForceNewRow(boolean forceNewRow) {
        this.forceNewRow = forceNewRow;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public List<String> getErrors() {
        List<String> errors = new ArrayList<String>();
        for(Field field : this) {
            errors.addAll(field.getErrors());
        }
        return errors;
    }

    public boolean hasRequiredFields() {
        for(Field field : this) {
            if(field.hasRequiredFields()) {
                return true;
            }
        }
        return false;
    }
}
