package com.mirego.nwad.domain

import com.mirego.trikot.streams.reactive.promise.Promise

interface CreateMomentUseCase {
    fun createNewMoment(createMomentData: CreateMomentData): Promise<Unit>
}

data class CreateMomentData(val jpegImageContent: ByteArray, val title: String, val parentMoment: String? = null)
