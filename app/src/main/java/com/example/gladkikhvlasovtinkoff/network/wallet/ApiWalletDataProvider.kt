package com.example.gladkikhvlasovtinkoff.network.wallet

import android.util.Log
import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData.Companion.PUBLIC_CATEGORY_USER
import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CurrencyCourseResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CurrencyResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import io.reactivex.Single
import java.math.BigDecimal
import javax.inject.Inject

class ApiWalletDataProvider @Inject constructor(private val api: Api) :
    RemoteWalletDataProvider {

    override fun addUser(userRequest: UserRequest): Single<UserResponse> =
        api.createUser(userRequest)

    override fun findUserByUsername(username: String): Single<List<UserResponse>> =
        api.findUserByUsername(username)

    override fun createCategory(categoryRequest: CategoryRequest): Single<CategoryDataSample> =
        api.createCategory(categoryRequest).map { response ->
            CategoryDataSample(
                username = response.user?.username ?: PUBLIC_CATEGORY_USER,
                name = if(response.name!=null)response.name else UNDEFINED_STR,
                stringId = response.stringId,
                colorRed = response.redColor,
                colorBlue = response.blueColor,
                colorGreen = response.greenColor,
                id = response.id
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

    override fun getCurrencyCourse(code: String): Single<CurrencyCourse> =
        api.getCurrencyCourse(code)
            .map { course ->
                CurrencyCourse(
                    code = course.code,
                    name = course.name,
                    course = course.course,
                    isUp = course.isUp
                )
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
                    id = response.category.id,
                    date = response.date,
                    walletId = response.wallet.id,
                    isIncome = response.income,
                    amount = response.amount,
                    currency = Currency(response.currency.code, UNDEFINED_STR),
                    transactionCategoryData = TransactionCategoryData
                        (
                        name = response.category.name ?: UNDEFINED_STR,
                        userName = response.category.user?.username ?: PUBLIC_CATEGORY_USER,
                        iconId = getIconIdByNameId(response.category.name ?: UNDEFINED_STR),
                        colorBlue = response.category.blueColor,
                        colorGreen = response.category.greenColor,
                        colorRed = response.category.redColor,
                        income = response.income,
                        id = response.category.id,
                        description = UNDEFINED_STR
                    )
                )
            }

    override fun getAllCategoriesByUsername(username: String): Single<List<CategoryDataSample>> =
        api.getAllCategoriesByUsername(username)
            .map { categories ->
                categories.map { response ->
                    CategoryDataSample(
                        username = response.user?.username ?: PUBLIC_CATEGORY_USER,
                        name = if(response.name!=null)response.name else UNDEFINED_STR,
                        stringId = response.stringId,
                        colorRed = response.redColor,
                        colorBlue = response.blueColor,
                        colorGreen = response.greenColor,
                        income = response.income,
                        id = response.id
                    )
                }
            }

    override fun getAllWalletsBalance(currencyCharCode: String, username: String): Single<String> =
        api.getAllWalletsBalance(currencyCharCode, username)

    override fun getExpensesByWallet(walletData: WalletData): Single<String> =
        api.getWalletExpensesCount(
            WalletSumRequest(
                balance = walletData.amount,
                currency = CurrencyResponse(
                    name = walletData.currency.name,
                    code = walletData.currency.code
                ),
                isHidden = walletData.hidden,
                id = walletData.id,
                limit = walletData.limit,
                name = walletData.name,
                user = UserResponse(
                    name = walletData.name,
                    username = walletData.username
                )
            )
        )

    override fun getIncomeByWallet(walletData: WalletData): Single<String> =
        api.getWalletIncomeCount(
            WalletSumRequest(
                balance = walletData.amount,
                currency = CurrencyResponse(
                    name = walletData.currency.name,
                    code = walletData.currency.code
                ),
                isHidden = walletData.hidden,
                id = walletData.id,
                limit = walletData.limit,
                name = walletData.name,
                user = UserResponse(
                    name = walletData.name,
                    username = walletData.username
                )
            )
        )

    override fun getAllExpenses(wallets: List<WalletData>): Single<String> =
        if (wallets.isNotEmpty()) {
            var toZipWith = getWalletExpensesInRubbles(wallets[0])
            for (i in 1 until wallets.size) {
                toZipWith = toZipWith.zipWith(
                    getWalletExpensesInRubbles(wallets[i]),
                    { sum, next ->
                        BigDecimal(sum).add(BigDecimal(next)).toString()
                    }
                )
            }
            toZipWith
        } else
            Single.just("0")


    override fun getAllIncome(wallets: List<WalletData>): Single<String> =
        if (wallets.isNotEmpty()) {
            var toZipWith = getWalletIncomeInRubbles(wallets[0])
            for (i in 1 until wallets.size) {
                toZipWith = toZipWith.zipWith(
                    getWalletIncomeInRubbles(wallets[i]),
                    { sum, next ->
                        BigDecimal(sum).add(BigDecimal(next)).toString()
                    }
                )
            }
            toZipWith
        } else
            Single.just("0")

    override fun deleteCategory(id: Long): Single<Boolean> =
        api.deleteCategory(id)


    private fun getWalletExpensesInRubbles(walletData: WalletData) =
        getExpensesByWallet(walletData)
            .flatMap { amount ->
                getCurrencyCourse(walletData.currency.code)
                    .map { course ->
                        BigDecimal(amount).multiply(BigDecimal(course.course)).toString()
                    }
            }

    private fun getWalletIncomeInRubbles(walletData: WalletData) =
        getIncomeByWallet(walletData)
            .flatMap { amount ->
                getCurrencyCourse(walletData.currency.code)
                    .map { course ->
                        BigDecimal(amount).multiply(BigDecimal(course.course)).toString()
                    }
            }
    override fun updateTransaction(walletTransactionModel: WalletTransactionModel) = TODO()
//        api.updateTransaction(
//            TransactionRequest(
//                amount = walletTransactionModel.amount,
//                categoryId = walletTransactionModel.transactionCategoryData.id,
//                date = walletTransactionModel.date,
//                isIncome = walletTransactionModel.isIncome,
//                transactionCurrencyCode =
//            )
//        )

    override fun deleteTransaction(id: Long): Single<Boolean> =
        api.deleteTransaction(id)


}
