package inputs;

import gamestates.Gamestate;
import main.Game;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseClicked(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mousePressed(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mousePressed(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseReleased(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseDragged(e);
            }
            case MENU -> {
            }
            case OPTIONS -> {
            }
            case QUIT -> {
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseMoved(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseMoved(e);
            }
        }
    }
}
