package com.pbi.newsapp.domain.model

data class RequestQuery(
    /**
     * Request query that can be used
     * for both Endpoints
     */
    val q: String? = null,
    val source: String? = null,
    val pageSize: String? = null,
    val page: String? = null,

    /**
     * Specific request Query for /everything Endpoint
     */
    val searchIn: String? = null,
    val excludeDomains: String? = null,
    val from: String? = null,
    val to: String? = null,
    val language: String? = null,
    val sortBy: String? = null,

    /**
     * Specific request Query for /top-headlines Endpoint
     */
    val country: String? = null,
    val category: String? = null,
) {
    fun toMap() : Map<String, String> {
        val query = mapOf(
            "q" to q,
            "source" to source,
            "pageSize" to pageSize,
            "page" to page,
            "searchIn" to searchIn,
            "excludeDomains" to excludeDomains,
            "from" to from,
            "to" to to,
            "language" to language,
            "sortBy" to sortBy,
            "country" to country,
            "category" to category
        )


        return query.filterValues { it != null } as Map<String, String>
    }
}
