package com.brucecompiler.context;

/**
 * BaseContext
 * This class provides a utility for storing and managing context-specific data(in this case, a user ID)
 * using the ThreadLocal mechanism. It ensures which the data is stored in a thread-safe manner
 */
public class BaseContext {

    // ThreadLocal storage for storing the current user's ID
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * Sets the current user's ID into the ThreadLocal
     *
     * @param id the ID to be stored, typically represents the currently logged-in user
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * Retrieves the current user's ID from the ThreadLocal
     *
     * @return The ID stored in the ThreadLocal, or null if no ID has been set
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * Removes the current user's ID from the ThreadLocal
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
