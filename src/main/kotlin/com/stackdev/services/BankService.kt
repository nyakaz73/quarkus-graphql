package com.stackdev.services

import com.stackdev.models.Account
import com.stackdev.models.Bank
import com.stackdev.models.Branch
import com.stackdev.models.Client
import java.util.stream.Collectors
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class BankService {

    final val banks = emptyList<Bank>().toMutableList()
    val clients = emptyList<Client>().toMutableList()
    private final val accounts = emptyList<Account>().toMutableList()
    private final val branches = emptyList<Branch>().toMutableList()

    init {
        //Initialise accounts
        accounts.add(
            Account(
                name = "Daniel Matthew",
                accountNumber = "12345",
                balance = 2000.00,
                client = Client(
                    firstName = "Daniel",
                    lastName = "Matthews",
                    age = 23,
                    idNumber = "324232"
                )
            )
        )
        accounts.add(
            Account(
                name = "Fred Lama",
                accountNumber = "123456",
                balance = 2000.00,
                client = Client(
                    firstName = "Fred",
                    lastName = "Lama",
                    age = 23,
                    idNumber = "656456"
                )
            )
        )
        //Initialise banks

        val branch = Branch(
            name = "NEW DELI",
            code = "0001"
        )
        val branch2 = Branch(
            name = "NEW DELI2",
            code = "0002"
        )
        branches.add(branch)
        branches.add(branch2)
        banks.add(
            Bank(
                name = "ABC Bank",
                bankCode = "B342346666",
                branch = branch
            )
        )
        banks.add(
            Bank(
                name = "ZBD Bank",
                bankCode = "0980000000",
                branch = branch
            )
        )

    }

    fun listBanks(): MutableList<Bank> {
        return banks
    }

    fun createClient(client:Client): Client {
         clients.add(client)
        return client
    }

    fun createAccount(account: Account): Account {
        accounts.add(account)
        return account
    }

    fun listAccounts(): MutableList<Account> {
        return accounts
    }

    fun getAccountId(id:Int):Account{
        return accounts[id]
    }

    fun createBank(bank:Bank): Bank {
        banks.add(bank)
        return bank
    }

    fun getBranchById(id:Int):Branch{
        return branches[id]
    }
    fun getBanksByBranch(branch:Branch): MutableList<Bank>? {
        return banks.stream()
            .filter { bank -> bank.branch.code!!.contains(branch.code!!) }
            .collect(Collectors.toList())
    }

    fun deleteAccount(id:Int): MutableList<Account> {
        accounts.removeAt(id)
        return accounts
    }

    fun updateAccount(account: Account, id: Int):Account{
        accounts[id].accountNumber = account.accountNumber
        accounts[id].name = account.name
        return accounts[id]
    }

}