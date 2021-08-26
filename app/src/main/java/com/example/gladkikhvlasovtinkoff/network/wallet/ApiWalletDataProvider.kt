package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData.Companion.PUBLIC_CATEGORY_USER
import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import io.reactivex.Single
import javax.inject.Inject

class ApiWalletDataProvider @Inject constructor(private val api: TransactionApi) :
    RemoteWalletDataProvider {

    override fun addUser(userRequest: UserRequest): Single<UserResponse> =
        api.createUser(userRequest)

    override fun findUserByUsername(username: String): Single<List<UserResponse>> =
        api.findUserByUsername(username)

    override fun createCategory(categoryRequest: CategoryRequest): Single<CategoryDataSample> =
        api.createCategory(categoryRequest).map { response ->
            CategoryDataSample(
                userName = response.user?.username ?: PUBLIC_CATEGORY_USER,
                name = response.name,
                stringId = response.stringId,
                username = response.username,
                colorRed = response.redColor,
                colorBlue = response.blueColor,
                colorGreen = response.greenColor
            )
        }

    override fun addUserWithAccount(account: GoogleSignInAccount): Single<UserResponse> =
        api.createUser(
            UserRequest(
                name = account.displayName!!,
                username = account.email!!
            )
        )

    override fun getAllCurrencies(): Single<List<Currency>> =
        api.getAllCurrencies()
            .map { responseList ->
                responseList.map { currencyResponse ->
                    Currency(
                        code = currencyResponse.code,
                        name = currencyResponse.name
                    )
                }
            }

    override fun findWalletById(walletId: Long): Single<WalletData> =
        api.findWalletById(walletId)
            .map { response ->
                WalletData(
                    id = response.id,
                    username = response.user.name,
                    name = response.name,
                    limit = response.limit,
                    amount = response.balance,
                    currency = Currency(
                        code = response.currency.code,
                        name = response.currency.name
                    ),
                    hidden = response.isHidden
                )
            }

    override fun getAllWalletByUsername(username: String): Single<List<WalletData>> =
        api.getAllWalletsByUsername(username)
            .map { wallets ->
                wallets.map { response ->
                    WalletData(
                        id = response.id,
                        username = response.user.name,
                        name = response.name,
                        limit = response.limit,
                        amount = response.balance,
                        currency = Currency(
                            code = response.currency.code,
                            name = response.currency.name
                        ),
                        hidden = response.isHidden
                    )
                }
            }

    override fun createWallet(walletRequest: WalletCreateRequest): Single<WalletData> =
        api.createWallet(walletRequest)
            .map { response ->
                WalletData(
                    id = response.id,
                    username = response.user.name,
                    name = response.name,
                    limit = response.limit,
                    amount = response.balance,
                    currency = Currency(
                        code = response.currency.code,
                        name = response.currency.name
                    ),
                    hidden = response.isHidden
                )
            }

    override fun deleteWallet(walletId: Long): Single<Boolean> =
        api.deleteWallet(walletId)

    override fun updateWallet(
        walletUpdateRequest: WalletUpdateRequest,
        walletId: Long
    ): Single<WalletData> =
        api.updateWallet(walletUpdateRequest, walletId)
            .map { response ->
                WalletData(
                    id = response.id,
                    username = response.user.name,
                    name = response.name,
                    limit = response.limit,
                    amount = response.balance,
                    currency = Currency(
                        code = response.currency.code,
                        name = response.currency.name
                    ),
                    hidden = response.isHidden
                )
            }

    override fun createTransaction(transactionRequest: TransactionRequest): Single<WalletTransactionModel> =
        api.createTransaction(transactionRequest)
            .map { response ->
                WalletTransactionModel(
                    id = response.id,
                    date = response.date,
                    walletId = response.walletId,
                    isIncome = response.income,
                    amount = response.amount,
                    currency = Currency(response.code, UNDEFINED_STR),
                    transactionCategoryData = TransactionCategoryData
                        (
                        name = response.category.name,
                        userName = response.category.user?.username ?: PUBLIC_CATEGORY_USER,
                        userName = response.category.username,
                        iconId = getIconIdByNameId(response.category.name),
                        colorBlue = response.category.blueColor,
                        colorGreen = response.category.greenColor,
                        colorRed = response.category.redColor,
                        description = response.category.description,
                        income = response.income
                    )
                )
            }

    override fun getAllCategoriesByUsername(username: String): Single<List<CategoryDataSample>> =
        api.getAllCategoriesByUsername(username)
            .map { categories ->
                categories.map { response ->
                    CategoryDataSample(
                        userName = response.user?.username ?: PUBLIC_CATEGORY_USER,
                        name = response.name,
                        stringId = response.stringId,
                        username = response.username,
                        colorRed = response.redColor,
                        colorBlue = response.blueColor,
                        colorGreen = response.greenColor,
                        income = response.income
                    )
                }
            }

}
