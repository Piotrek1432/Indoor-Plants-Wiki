package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public  FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
