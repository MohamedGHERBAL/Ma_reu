package com.example.mareu.DI;

import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;

/**
 * Created by Mohamed GHERBAL (pour OC) on 20/07/2021
 */
public class DI {

        private static ReunionApiService service = new DummyReunionApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}.
     * Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
