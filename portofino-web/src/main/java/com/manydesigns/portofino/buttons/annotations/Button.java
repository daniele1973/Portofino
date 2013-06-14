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

package com.manydesigns.portofino.buttons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares that the method on which this annotation is attached is to be exposed as a button on a web page.
 * This annotation is only supposed to work on handler methods in a
 * {@link com.manydesigns.portofino.actions.admin.page.PageAdminAction}.
 *
 *
 * @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
 * @author Angelo Lupo          - angelo.lupo@manydesigns.com
 * @author Giampiero Granatella - giampiero.granatella@manydesigns.com
 * @author Alessio Stalla       - alessio.stalla@manydesigns.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Button {
    public static final String copyright =
            "Copyright (c) 2005-2013, ManyDesigns srl";

    /**
     * The list where this button is to be placed. Web pages will include lists of buttons by name.
     */
    String list();

    /**
     * The order of the button inside the list. Buttons with lower order come before buttons with higher order.
     */
    double order() default 1.0;

    /**
     * The resource bundle key for the button's label.
     */
    String key() default "";

    /**
     * The resource bundle key for the button's title (shown as a tooltip on most browsers).
     */
    String titleKey() default "";

    /**
     * The name of the button's icon.
     */
    String icon() default "";

    /**
     * If this is true, the button is the primary one in its list and can be rendered differently (for example, with
     * a different background) to emphasize it to the user. Only one button for each list can be marked as primary.
     */
    boolean primary() default false;
}
