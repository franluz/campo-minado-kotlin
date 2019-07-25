package modelo

import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedureCallbacks

enum class TabuleiroEvento(VITORIA,DERROTA)
class Tabuleiro(val qtdLinhas:Int, val qntColunas:Int, private val qtdMinas:Int){
    private val campos = ArrayList<ArrayList<Campo>>()
    private val callbacks = ArrayList<(TabuleiroEvento)->Unit>()
    init {
        gerarCampos()
        associarVizinhos()
        sortearMinas()
    }
    private fun gerarCampos(){
        for(linha in 0 util qtdLinhas){
            campos.add(ArrayList())
            for(coluna in 0 until qntColunas){
                val campoNovo = Campo(linha,coluna)
                campos[linha].add(campoNovo)
            }
        }
    }
    private fun associarVizinhos(){
        forEachCampos { associarVizinhos(it) }
    }
    private fun associarVizinhos(campo: Campo){
        var (linha,coluna) = campo
        val linhas = arrayListOf(linha-1,linha,linha+1)
        val colunas = arrayListOf(coluna-1,coluna, coluna+1)
        linhas.forEach{l->
            colunas.forEach{c->
                val atual = campos.getOrNull(l)?.getOrNull(c)
                atual?.takeIf { campo!=it }?.let { campo.addVizinho(it) }
            }
        }
    }
    private fun sortearMinas(){

    }
    fun forEachCampos(callback: (Campo)->Unit){
        campos.forEach{linha -> linha.forEach(callback)}
    }
}