package com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques

data class MosquesResponse(
        val html_attributions: List<Any>,
        val next_page_token: String,
        val results: List<Result>,
        val status: String
)