package org.example.queries

import dev.ceviz.query.Query

data class TestQuery(
    val type: String
) : Query<String>
