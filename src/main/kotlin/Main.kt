import java.util.*

fun main() {
    val conta = ContaBancaria()
    val random = Random()
//    val date = Calendar.getInstance().time
//    val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
//    val dataOperaçao = dateTimeFormat.format(date)

    conta.addConta(Dados("Vinicius", 123, 0.0))
    conta.addConta(Dados("Victor", 124, 0.0))

    conta.depositar(123, 100.0)
    conta.depositar(123, 100.0)
    conta.depositar(123, 100.0)
    conta.depositar(123, 100.0)

    conta.depositar(124, 50.0)
    conta.depositar(124, 50.0)

    conta.sacar(123, 500.0)

    conta.extrato(123)
}

data class Dados(
    val nomeCliente: String,
    val numConta: Int,
    var saldoConta: Double
)

class ContaBancaria {
    val conta = mutableMapOf<Int, MutableList<Dados>>()
    val operacoes = mutableMapOf<Int, MutableList<String>>()
    var dadosOperacao = mutableListOf<String>()

    fun addConta(dados: Dados) {


        if (conta.contains(dados.numConta)) {

            conta[dados.numConta]?.add(dados)

        } else {
            val dadosConta = mutableListOf<Dados>()
            dadosConta.add(dados)
            conta[dados.numConta] = dadosConta
        }

    }

    fun depositar(numConta: Int, valor: Double) {
        conta[numConta]?.map { deposito: Dados ->
            deposito.saldoConta += valor
        }
        val infoDeposito = "Deposito de R$ $valor na conta $numConta."
        dadosOperacao.add(infoDeposito)
        operacoes[numConta] = dadosOperacao
    }

    fun sacar(numConta: Int, valor: Double) {
        conta[numConta]?.map { saque: Dados ->
            if (saque.saldoConta >= valor) {
                saque.saldoConta -= valor
                val infoDeposito = "Saque de R$ $valor na conta $numConta. Saldo de ${saque.saldoConta}"
                dadosOperacao.add(infoDeposito)
                operacoes[numConta] = dadosOperacao
            }
        }
    }


    fun extrato(numConta: Int) {

        println("Dados da conta-corrente numero: $numConta")
        println("--------------------------------------")

//        operacoes[numConta]?.forEach { s ->
//            println(s)
//        }

        conta[numConta]?.forEach {

            println("---------------------------------------")
            println("Nome: ${it.nomeCliente}, Numero conta: ${it.numConta}, Saldo: ${it.saldoConta}")

        }
    }
}
