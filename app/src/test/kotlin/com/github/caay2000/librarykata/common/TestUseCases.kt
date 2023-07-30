package com.github.caay2000.librarykata.common

import com.github.caay2000.common.test.http.HttpDataResponse
import com.github.caay2000.common.test.mock.MockDateProvider
import com.github.caay2000.common.test.mock.MockIdGenerator
import com.github.caay2000.librarykata.context.account.domain.Account
import com.github.caay2000.librarykata.context.account.domain.AccountId
import com.github.caay2000.librarykata.context.account.domain.Email
import com.github.caay2000.librarykata.context.account.domain.IdentityNumber
import com.github.caay2000.librarykata.context.account.domain.PhoneNumber
import com.github.caay2000.librarykata.context.account.domain.PhonePrefix
import com.github.caay2000.librarykata.context.account.mother.AccountMother
import com.github.caay2000.librarykata.context.account.primaryadapter.http.serialization.AccountDetailsDocument
import io.ktor.server.testing.ApplicationTestBuilder
import java.time.LocalDateTime
import java.util.UUID

class TestUseCases(
    private val libraryClient: LibraryClient = LibraryClient(),
    private val mockIdGenerator: MockIdGenerator? = null,
    private val mockDateProvider: MockDateProvider? = null,
) {

    context(ApplicationTestBuilder)
    fun `account is created`(
        account: Account = AccountMother.random(),
        accountId: AccountId? = null,
        identityNumber: IdentityNumber? = null,
        email: Email? = null,
        phonePrefix: PhonePrefix? = null,
        phoneNumber: PhoneNumber? = null,
    ): HttpDataResponse<AccountDetailsDocument> {
        val id = accountId?.value ?: account.id.value
        `id will be mocked`(id)
        `datetime will be mocked`(account.registerDate.value)
        return libraryClient.createAccount(
            identityNumber = identityNumber ?: account.identityNumber,
            name = account.name,
            surname = account.surname,
            birthdate = account.birthdate,
            email = email ?: account.email,
            phonePrefix = phonePrefix ?: account.phonePrefix,
            phoneNumber = phoneNumber ?: account.phoneNumber,
        )
    }

    context(ApplicationTestBuilder)
    fun `find account`(id: AccountId): HttpDataResponse<AccountDetailsDocument> = libraryClient.findAccount(id)

    private fun `id will be mocked`(id: UUID): UUID = mockIdGenerator?.mock(id).let { id }
    private fun `datetime will be mocked`(datetime: LocalDateTime): LocalDateTime = mockDateProvider?.mock(datetime).let { datetime }
}
