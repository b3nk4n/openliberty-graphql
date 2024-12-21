package de.b3nk4n.models;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Type;

@Type("systemMetrics")
@Description("System metrics")
public record SystemMetrics(
        @NonNull int cpus,
        @NonNull long heapSize,
        @NonNull long offHeapSize) {
}
