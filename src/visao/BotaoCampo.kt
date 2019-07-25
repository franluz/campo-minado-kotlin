package visao

import modelo.Campo
import modelo.CampoEvento
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.SwingUtilities

private val COR_BG_NORMAL = Color(184,184,184)
private val COR_BG_MARCACAO = Color(8,179,247)
private val COR_BG_EXPLOSAO = Color(189,66,68)
private val COR_TXT_VERDE = Color(0,100,0)
class BotaoCampo (private val campo: Campo):JButton(){

    init {
        font= font.deriveFont(Font.BOLD)
        background= COR_BG_NORMAL
        isOpaque=true
        border= BorderFactory.createBevelBorder(0)
        addMouseListener(MouseCliqueListener(campo,{it.abrir()},{it.alterarMarcacao()}))
        campo.onEvento(this::aplicarEstilo)
    }

    fun aplicarEstilo(campo: Campo,campoEvento: CampoEvento):Unit{
        when(campoEvento){
            CampoEvento.EXPLOSAO-> aplicarEstiloExplodido()
            CampoEvento.ABERTURA-> aplicarEstiloAberto()
            CampoEvento.MARCACAO-> aplicarEstiloMarcado()
            else-> aplicarEstiloPadrao()
        }
        SwingUtilities.invokeLater{
            repaint()
            validate()
        }
    }
    fun aplicarEstiloExplodido(){
        background=COR_BG_EXPLOSAO
        text="x"

    }
    fun aplicarEstiloAberto(){
        background=COR_BG_NORMAL
        border= BorderFactory.createLineBorder(Color.GRAY)
        foreground=when(campo.qntVizinhosMinados){
            1-> COR_TXT_VERDE
            2-> Color.BLUE
            3-> Color.YELLOW
            4,5,6-> Color.YELLOW
            else -> Color.PINK
        }
        text = if(campo.qntVizinhosMinados>0) "" + campo.qntVizinhosMinados else ""
    }
    fun aplicarEstiloMarcado(){
        background= COR_BG_MARCACAO
        foreground=Color.BLACK
        text="N"
    }
    fun aplicarEstiloPadrao(){
        background=COR_BG_NORMAL
        border= BorderFactory.createBevelBorder(0)
        text=""
    }
}