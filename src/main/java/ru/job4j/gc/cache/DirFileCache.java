package ru.job4j.gc.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getName());

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String cache = null;
        try {
            cache = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            LOG.error("You entered the wrong file name");
        }
        return cache;
    }

}
