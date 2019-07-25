package visao

import modelo.Tabuleiro
import java.awt.GridLayout
import javax.swing.JPanel

class PainelTabuleiro(tabuleiro: Tabuleiro):JPanel(){
    init{
        layout = GridLayout(tabuleiro.qtdLinhas,tabuleiro.qntColunas)
        tabuleiro.forEachCampos { campo->
            val botao = BotaoCampo(campo)
            add(botao)
        }
    }
}