package fixtures

import QueryHandler

class TestQueryHandler : QueryHandler<TestQuery, String> {
    override suspend fun handle(query: TestQuery): String {
        return "Handler: ${query.value}"
    }
}
