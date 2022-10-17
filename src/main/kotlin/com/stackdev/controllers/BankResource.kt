package com.stackdev.controllers

import com.stackdev.models.Account
import com.stackdev.models.Bank
import com.stackdev.models.Branch
import com.stackdev.models.Client
import com.stackdev.services.BankService
import org.eclipse.microprofile.graphql.*
import javax.inject.Inject

@GraphQLApi
class BankResource {

    @Inject
    lateinit var bankService: BankService

    @Query("allAccounts")
    @Description("Get all accounts")
    fun getAllAccounts(): MutableList<Account> {
        return bankService.listAccounts()
    }

    @Query("account")
    @Description("Getting an account by primary key id")
    fun getAccountById(@Name("accountId") id:Int): Account {
        return bankService.getAccountId(id)
    }

    @Query("branch")
    @Description("Getting an branch by primary key id")
    fun getBrandById(@Name("branchId")id:Int):Branch{
        return  bankService.getBranchById(id)
    }

    @Query("banksByBranch")
    @Description("Getting banks by Branch")
    fun getBanksByBranchLocation(@Source branch: Branch): MutableList<Bank>? {
        return bankService.getBanksByBranch(branch)
    }

    @Mutation
    fun createClient(client:Client): Client {
        return bankService.createClient(client)
    }

    @Mutation
    fun deleteAccount(id:Int):MutableList<Account>{
        return bankService.deleteAccount(id)
    }

    @Mutation
    fun updateAccount(account:Account,@Name("accountId") id:Int):Account{
        return  bankService.updateAccount(account,id)
    }
}