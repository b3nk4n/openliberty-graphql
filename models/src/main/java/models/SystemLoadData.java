package models;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

@Type("loadData")
@Description("System usage data")
public record SystemLoadData(
        @NonNull double loadAverage,
        @NonNull long heapUsed,
        @NonNull long nonHeapUsed
) {
}
