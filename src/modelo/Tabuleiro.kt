package modelo

import kotlin.random.Random
import java.util.*

enum class TabuleiroEvento{VITORIA,DERROTA}
class Tabuleiro(val qtdLinhas:Int, val qntColunas:Int, private val qtdMinas:Int){
    private val campos = ArrayList<ArrayList<Campo>>()
    private val callbacks = ArrayList<(TabuleiroEvento)->Unit>()
    init {
        gerarCampos()
        associarVizinhos()
        sortearMinas()
    }
    private fun gerarCampos(){
        for(linha in 0 until qtdLinhas){
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
        val gerador = java.util.Random()
        var linhaSorteada=-1
        var colunaSorteada=-1
        var qntMinasAtual=0
        while(qntMinasAtual<qtdMinas){
            linhaSorteada = gerador.nextInt(qtdLinhas)
            colunaSorteada = gerador.nextInt(qntColunas)
            val campoSorteado = campos[linhaSorteada][colunaSorteada]
            if(campoSorteado.seguro){
                campoSorteado.minar()
                qntMinasAtual++
            }
        }
    }
    private fun objetivoAlcancado():Boolean{
        var jogadorGanhou = true
        forEachCampos { if(!it.objetivoAlcancado) jogadorGanhou=false  }
        return jogadorGanhou
    }
    private fun verificarDerrotaOuVitoria(campo:Campo, campoEvento:CampoEvento){
        if(campoEvento == CampoEvento.EXPLOSAO){
            callbacks.forEach{it(TabuleiroEvento.DERROTA)}
        }else if(objetivoAlcancado()){
            callbacks.forEach{it(TabuleiroEvento.VITORIA)}
        }
    }
    fun forEachCampos(callback: (Campo)->Unit){
        campos.forEach{linha -> linha.forEach(callback)}
    }
    fun onEvento(callback: (TabuleiroEvento) -> Unit){
        callbacks.add (callback)
    }
    fun reiniciar(){
        forEachCampos { it.reiniciar() }
        sortearMinas()
    }
}