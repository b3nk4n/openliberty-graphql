package de.b3nk4n.models;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

@Type("system")
@Description("Information about a single system")
public record SystemInfo(
        @NonNull String hostName,
        @NonNull String userName,
        String osName,
        String osVersion,
        @Name("customNote") String note,
        JavaInfo java) {
}
