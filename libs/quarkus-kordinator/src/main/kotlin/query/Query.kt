package query

interface Query<TResponse>

interface QueryHandler<TQuery : Query<TResponse>, TResponse> {
    suspend fun handle(query: TQuery): TResponse
}
