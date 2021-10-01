package com.example.gocoronago.about.usecase

import com.example.gocoronago.about.model.Contributor
import com.example.gocoronago.about.repository.IAboutUsRepository
import javax.inject.Inject

interface IGetContributorListUseCase {
    operator fun invoke(): List<Contributor>
}

class GetContributorListUseCase @Inject constructor(
    private val repo: IAboutUsRepository
) : IGetContributorListUseCase {
    override fun invoke(): List<Contributor> {
        return repo.getContributorList()
    }
}