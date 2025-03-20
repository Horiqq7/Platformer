package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().KeyReleased(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().KeyReleased(e);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().KeyPressed(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().KeyPressed(e);
            }
        }
    }
}