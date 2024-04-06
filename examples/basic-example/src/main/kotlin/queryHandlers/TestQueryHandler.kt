package org.example.queryHandlers

import dev.ceviz.query.QueryHandler
import org.example.queries.TestQuery

class TestQueryHandler : QueryHandler<TestQuery, String> {
    override suspend fun handle(query: TestQuery): String {
        if (query.type == "info") {
            return "Info Test query type: ${query.type}"
        } else if (query.type == "error") {
            return "Error Test query type: ${query.type}"
        } else if (query.type == "warm") {
            return "Warm Test query type: ${query.type}"
        }

        return "Test query type: ${query.type}"
    }
}
