# quarkus-graphql Project
### Show some :heart: and :star: the repo to support the project
# GraphQL Staff
## 1. Multiple Quries
GraphQL allows you to make multiple quries at once , See example Below

```jshelllanguage
{
	account1:account(accountId: 1){
    name
  }
  account2: account(accountId: 0){
    name,
    accountNumber
  }
}
```
```jshelllanguage
{
  branch1:branch(branchId:0){
    code
    name
  }
  branch2:branch(branchId:1){
    code
    name
  }
}
```

## 2. @Source
GraphQL allow you to add a source to your query, so get linked data
```kotlin
    @Query("banksByBranch")
    @Description("Getting banks by Branch")
    fun getBanksByBranchLocation(@Source branch: Branch): MutableList<Bank>? {
        return bankService.getBanksByBranch(branch)
    }
```
The source allow you to make a query as a source provider to you main search query
```jshelllanguage
{
	branch(branchId:1){
    name
    code
    banksByBranch {
      name
      bankCode
    }
  }
}
```
## 3. Mutation
Mutation allow us to create , update and delete records
### a) To Create a record
```kotlin
    @Mutation
    fun createClient(client:Client): Client {
        return bankService.createClient(client)
    }
```
Notice we didn't explicitly define the query name, we are using the function name as the query name
```text
mutation {
    createClient(client:{
        firstName: "Tafadzwa"
        lastName: "Lameck"
        idNumber:"3453453"
        age : 24
    }) {
        lastName
        firstName
        idNumber
        age
    }
}
```

### a) To Delete a record
Note from the resource class our service is going to return a mutable list of ```undeleted``` accounts.
```kotlin
@Mutation
fun deleteAccount(id:Int):MutableList<Account>{
    return bankService.deleteAccount(id)
}
```
Notice how we are returning the account details including the client details of the remaining accounts
```text
mutation {
  deleteAccount(id:1){
    name
    accountNumber
    balance
    client {
      firstName
      lastName
      age
      idNumber
    }
  }
}
```
Here is the response of the above query:
```text
{
  "data": {
    "deleteAccount": [
      {
        "name": "Daniel Matthew",
        "accountNumber": "12345",
        "balance": 2000,
        "client": {
          "firstName": "Daniel",
          "lastName": "Matthews",
          "age": 23,
          "idNumber": "324232"
        }
      }
    ]
  }
}
```

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-graphql-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- SmallRye GraphQL ([guide](https://quarkus.io/guides/microprofile-graphql)): Create GraphQL Endpoints using the code-first approach from MicroProfile GraphQL
