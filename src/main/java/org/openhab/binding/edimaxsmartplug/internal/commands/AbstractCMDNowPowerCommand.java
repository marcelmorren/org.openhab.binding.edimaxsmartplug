/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.edimaxsmartplug.internal.commands;

import java.util.List;

/**
 * Base class for Commands of type NOW_POWER.
 *
 * @author Heinz - Initial contribution
 *
 * @param <T>
 */
public abstract class AbstractCMDNowPowerCommand<T> extends AbstractCMDCommand<T> {

    @Override
    protected List<String> getPath() {
        List<String> list = super.getPath();
        list.add("NOW_POWER");
        return list;
    }

}
