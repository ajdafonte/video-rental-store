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

    /**
     * Returns the representation in milliseconds if the source is not <code>null</code>. If source is <code>null</code> <code>null</code> is
     * returned.
     *
     * @param source a {@link OffsetDateTime}
     * @return millis if source is not null or null if source is null.
     */
    public static Long millisOrNull(final OffsetDateTime source)
    {
        return source == null ? null : source.toInstant().toEpochMilli();
    }
}
