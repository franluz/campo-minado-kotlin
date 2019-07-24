package modelo

import javax.swing.text.StyledEditorKit

enum class CampoEvento {ABERTURA,MARCACAO,EXPLOSAO,REINICIALIZACAO}
data class Campo(val linha:Int, val column:Int){
    private val vizinhos = ArrayList<Campo>();
    private val callbacks = ArrayList<(Campo,CampoEvento)->Unit>();
    var marcado: Boolean = false
    var aberto: Boolean = false
    var minado: Boolean = false
}