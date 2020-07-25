package eu.retrox.dragon.managers;

import java.util.*;

public class EntityManager {

    private static EntityManager instance;
    private Set<UUID> toKill;

    private EntityManager() {
        this.toKill = new HashSet<>();
    }

    public static EntityManager getInstance() {
        if(instance == null) instance = new EntityManager();
        return instance;
    }

    public Set<UUID> getToKill() {
        return toKill;
    }
}
