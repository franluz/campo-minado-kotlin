package visao

import modelo.Campo
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
class MouseCliqueListener(    private val campo:Campo,
                              private val onBotaoEsquerdo:(Campo)->Unit,
                              private val onBotaoDireiro:(Campo)->Unit
):MouseListener{

    override fun mousePressed(e: MouseEvent?) {
        when(e?.button){
            MouseEvent.BUTTON1 -> onBotaoEsquerdo
            MouseEvent.BUTTON2 -> onBotaoDireiro
        }
    }

    override fun mouseClicked(e: MouseEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mouseEntered(e: MouseEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mouseExited(e: MouseEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mouseReleased(e: MouseEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


