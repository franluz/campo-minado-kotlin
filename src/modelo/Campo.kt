package modelo

import javax.swing.text.StyledEditorKit

enum class CampoEvento {ABERTURA,MARCACAO,EXPLOSAO,REINICIALIZACAO,DESMARCACAO}
data class Campo(val linha:Int, val column:Int){
    private val vizinhos = ArrayList<Campo>();
    private val callbacks = ArrayList<(Campo,CampoEvento)->Unit>();
    var marcado: Boolean = false
    var aberto: Boolean = false
    var minado: Boolean = false

    //somente leitura
    val desmarcado: Boolean get() =! marcado
    val fechado: Boolean get() =!aberto
    val seguro: Boolean get() =!minado
    val objetivoAlcancado:Boolean get()=seguro && aberto || minado && marcado
    val qntVizinhosMinados:Int get()=vizinhos.filter{it.minado==true}.size
    val vizinhancaSegura: Boolean get() = vizinhos.map{it.seguro}.reduce{resultado,seguro -> resultado && seguro}

    fun addVizinho(vizinho:Campo){
        vizinhos.add(vizinho)
    }
    fun onEvento(callback:(Campo,CampoEvento)->Unit){
        callbacks.add(callback)
    }
    fun abrir(){
        if(fechado){
            aberto = true
            if(minado){
                callbacks.forEach{it(this,CampoEvento.EXPLOSAO)}
            }else{
                callbacks.forEach{it(this,CampoEvento.ABERTURA)}
                vizinhos.filter { it.fechado && it.seguro && vizinhancaSegura }.forEach{it.abrir()}
            }
        }
    }
    fun alterarMarcacao(){
        if(fechado){
            marcado = !marcado
            val evento = if (marcado) CampoEvento.MARCACAO else CampoEvento.DESMARCACAO
            callbacks.forEach{it(this,evento)}
        }
    }
    fun minar(){
        minado=true
    }
    fun reiniciar(){
         marcado = false
         aberto = false
         minado = false
        callbacks.forEach {it(this,CampoEvento.REINICIALIZACAO)  }
    }
}