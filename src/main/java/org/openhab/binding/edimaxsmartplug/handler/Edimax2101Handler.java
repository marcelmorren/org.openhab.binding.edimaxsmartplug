/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.edimaxsmartplug.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.edimaxsmartplug.EdimaxSmartPlugBindingConstants;
import org.openhab.binding.edimaxsmartplug.internal.commands.GetCurrent;
import org.openhab.binding.edimaxsmartplug.internal.commands.GetPower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Edimax2101Handler} is responsible for handling commands for the
 * Edimax 2101, which are sent to one of the channels.
 *
 * @author Falk Harnisch - Initial contribution
 */
public class Edimax2101Handler extends EdimaxSmartPlugHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Edimax2101Handler.class);

    private ScheduledFuture<?> pollingJob;

    /**
     * Constructor the only calls the super constructor.
     *
     * @param thing The thing
     */
    public Edimax2101Handler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        super.handleCommand(channelUID, command);
        try {
            if (channelUID.getId().equals(EdimaxSmartPlugBindingConstants.CURRENT)) {
                if (command instanceof RefreshType) {
                    final DecimalType current = new DecimalType(getCurrent());
                    LOGGER.debug("Current: {}", current);
                    updateState(channelUID, current);
                }
            }
            if (channelUID.getId().equals(EdimaxSmartPlugBindingConstants.POWER)) {
                if (command instanceof RefreshType) {
                    final DecimalType power = new DecimalType(getPower());
                    LOGGER.debug("Power: {}", power);
                    updateState(channelUID, power);
                }
            }
            updateStatus(ThingStatus.ONLINE);
        } catch (IOException e) {
            LOGGER.error("Socket Communication Error2 {}: {}", e.getClass().toString(), e.getLocalizedMessage());
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR);
        }

    }

    @Override
    public void initialize() {
        super.initialize();
        final Runnable runnable = () -> refreshValues();
        pollingJob = scheduler.scheduleWithFixedDelay(runnable, 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void dispose() {
        pollingJob.cancel(true);
    }

    /**
     * Sends command to the channel to refresh current and power.
     */
    private void refreshValues() {
        handleCommand(getThing().getChannel(EdimaxSmartPlugBindingConstants.CURRENT).getUID(), RefreshType.REFRESH);
        handleCommand(getThing().getChannel(EdimaxSmartPlugBindingConstants.POWER).getUID(), RefreshType.REFRESH);
    }

    /**
     * Returns the current.
     *
     * @return Current in Ampere
     * @throws IOException if communication to device fails
     */
    public BigDecimal getCurrent() throws IOException {
        final GetCurrent getC = new GetCurrent();
        return getC.executeCommand(ci);
    }

    /**
     * Gets the actual power.
     *
     * @return Actual power in Watt
     * @throws IOException if communication to device fails
     */
    public BigDecimal getPower() throws IOException {
        final GetPower getC = new GetPower();
        return getC.executeCommand(ci);
    }

}
