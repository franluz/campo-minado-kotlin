package visao

import modelo.Tabuleiro
import modelo.TabuleiroEvento
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() {

}
class TelaPrincipal:JFrame(){
    private val tabuleiro = Tabuleiro(qtdLinhas = 16,qntColunas = 30,qtdMinas = 89)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)
    init {
        tabuleiro.onEvento { this:: mostrarResultado }
        add(painelTabuleiro)
        setSize(690,438)
        setLocation(null)
        defaultCloseOperation= EXIT_ON_CLOSE
        title= "Campo Minado"
        isVisible= true
    }
    private fun mostrarResultado(tabuleiroEvento: TabuleiroEvento){
        SwingUtilities.invokeLater {
            val msg = when(tabuleiroEvento){
                TabuleiroEvento.VITORIA-> " Você ganhou"
                TabuleiroEvento.DERROTA -> " Você perdeu"
            }
        }
    }
}