package me.sizableshrimp.mc122477fix;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Callback for polling SDL key and char events.
 * Called after each poll.
 */
public interface SDLPollCallback {
    Event<SDLPollCallback> EVENT = EventFactory.createArrayBacked(SDLPollCallback.class,
            listeners -> () -> {
                for (SDLPollCallback listener : listeners) {
                    listener.onPoll();
                }
            });

    void onPoll();
}
