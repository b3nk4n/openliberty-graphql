package models;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

@Type("systemLoad")
@Description("Information about system usage")
public record SystemLoad(
        @NonNull String hostName,
        @NonNull SystemLoadData loadData
) {
}
