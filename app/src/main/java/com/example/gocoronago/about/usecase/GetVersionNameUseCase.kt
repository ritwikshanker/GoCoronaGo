package com.example.gocoronago.about.usecase

import com.example.gocoronago.about.repository.IAboutUsRepository
import javax.inject.Inject

interface IGetVersionNameUseCase {
    operator fun invoke(): String
}

class GetVersionNameUseCase @Inject constructor(
    val repo: IAboutUsRepository
) : IGetVersionNameUseCase {
    override fun invoke(): String {
        return repo.getVersionName()
    }
}