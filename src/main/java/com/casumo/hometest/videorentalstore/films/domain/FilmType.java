package com.casumo.hometest.videorentalstore.films.domain;

import java.util.HashMap;
import java.util.Map;


/**
 * FilmType enum.
 */
public enum FilmType
{
    NEW_RELEASE(1), REGULAR(2), OLD(3);

    private final int value;
    private static final Map<Integer, FilmType> ALL_TYPES = new HashMap<>();

    static
    {
        for (final FilmType type : FilmType.values())
        {
            ALL_TYPES.put(type.value, type);
        }
    }

    FilmType(final int value)
    {
        this.value = value;
    }

    public static FilmType getByValue(final int value)
    {
        return ALL_TYPES.get(value);
    }

    public int getValue()
    {
        return value;
    }}
