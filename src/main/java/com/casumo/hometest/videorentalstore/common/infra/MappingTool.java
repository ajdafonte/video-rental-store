package com.casumo.hometest.videorentalstore.common.infra;

import static java.time.ZoneOffset.UTC;

import java.time.Instant;
import java.time.OffsetDateTime;


/**
 * MappingTool class.
 */
public class MappingTool
{
    private static OffsetDateTime offsetDateTime(final long timeStamp)
    {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), UTC);
    }

    public static OffsetDateTime offsetDateTimeOrNull(final Long timeStamp)
    {
        return timeStamp == null ? null : offsetDateTime(timeStamp);
    }
}
