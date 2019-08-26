/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.edimaxsmartplug;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link EdimaxSmartPlugBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Falk Harnisch - Initial contribution
 */
@NonNullByDefault
public class EdimaxSmartPlugBindingConstants {

    public static final String BINDING_ID = "edimaxsmartplug";

    // List of all Thing Type UIDs
    public final static ThingTypeUID THING_TYPE_SP1101W = new ThingTypeUID(BINDING_ID, "sp1101w");
    public final static ThingTypeUID THING_TYPE_SP2101W = new ThingTypeUID(BINDING_ID, "sp2101w");

    // List of all Channel ids
    public final static String SWITCH = "switch";
    public final static String CURRENT = "current";
    public final static String POWER = "power";

}
