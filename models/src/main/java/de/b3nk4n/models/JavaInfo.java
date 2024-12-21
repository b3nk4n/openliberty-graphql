package de.b3nk4n.models;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

@Type("java")
@Description("Information about a Java installation")
public record JavaInfo(
        @Name("vendorName") String vendor,
        @NonNull String version) {
}
