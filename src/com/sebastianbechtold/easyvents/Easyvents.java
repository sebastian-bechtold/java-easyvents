package com.sebastianbechtold.easyvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Easyvents<T> {

	// ATTENTION: Eclipse recommendation "Add type arguments to EasyVents" must NOT be done!
	public static Easyvents defaultDispatcher = new Easyvents();
	
	HashMap<Class<T>, ArrayList<Consumer<Object>>> mListeners = new HashMap<>();

	public void addListener(Class<T> type, Consumer<Object> listener) {

		if (!mListeners.containsKey(type)) {
			mListeners.put(type, new ArrayList<Consumer<Object>>());
		}

		if (!mListeners.get(type).contains(listener)) {
			mListeners.get(type).add(listener);
		}
	}

	public void removeListener(Class<T> type, Consumer<Object> listener) {
		if (!mListeners.containsKey(type)) {
			return;
		}

		if (mListeners.get(type).contains(listener)) {
			mListeners.get(type).remove(listener);
		}
	}

	public void fire(Class<T> type, Object payload) {

		if (mListeners.get(type) == null) {
			return;
		}

		for (Consumer<Object> listener : mListeners.get(type)) {

			if (listener != null) {
				listener.accept(payload);
			} else {
				System.out.println("ERROR: Easyvents: Listener for event " + type + " is null!");
			}
		}
	}

}
