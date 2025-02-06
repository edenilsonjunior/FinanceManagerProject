package br.edu.ifsp.arq.tsi.arqweb2.financeManager.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Bootstrapper {

    private static final Map<Class<?>, Object> daos = new HashMap<>();
    private static final Map<Class<?>, Object> services = new HashMap<>();
    private static final List<Object> handlers = new ArrayList<>();

    private Bootstrapper() {}

    static <T> void addService(Class<T> interfaceType, T implementation) {
        services.put(interfaceType, implementation);
    }

    static <T> void addDao(Class<T> interfaceType, T implementation) {
        daos.put(interfaceType, implementation);
    }

    static <T> void addHandler(T implementation) {
        handlers.add(implementation);
    }

    @SuppressWarnings("unchecked")
    public static <T> T resolve(Class<T> type) {

        if (services.containsKey(type))
            return (T) services.get(type);

        if (daos.containsKey(type))
            return (T) daos.get(type);

        boolean containsHandler = handlers
                .stream()
                .anyMatch(h -> h.getClass().equals(type));

        if (containsHandler) {
            return (T) handlers
                    .stream()
                    .filter(h -> h.getClass().equals(type)).findFirst().get();
        }

        throw new RuntimeException("Nenhuma implementação registrada para: " + type.getName());
    }
}