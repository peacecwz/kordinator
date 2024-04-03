package com.peacecwz.query

interface Query<TResult>

interface QueryHandler<TQuery : Query<TResult>, TResult> {
    suspend fun handle(query: TQuery): TResult
}
